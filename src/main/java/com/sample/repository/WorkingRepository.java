package com.sample.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<String> geturgencyType(){
		String sql = " SELECT KIND_DESCRIPTION FROM KIND WHERE KIND_TYPE = 'DDL_WORKING_TYPE' ";
		List<String> list = jdbcTemplate.queryForList(sql, String.class);
		return list;	
	}
	
	public void saveJob(List<Object> list){
		String sql = " INSERT INTO WT01 VALUES (?,?,?,?,?,?,?,?,?,?) ";
		int n = jdbcTemplate.update(sql, list.toArray());
	}
	
	public List<Map<String, Object>> selectJob(List<Object> list){
		String sql = " SELECT * FROM WT01 WHERE  WT01_TITLE = ? ";
		List<Map<String, Object>> listBack = jdbcTemplate.queryForList(sql, list.toArray());
		return listBack;
	}
}
