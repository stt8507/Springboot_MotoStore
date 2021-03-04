package com.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.Model.PagePaginationObj;
import com.sample.service.HelloService;

@Controller
public class HelloController {

	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		
		ModelAndView mView = new ModelAndView();
		
		String gotoPage = request.getParameter("gotoPage");
		String totalRecord = request.getParameter("totalRecord");
		
		//初始搜尋頁面
		if(gotoPage == null) {
			return new ModelAndView("index");
		}
		
		String name = request.getParameter("nameForPage");
		Integer totalRecordInt = Integer.parseInt(totalRecord);
		PagePaginationObj ppObj = new PagePaginationObj(totalRecordInt, Integer.parseInt(gotoPage));
		List<Map<String, Object>> list = helloService.searchByIndex(new Object[]{name}, ppObj);
		mView.addObject("nameForPage", name);
		mView.addObject("list", list);
		mView.addObject("ppObj", ppObj);
		mView.setViewName("index");
		return mView;
	}
	
	@RequestMapping("/")
	public ModelAndView sign() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("sign");
		return mView;
	}

	@RequestMapping("/search")
	public ModelAndView searchData(HttpServletRequest request) {
		String name = request.getParameter("searchName");
		ModelAndView mView = new ModelAndView();
		List<Map<String, Object>> list = helloService.search(new Object[] {name});
		PagePaginationObj ppObj = new PagePaginationObj(list.size(), 1);
		mView.addObject("nameForPage", name);
		mView.addObject("list", list);
		mView.addObject("ppObj", ppObj);
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
		String comment = request.getParameter("comment");
		Object[] parasT01 = new String[]{seqnoT01, name, price, store, part.getSubmittedFileName(), comment};
		Object[] parasT02 = new String[]{seqnoT02, seqnoT01, price, store, part.getSubmittedFileName(), name, comment};
		helloService.insertData(parasT01, parasT02, part);
		ModelAndView mView = new ModelAndView();
		mView.setViewName("index");
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
				String comment = request.getParameter("T01_COMMENT_" + i);
				Part part = request.getPart("T01_PIC_" + i);
				
				Object[] parasT01 = new String[]{name, price, store, picName, comment, id};
				Object[] parasT02 = new String[]{seqnoM2, id, price, store, picName, name, comment};
				helloService.updateData(parasT01, parasT02, part);
				
 			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("index");
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
				Object[] parasT01 = new Object[] {id, picName};
				helloService.deleteData(parasT01);
			}

		}
		ModelAndView mView = new ModelAndView("index");
		return mView;
	}

	@RequestMapping("/show")
	public ModelAndView showAll() {
		ModelAndView mView = new ModelAndView();
		List<Map<String, Object>> list = helloService.showAll();
		mView.addObject("list", list);
		mView.setViewName("show");
		return mView;
	}
	
	@RequestMapping("/detail")
	public ModelAndView showDetail(HttpServletRequest request) {
		String t01id = request.getParameter("T01_CHOSENID");
		ModelAndView mView = new ModelAndView();
		mView = helloService.showDetail(t01id);
		mView.setViewName("detail");
		return mView;
	}
}
