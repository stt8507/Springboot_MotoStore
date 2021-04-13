package com.sample.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.sample.Model.PagePaginationObj;
import com.sample.Util.ImageUtils;
import com.sample.repository.HelloRepository;

@Service
public class HelloService {

	private String LOCALPATH = "D:\\workspace\\Springboot_MotoStore\\src\\main\\resources\\static\\image\\";

	@Autowired
	HelloRepository helloRepository;
	
	public List<Map<String, Object>> search(Object[] parasT01) {
		List<Map<String, Object>> list = helloRepository.search((String)parasT01[0]);
		return list;	
	}
	
	public List<Map<String, Object>> searchByIndex(Object[] parasT01, PagePaginationObj ppObj) {
		List<Map<String, Object>> list = helloRepository.searchByname((String)parasT01[0], ppObj);
		return list;	
	}
	
	public List<Map<String, Object>> showAll(){
		List<Map<String, Object>> list = helloRepository.findAll();
		return list;
	}
	
	public ModelAndView showDetail(String t01id){
		ModelAndView mView = new ModelAndView();
		List<Map<String, Object>> list = helloRepository.searchT02ID(t01id);
		SqlRowSet result = helloRepository.findT01picAndT01Comm(t01id);
		if(result != null) {
			while (result.next()) {
				String t01_picName = result.getString("T01_PICNAME");
				String t01_comment = result.getString("T01_COMMENT");
				mView.addObject("T01_COMMENT", t01_comment);
				mView.addObject("T01_PICNAME", "Ori_"+t01_picName);
			}
		}
		mView.addObject("list", list);
		return mView;
		
	}
	
	public void insertData(Object[] parasT01, Object[] parasT02, Part part) {
		helloRepository.insertMT01Data(parasT01);
		helloRepository.insertMT02Data(parasT02);
		try {
			uploadPic(part);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateData(Object[] parasT01, Object[] parasT02, Part part) {
		helloRepository.updateMT01Data(parasT01);
		helloRepository.insertMT02Data(parasT02);
		try {
			uploadPic(part);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(Object[] parasT01) {
		Integer recordNum = helloRepository.selectRepeatMT01Data(parasT01);
		helloRepository.selectRepeatMT01Data(parasT01);
		helloRepository.deleteMT01Data((String) parasT01[0]);

		if (recordNum.intValue() == 0) {
			try {
				delePic((String) parasT01[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void uploadPic(Part part) throws Exception {

		String partFileName = part.getSubmittedFileName();
		if ("".equals(partFileName)) {
			return;
		}
		// 要上傳的目標檔案存放路徑
		String[] nameparas = partFileName.split("\\.");
		String name = nameparas[0];
		String suffix = nameparas[1];

		String filepath = LOCALPATH + "Ori_" + partFileName;
		if (new File(filepath).exists()) {
			return;
		}
		try (InputStream in = part.getInputStream(); OutputStream out = new FileOutputStream(filepath)) {
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
		} catch (IOException ex) {
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
		if (file.exists() && fileOri.exists()) {
			file.delete();
			fileOri.delete();
		}
	}
	
	
}
