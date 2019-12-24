package com.hpit.hotnews.entity;

import java.math.BigDecimal;
import java.util.Date;

public class News {
	private BigDecimal newsid;
    private String title;
    private String content;
    private String pictureUrl;
    private String author;
    private BigDecimal thumbsUp;
    private BigDecimal collect;
    private Date createdate;
    private String at;
    private String source;
	public News() {
		super();
	}
	public News(BigDecimal newsid, String title, String content,
			String pictureUrl, String author, BigDecimal thumbsUp,
			BigDecimal collect, Date createdate, String at, String source) {
		super();
		this.newsid = newsid;
		this.title = title;
		this.content = content;
		this.pictureUrl = pictureUrl;
		this.author = author;
		this.thumbsUp = thumbsUp;
		this.collect = collect;
		this.createdate = createdate;
		this.at = at;
		this.source = source;
	}
	public BigDecimal getNewsid() {
		return newsid;
	}
	public void setNewsid(BigDecimal newsid) {
		this.newsid = newsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BigDecimal getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(BigDecimal thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	public BigDecimal getCollect() {
		return collect;
	}
	public void setCollect(BigDecimal collect) {
		this.collect = collect;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "News [newsid=" + newsid + ", title=" + title + ", content="
				+ content + ", pictureUrl=" + pictureUrl + ", author=" + author
				+ ", thumbsUp=" + thumbsUp + ", collect=" + collect
				+ ", createdate=" + createdate + ", at=" + at + ", source="
				+ source + "]";
	}
    

}
