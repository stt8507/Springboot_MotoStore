package com.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")
    public ModelAndView findAll(){
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT01 ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("index");
		return mView;
    }
	
	@RequestMapping("/search")
    public ModelAndView findByName(HttpServletRequest request){
		String name = request.getParameter("name");
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT01 WHERE T01_NAME='" + name + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("index");
		return mView;
    }
	
	@RequestMapping("/insert")
    public ModelAndView InsertData(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String seqno = "T01" + sdf.format(new Date());
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String store = request.getParameter("store");
		String sql = " INSERT INTO MT01 VALUES ('"+seqno+"','"+name+"','"+price+"','"+store+"') ";
		jdbcTemplate.update(sql);
		System.out.println(sql);
		ModelAndView mView = findAll();
		return mView;
    }
	
	@RequestMapping("/update")
    public ModelAndView UpdateData(HttpServletRequest request){
		String listCountString = request.getParameter("listCount");
		int listCount = Integer.parseInt(listCountString);
		
		
		for (int i = 1; i <= listCount; i++) {
			String selchk = request.getParameter("selchk_" + i);
			
			if("Y".equals(selchk)) {
				String id = request.getParameter("T01_ID_" + i);
				String name = request.getParameter("T01_NAME_" + i);
				String price = request.getParameter("T01_PRICE_" + i);
				String store = request.getParameter("T01_STORE_" + i);
				String sql = " UPDATE MT01 SET T01_NAME='"+name+"',T01_PRICE='"+price+"',T01_STORE='"+store+"' WHERE T01_ID='"+id+"' ";
				jdbcTemplate.update(sql);
				System.out.println(sql);
			}
		}
		ModelAndView mView = findAll();
		return mView;
    }
	
	@RequestMapping("/delete")
    public ModelAndView DeleteData(HttpServletRequest request){
		String listCountString = request.getParameter("listCount");
		int listCount = Integer.parseInt(listCountString);
		
		for (int i = 1; i <= listCount; i++) {
			String selchk = request.getParameter("selchk_" + i);
			
			if("Y".equals(selchk)) {
				String id = request.getParameter("T01_ID_" + i);
				String sql = " DELETE FROM MT01 WHERE T01_ID='"+id+"' ";
				jdbcTemplate.update(sql);
				System.out.println(sql);
			}
			
		}
		ModelAndView mView = findAll();
		return mView;
    }
}
