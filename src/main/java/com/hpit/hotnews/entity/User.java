package com.hpit.hotnews.entity;

import java.math.BigDecimal;
import java.util.Date;



public class User {
	private BigDecimal userid;
	private String username;
	private String password = "";
	private String email;
	private String uuid;
	private String openid;
	private String nickname;
	private String sex;
	private String city;
	private String headimgurl;
	private Date createdate;
	private Date modifytime;
	
	private String language;
	private String province;
	private String country;
	private String[] privilege;
	private String code;
	
	public User() {
		super();
	}
	public User(BigDecimal userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	public User(BigDecimal userid, String username, String password,
			String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User(BigDecimal userid, String username, String password,
			String email, String openid, String nickname,String sex,
			String city,String headimgurl,String uuid,Date createdate) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.uuid = uuid;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.city = city;
		this.headimgurl = headimgurl;
		this.createdate = createdate;
	}
	public BigDecimal getUserid() {
		return userid;
	}
	public void setUserid(BigDecimal userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "用户名："+username+"，密码："+password;
	}
}
