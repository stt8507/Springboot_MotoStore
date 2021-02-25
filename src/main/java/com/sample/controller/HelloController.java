package com.sample.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	private String LOCALPATH = "D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\static\\image\\";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public ModelAndView findAll() {
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT01 ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("index");
		return mView;
	}

	@RequestMapping("/search")
	public ModelAndView findByName(HttpServletRequest request) {
		String name = request.getParameter("name");
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT01 WHERE T01_NAME='" + name + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("index");
		return mView;
	}

	@RequestMapping("/insert")
	public ModelAndView InsertData(HttpServletRequest request) throws IOException, ServletException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String seqno = "T01" + sdf.format(new Date());
		Part part = request.getPart("file");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String store = request.getParameter("store");
		String sql = " INSERT INTO MT01 VALUES ('" + seqno + "','" + name + "','" + price + "','" + store + "','" + part.getSubmittedFileName() + "') ";
		jdbcTemplate.update(sql);
		System.out.println(sql);
		uploadPic(part);
		ModelAndView mView = findAll();
		return mView;
	}

	@RequestMapping("/update")
	public ModelAndView UpdateData(HttpServletRequest request) throws IOException, ServletException {
		String listCountString = request.getParameter("listCount");
		int listCount = Integer.parseInt(listCountString);

		for (int i = 1; i <= listCount; i++) {
			String selchk = request.getParameter("selchk_" + i);

			if ("Y".equals(selchk)) {
				
				String id = request.getParameter("T01_ID_" + i);
				String name = request.getParameter("T01_NAME_" + i);
				String price = request.getParameter("T01_PRICE_" + i);
				String store = request.getParameter("T01_STORE_" + i);
				Part part = request.getPart("T01_PIC_" + i);
				String sql = " UPDATE MT01 SET T01_NAME='" + name + "',T01_PRICE='" + price + "',T01_STORE='" + store +
						"',T01_PIC='" + part.getSubmittedFileName() + "' WHERE T01_ID='" + id + "' ";
				jdbcTemplate.update(sql);
				System.out.println(sql);
				
 			}
		}
		ModelAndView mView = findAll();
		return mView;
	}

	@RequestMapping("/delete")
	public ModelAndView DeleteData(HttpServletRequest request) throws IOException, ServletException {
		String listCountString = request.getParameter("listCount");
		int listCount = Integer.parseInt(listCountString);

		for (int i = 1; i <= listCount; i++) {
			String selchk = request.getParameter("selchk_" + i);

			if ("Y".equals(selchk)) {
				String id = request.getParameter("T01_ID_" + i);
				String picName = request.getParameter("T01_PICNAME_" + i);
				delePic(picName);
				String sql = " DELETE FROM MT01 WHERE T01_ID='" + id + "' ";
				jdbcTemplate.update(sql);
				System.out.println(sql);
			}

		}
		ModelAndView mView = findAll();
		return mView;
	}

	@RequestMapping("/show")
	public ModelAndView showAll() {
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT01 ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("show");
		return mView;
	}
	
	public void uploadPic(Part part) throws IOException, ServletException {
		// 要上傳的目標檔案存放路徑
		
		try(InputStream in = part.getInputStream();  
		OutputStream out = new FileOutputStream(LOCALPATH + part.getSubmittedFileName())) {
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	             out.write(buffer, 0, length);
	        }
		} catch(IOException ex) {
	            throw new UncheckedIOException(ex);
	    }
	}
	
	public void delePic(String filename) throws IOException, ServletException {
		String fileString = LOCALPATH + filename;
		System.out.println(filename);
		
		File file = new File(fileString);
		file.delete();
	}
}
