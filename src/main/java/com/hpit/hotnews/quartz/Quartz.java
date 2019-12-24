package com.hpit.hotnews.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.service.MakeHtmlService;

public class Quartz {
	@Autowired
	private MakeHtmlService makeHtmlService;
	public void work(){
		System.out.println("我是来调度任务的");
		makeHtmlService.generate();
	}
	
}
