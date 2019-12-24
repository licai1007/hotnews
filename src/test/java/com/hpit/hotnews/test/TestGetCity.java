package com.hpit.hotnews.test;

import org.junit.Test;

import com.hpit.hotnews.common.GetCity;

public class TestGetCity {
	@Test
	public void testGetCity(){
		String ct = GetCity.getCity();
		System.out.println(ct);
	}
}
