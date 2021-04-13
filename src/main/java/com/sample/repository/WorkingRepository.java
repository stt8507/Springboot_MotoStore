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
		jdbcTemplate.update(sql, list.toArray());
	}
	
	public List<Map<String, Object>> selectJob(List<Object> list){
		String sql = " SELECT * FROM WT01 WHERE  WT01_TITLE = ? ";
		List<Map<String, Object>> listBack = jdbcTemplate.queryForList(sql, list.toArray());
		return listBack;
	}
	
	public List<String> getWorkingStatus(){
		String sql = " SELECT KIND_DESCRIPTION FROM KIND WHERE KIND_TYPE = 'WORKING_STATUS' ";
		List<String> list = jdbcTemplate.queryForList(sql, String.class);
		return list;
	}
	
	public void updateWorkingStatus(List<Object> list) {
		String sql = " UPDATE WT01 SET WT01_STATUS = ? WHERE WT01_ID = ? ";
		jdbcTemplate.update(sql, list.toArray());
	}

	public List<Map<String, Object>> getFile(List<Object> list) {
		String sql = " SELECT * FROM WT02 WHERE WT02_FILENAME LIKE ? ";
		List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql, list.toArray());
		return list2;
	}

	public void addNewDoc(String fileName) {
		String sql = " INSERT INTO WT02 VALUES(?,?) ";
		jdbcTemplate.update(sql, "WT02_02", fileName);
	}
}
