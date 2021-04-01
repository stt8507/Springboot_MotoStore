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
}
