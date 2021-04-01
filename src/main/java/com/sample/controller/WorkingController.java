package com.sample.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.repository.WorkingRepository;
import com.sample.service.WorkingService;

import javassist.expr.NewArray;


@Controller
public class WorkingController {
	
	@Autowired
	WorkingService workingService;

	@RequestMapping("/log")
	public ModelAndView logList(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("log");
		return mView;	
	}
	
	@RequestMapping("/searchWorkname")
	public ModelAndView searchWorkname(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("log");
		List<Object> workName = new ArrayList<Object>();
		workName.add(request.getParameter("workName"));
		List<Map<String, Object>> list2 = workingService.selectJob(workName);
		Map<String, Object> data = list2.get(0);
		List<String> tags = Arrays.asList(data.get("WT01_TYPE").toString().split(","));
		mView.addObject("status",data.get("WT01_STATUS"));
		mView.addObject("content",data.get("WT01_CONTENT"));
		mView.addObject("title",data.get("WT01_TITLE"));
		mView.addObject("cdate",data.get("WT01_CDATE"));
		mView.addObject("tags",tags);
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
	
	@RequestMapping("/addWork")
	public ModelAndView NavAndFooter(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		List<String> ddlList = workingService.getUrgencyType();
		mView.addObject("ddlList", ddlList);
		mView.setViewName("addWork");
		return mView;
		
	}
	
	@RequestMapping("/addNewWork")
	public ModelAndView addNewWork(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 30);
		List<Object> list = new ArrayList<Object>();
		list.add("WT01_2");
		list.add(request.getParameter("type"));
		list.add(request.getParameter("title"));
		list.add(request.getParameter("owner"));
		list.add(request.getParameter("urgency"));
		list.add("代接單");
		list.add(new Date());
		list.add(calendar.getTime());
		list.add(calendar.getTime());
		list.add(request.getParameter("content"));
		workingService.saveJob(list);
		List<String> ddlList = workingService.getUrgencyType();
		mView.addObject("ddlList", ddlList);
		mView.setViewName("addWork");
		return mView;
		
	}
	
	@RequestMapping("/addNewDoc")
	public ModelAndView addNewDoc(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("myDoc");
		return mView;
		
	}
	
	@RequestMapping("/uploadImg")
	public @ResponseBody Map<String, Object> uploadImg(@RequestParam("files") MultipartFile file) {
		Map<String, Object> map = new HashMap<String , Object>();
	   	String fileStr = file.getOriginalFilename();
		File imgServ = new File("D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\static\\article_image\\" +fileStr);
		try {
			file.transferTo(imgServ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("fileName", fileStr);
		return map;
	}
	
	@RequestMapping("/deleteImg")
	public @ResponseBody Map<String, Object> deleteImg(@RequestParam("imgSrc") String imgSrc) {
		Map<String, Object> map = new HashMap<String , Object>();
		String fileName = imgSrc.substring(imgSrc.lastIndexOf("/")+1);
		File imgServ = new File("D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\static\\article_image\\" +fileName);
		if(imgServ.exists()) {
			imgServ.delete();
		}
		map.put(imgSrc, imgSrc);
		return map;
	}
}
