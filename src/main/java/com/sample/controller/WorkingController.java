package com.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WorkingController {

	@RequestMapping("/log")
	public ModelAndView logList(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("log");
		return mView;
		
	}
	
	@RequestMapping("/myDoc")
	public ModelAndView myDoc(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("myDoc");
		return mView;
		
	}
	
	@RequestMapping("/toDoList")
	public ModelAndView toDoList(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("toDoList");
		return mView;
		
	}
	
	@RequestMapping("/NavAndFooter")
	public ModelAndView NavAndFooter(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("NavAndFooter");
		return mView;
		
	}
}
