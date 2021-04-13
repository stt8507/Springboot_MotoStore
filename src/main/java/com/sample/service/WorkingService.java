package com.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.repository.WorkingRepository;

@Service
public class WorkingService {

	@Autowired
	WorkingRepository workingRepository;

	public List<String> getUrgencyType() {
		List<String> list = workingRepository.geturgencyType();
		return list;
	}
	
	public void saveJob(List<Object> list) {
		workingRepository.saveJob(list);
	}
	
	public List<Map<String, Object>> selectJob(List<Object> list) {	
		List<Map<String, Object>> listBack = workingRepository.selectJob(list);
		return listBack;
	}
	
	public List<String> getWorkingStatus() {
		List<String> list = workingRepository.getWorkingStatus();
		return list;
	}
	
	public String[] getFile(List<Object> list) {
		List<Map<String, Object>> list2 = workingRepository.getFile(list);
		String fileName = list2 == null ? "" :(String)list2.get(0).get("WT02_FILENAME");
		String[] fileStatus = fileName.split("\\.");
		return fileStatus;
	}
	
	public void updateWorkingStatus(List<Object> list) {
		workingRepository.updateWorkingStatus(list);
	}

	public void addNewDoc(String fileName) {
		workingRepository.addNewDoc(fileName);
	}
}
