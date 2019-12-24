package com.hpit.hotnews.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWSSoap;

import com.hpit.hotnews.common.Constants;
import com.hpit.hotnews.common.GetCity;
import com.opensymphony.xwork2.ActionSupport;

public class WeatherAction extends ActionSupport{
	private static final long serialVersionUID = -6536151309389864910L;
	@Autowired
	private WeatherWSSoap soap;
	private List<String> listInfo;
	public String getWeather(){
		ArrayOfString weatherInfo = soap.getWeather("深圳",Constants.WEBSERVICE_WEBXML_ID);
		listInfo = weatherInfo.getString();
		System.out.println(listInfo);
		return SUCCESS;
	}
	public List<String> getListInfo() {
		return listInfo;
	}
	
}
