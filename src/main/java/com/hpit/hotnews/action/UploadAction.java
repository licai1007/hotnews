package com.hpit.hotnews.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hpit.hotnews.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	private static final long serialVersionUID = -7876518734758989115L;
	private File[] video;
	private String[] videoFileName;
	private String[] videoContentType;
	private String flag = "video";
	private String result;
	
	public String upload() throws Exception {
		System.out.println("执行上传");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(null == user){
			result = "notLogin";
			return "success";
		}
		for(int i=0;i<video.length;i++){
			String realPath = ServletActionContext.getServletContext().getRealPath("/video");
			InputStream is = new FileInputStream(video[i]);
			OutputStream os = new FileOutputStream(realPath+"\\"+videoFileName[i]);
			byte buffer[] = new byte[1024];
			int length = 0;
			while((length = is.read(buffer)) != -1){
				os.write(buffer,0,length);
			}
			os.close();
			is.close();
		}
		result = "ok";
		return "success";
	}

	public File[] getVideo() {
		return video;
	}

	public void setVideo(File[] video) {
		this.video = video;
	}

	public String[] getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String[] videoFileName) {
		this.videoFileName = videoFileName;
	}

	public String[] getVideoContentType() {
		return videoContentType;
	}

	public void setVideoContentType(String[] videoContentType) {
		this.videoContentType = videoContentType;
	}

	public String getFlag() {
		return flag;
	}

	public String getResult() {
		return result;
	}
	
}
