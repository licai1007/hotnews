package com.hpit.hotnews.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.hpit.hotnews.entity.News;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class FreemarkerUtil implements ServletContextAware{
	private ServletContext servletContext;
	@Autowired
	private Configuration configuration;
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public void toHTML(Map<String,Object> root){
		try {
			Template template = configuration.getTemplate("news.html");
			News news = (News)root.get("news");
			String realPath = servletContext.getRealPath("");
			String filePath = "/html/"+news.getNewsid()+".html";
			String path = realPath + filePath;
			Writer out = new OutputStreamWriter(new FileOutputStream(path),"utf-8");
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
