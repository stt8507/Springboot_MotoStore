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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.Util.FileUtils;
import com.sample.service.WorkingService;

import javassist.expr.NewArray;



@Controller
public class WorkingController {
	
	private String LOCALFILEPATH = "D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\file\\";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	WorkingService workingService;

	@RequestMapping("/log")
	public ModelAndView logList(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("sourcePage","work");
		mView.setViewName("log");
		return mView;	
	}
	
	@RequestMapping("/searchWorkname")
	public ModelAndView searchWorkname(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("sourcePage", "work");
		mView.setViewName("log");
		List<Object> workName = new ArrayList<Object>();
		workName.add(request.getParameter("workName"));
		List<Map<String, Object>> list2 = workingService.selectJob(workName);
		List<String> status = workingService.getWorkingStatus();
		mView.addObject("status",status);
		if(list2 != null) {
			Map<String, Object> data = list2.get(0);
			
			List<String> tags = Arrays.asList(data.get("WT01_TYPE").toString().split(","));
			mView.addObject("status",data.get("WT01_STATUS"));
			mView.addObject("content",data.get("WT01_CONTENT"));
			mView.addObject("title",data.get("WT01_TITLE"));
			mView.addObject("cdate",data.get("WT01_CDATE"));
			mView.addObject("selectedStatus",data.get("WT01_STATUS"));
			
			mView.addObject("tags",tags);
			mView.addObject("logID",data.get("WT01_ID"));
		}
		
		return mView;
		
	}
	
	@RequestMapping("/myDoc")
	public ModelAndView myDoc(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("sourcePage", "doc");
		mView.setViewName("myDoc");
		return mView;	
	}
	
	@RequestMapping("/changeWorkStatus")
	public ModelAndView changeWorkStatus(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(request.getParameter("WorkStatus"));
		listParam.add(request.getParameter("logTitle"));
		workingService.updateWorkingStatus(listParam);
		List<String> status = workingService.getWorkingStatus();
		mView.addObject("status",status);
		mView.addObject("sourcePage", "work");
		mView.setViewName("log");
		return mView;	
	}
	
	@RequestMapping("/WorkingList")
	public ModelAndView toDoList(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("sourcePage", "work");
		mView.setViewName("WorkingList");
		return mView;
		
	}
	
	@RequestMapping("/addWork")
	public ModelAndView NavAndFooter(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		List<String> ddlList = workingService.getUrgencyType();
		mView.addObject("ddlList", ddlList);
		mView.addObject("sourcePage", "work");
		mView.setViewName("addWork");
		return mView;
		
	}
	
	@RequestMapping("/addNewWork")
	public ModelAndView addNewWork(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 30);
		String date = sdf.format(new Date());
		List<Object> list = new ArrayList<Object>();
		list.add("WT01_" + date);
		list.add(request.getParameter("type"));
		list.add(request.getParameter("title"));
		list.add(request.getParameter("owner"));
		list.add(request.getParameter("urgency"));
		list.add("待接單");
		list.add(new Date());
		list.add(calendar.getTime());
		list.add(calendar.getTime());
		list.add(request.getParameter("content"));
		workingService.saveJob(list);
		List<String> ddlList = workingService.getUrgencyType();
		mView.addObject("ddlList", ddlList);
		mView.addObject("sourcePage", "work");
		mView.setViewName("addWork");
		return mView;
		
	}
	
	@RequestMapping("/addNewDoc")
	public ModelAndView addNewDoc(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		try {
			Part part = request.getPart("newFile");
			String fileName = part.getSubmittedFileName();
			FileUtils.upload(part, LOCALFILEPATH, fileName);
			workingService.addNewDoc(fileName);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			mView.addObject("errorMsg","No File!");
		}
		
		mView.addObject("sourcePage", "doc");
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
