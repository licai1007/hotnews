package com.hpit.hotnews.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpit.hotnews.common.CheckoutUtil;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
public class OwnerCheckAction extends ActionSupport{
	private static final long serialVersionUID = -6630168977989590188L;
	/**
	 * 微信消息接收和token验证
	 * 
	 */
	 public void ownerCheck(){
	 System.out.println(111);	 HttpServletRequest request = ServletActionContext.getRequest();
	 HttpServletResponse response = ServletActionContext.getResponse();
	 boolean isGet = request.getMethod().toLowerCase().equals("get");
	 PrintWriter print;
	 if (isGet) {
	  // 微信加密签名
	  String signature = request.getParameter("signature");
	  // 时间戳
	  String timestamp = request.getParameter("timestamp");
	  // 随机数
	  String nonce = request.getParameter("nonce");
	  // 随机字符串
	  String echostr = request.getParameter("echostr");
	  // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
	  if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
	  try {
	   print = response.getWriter();
	   print.write(echostr);
	   print.flush();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  }
	 }
	 }
}
