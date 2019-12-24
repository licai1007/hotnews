package com.hpit.hotnews.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hpit.hotnews.common.Constants;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWSSoap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationWebService.xml"})
public class TestWeatherWS {
	@Autowired
	private WeatherWSSoap soap;
	@Test
	public void testGetWeather(){
		ArrayOfString weather = soap.getWeather("武汉",Constants.WEBSERVICE_WEBXML_ID);
		System.out.println(weather.getString());
	}
	@Test
	public void testGetRegionProvince(){
		ArrayOfString province = soap.getRegionProvince();
		System.out.println(province.getString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
