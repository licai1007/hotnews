package com.hpit.hotnews.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Comment {
	private BigDecimal commentid;
    private BigDecimal nid;
    private String message;
    private String writer;
    private BigDecimal agree;
    private Date writertime;
    private List<Reply> replys;
	public Comment() {
		super();
	}
	public Comment(BigDecimal commentid, BigDecimal nid, String message,
			String writer, BigDecimal agree, Date writertime) {
		super();
		this.commentid = commentid;
		this.nid = nid;
		this.message = message;
		this.writer = writer;
		this.agree = agree;
		this.writertime = writertime;
	}
	public BigDecimal getCommentid() {
		return commentid;
	}
	public void setCommentid(BigDecimal commentid) {
		this.commentid = commentid;
	}
	public BigDecimal getNid() {
		return nid;
	}
	public void setNid(BigDecimal nid) {
		this.nid = nid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public BigDecimal getAgree() {
		return agree;
	}
	public void setAgree(BigDecimal agree) {
		this.agree = agree;
	}
	public Date getWritertime() {
		return writertime;
	}
	public void setWritertime(Date writertime) {
		this.writertime = writertime;
	}
	
	public List<Reply> getReplys() {
		return replys;
	}
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", nid=" + nid
				+ ", message=" + message + ", writer=" + writer + ", agree="
				+ agree + ", writertime=" + writertime + "]";
	}
    
}
