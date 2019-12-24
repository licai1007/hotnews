package com.hpit.hotnews.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CodeAction extends ActionSupport{
	private static final long serialVersionUID = -472161564227163239L;
	public void code(){
		BufferedImage image = new BufferedImage(78,32,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		g.fillRect(0, 0, 78,32);
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int index,len = chars.length;
		for(int i = 0;i < 4;i++){
			g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			g.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,32));
			index = r.nextInt(len);
			g.drawString(""+chars[index],(i*18)+2,28);
			sb.append(chars[index]);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("code",sb.toString());
		try {
			ImageIO.write(image,"jpg",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
