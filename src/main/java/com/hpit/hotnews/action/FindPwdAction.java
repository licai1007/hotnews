package com.hpit.hotnews.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.hpit.hotnews.common.AESUtil;
import com.hpit.hotnews.common.Constants;
import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FindPwdAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = -4650339823658001541L;
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender javaMailSender;
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	@Override
	public void validate() {
		logger.info("执行找回密码后端验证！");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(user.getCode().toUpperCase().equals(session.getAttribute("code"))){
			boolean b = userService.checkUser(user.getUsername());
			if(b){
				return;
			}else{
				addFieldError("findError","您输入的用户名不存在！");
			}
		}else{
			addFieldError("findError","您输入的验证码不正确！");
		}
		ActionContext context = ServletActionContext.getContext();
		LinkedHashMap<String,ArrayList<String>> value = (LinkedHashMap<String,ArrayList<String>>)context.getValueStack().findValue("fieldErrors");
		ArrayList<String> list = value.get("findError");
		request.setAttribute("error","findError#"+list.get(0));
	}
	@Override
	public String execute() throws Exception {
		logger.info("执行找回密码控制器！");
		HttpServletRequest request = ServletActionContext.getRequest();
		User ur = userService.getUser(user.getUsername());
		if(ur.getEmail().equals("无")){
			request.setAttribute("fpwd","您的账户未绑定邮箱！");
		}else{
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			Map<String,Object> templateData = new HashMap<String,Object>();
			templateData.put("name",ur.getUsername());
			byte[] pwd = AESUtil.decrypt(AESUtil.parseHexStr2Byte(ur.getPassword()),
					Constants.AES_PWD);
			templateData.put("password",new String(pwd));
			helper.setFrom("user1@licai.com");
			helper.setTo(ur.getEmail());
			helper.setSubject("通过邮箱找回密码！");
			helper.setText(getHtml(templateData),true);
			javaMailSender.send(message);
			request.setAttribute("fpwd","密码已发送到您的邮箱！");
		}
		return SUCCESS;
	}
	@Override
	public User getModel() {
		if(null == user){
			user = new User();
		}
		return user;
	}
	
	@Autowired
	private Configuration configuration;

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public String getHtml(Map<String, Object> templateData) {
		String str = "";
		try {
			Template template = configuration
					.getTemplate("findPwdTemplate.ftl");
			str = FreeMarkerTemplateUtils.processTemplateIntoString(template,
					templateData);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return str;
	}
}
