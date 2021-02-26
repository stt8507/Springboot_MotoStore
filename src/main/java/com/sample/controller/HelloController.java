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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.Util.ImageUtils;

@Controller
public class HelloController {

	private String LOCALPATH = "D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\static\\image\\";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
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
	public ModelAndView InsertData(HttpServletRequest request) throws Exception {
		
		String seqnoT01 = "T01" + sdf.format(new Date());
		String seqnoT02 = "T02" + sdf.format(new Date());
		Part part = request.getPart("file");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String store = request.getParameter("store");
		Object[] parasT01 = new String[]{seqnoT01, name, price, store, part.getSubmittedFileName()};
		Object[] parasT02 = new String[]{seqnoT02, seqnoT01, price, store, part.getSubmittedFileName(), name};
		String sql = " INSERT INTO MT01 VALUES (?,?,?,?,?) ";
		jdbcTemplate.update(sql, parasT01);
		System.out.println(sql);
		String sqlT2 =" INSERT INTO MT02 VALUES (?,?,?,?,?,?) ";
		jdbcTemplate.update(sqlT2, parasT02);
		System.out.println(sqlT2);
		uploadPic(part);
		ModelAndView mView = findAll();
		return mView;
	}

	@RequestMapping("/update")
	public ModelAndView UpdateData(HttpServletRequest request) throws Exception {
		String listCountString = request.getParameter("listCount");
		int listCount = Integer.parseInt(listCountString);

		for (int i = 1; i <= listCount; i++) {
			String selchk = request.getParameter("selchk_" + i);

			if ("Y".equals(selchk)) {
				String seqnoM2 = "T02" + sdf.format(new Date());
				String id = request.getParameter("T01_ID_" + i);
				String name = request.getParameter("T01_NAME_" + i);
				String price = request.getParameter("T01_PRICE_" + i);
				String store = request.getParameter("T01_STORE_" + i);
				String picName = request.getParameter("T01_PICNAME_" + i);
				Part part = request.getPart("T01_PIC_" + i);
				Object[] parasT01 = new String[]{name, price, store, picName, id};
				Object[] parasT02 = new String[]{seqnoM2, id, price, store, picName, name};
				String sqlT1 = " UPDATE MT01 SET T01_NAME=?, T01_PRICE=?, T01_STORE=?, T01_PICNAME=? WHERE T01_ID=? ";
				jdbcTemplate.update(sqlT1, parasT01);
				System.out.println(sqlT1);
				String sqlT2 =" INSERT INTO MT02 VALUES (?,?,?,?,?,?) ";
				jdbcTemplate.update(sqlT2, parasT02);
				System.out.println(sqlT2);
				uploadPic(part);
 			}
		}
		ModelAndView mView = findAll();
		return mView;
	}

	@RequestMapping("/delete")
	public ModelAndView DeleteData(HttpServletRequest request) throws Exception {
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
	
	@RequestMapping("/detail")
	public ModelAndView showDetail(HttpServletRequest request) {
		String t01id = request.getParameter("T01_CHOSENID");
		ModelAndView mView = new ModelAndView();
		String sql = " SELECT * FROM MT02 WHERE T02_T01ID = '"+ t01id +"' ORDER BY T02_ID ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		mView.addObject("list", list);
		mView.setViewName("detail");
		return mView;
	}
	
	public void uploadPic(Part part) throws Exception {
		
		String partFileName = part.getSubmittedFileName();
		if("".equals(partFileName)){
			return;
		}
		// 要上傳的目標檔案存放路徑
		String[] nameparas = partFileName.split("\\.");
		String name = nameparas[0];
		String suffix = nameparas[1];
		
		String filepath = LOCALPATH + "Ori_" + partFileName;
		if(new File(filepath).exists()) {
			return;
		}
		try(InputStream in = part.getInputStream();  
		OutputStream out = new FileOutputStream(filepath)) {
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	             out.write(buffer, 0, length);
	        }
		} catch(IOException ex) {
	            throw new UncheckedIOException(ex);
	    }
		ImageUtils iU = new ImageUtils(filepath, LOCALPATH, name, suffix);
		iU.zoom(1920, 1080);
	}
	
	public void delePic(String filename) throws Exception {
		
		String fileString = LOCALPATH + filename;
		String fileOriString = LOCALPATH + "Ori_" + filename;
		File file = new File(fileString);
		File fileOri = new File(fileOriString);
		if(file.exists() && fileOri.exists()) {
			file.delete();
			fileOri.delete();
		}
	}
}
