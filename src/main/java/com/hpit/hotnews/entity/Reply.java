package com.hpit.hotnews.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Reply {
	private BigDecimal replyid;
    private BigDecimal cid;
    private String msg;
    private String rauthor;
    private Date rtime;
	public Reply() {
		super();
	}
	public Reply(BigDecimal replyid, BigDecimal cid, String msg,
			String rauthor, Date rtime) {
		super();
		this.replyid = replyid;
		this.cid = cid;
		this.msg = msg;
		this.rauthor = rauthor;
		this.rtime = rtime;
	}
	public BigDecimal getReplyid() {
		return replyid;
	}
	public void setReplyid(BigDecimal replyid) {
		this.replyid = replyid;
	}
	public BigDecimal getCid() {
		return cid;
	}
	public void setCid(BigDecimal cid) {
		this.cid = cid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRauthor() {
		return rauthor;
	}
	public void setRauthor(String rauthor) {
		this.rauthor = rauthor;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	@Override
	public String toString() {
		return "Reply [replyid=" + replyid + ", cid=" + cid + ", msg=" + msg
				+ ", rauthor=" + rauthor + ", rtime=" + rtime + "]";
	}
     
}
