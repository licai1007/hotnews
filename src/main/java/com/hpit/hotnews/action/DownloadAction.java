package com.hpit.hotnews.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
	private static final long serialVersionUID = -1482495692085231174L;
	private InputStream downLoadFile;
	private String fileName;
	private String flag = "video";
	@Override
	public String execute() throws Exception {
		System.out.println("执行下载");
		String realPath = ServletActionContext.getServletContext().getRealPath("/video");
		File file = new File(realPath +File.separator+fileName);
		downLoadFile = new FileInputStream(file);
		return Action.SUCCESS;
	}
	public InputStream getDownLoadFile() {
		return downLoadFile;
	}
	public void setDownLoadFile(InputStream downLoadFile) {
		this.downLoadFile = downLoadFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFlag() {
		return flag;
	}
	
}
