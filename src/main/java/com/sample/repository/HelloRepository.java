package com.sample.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.sample.Model.PagePaginationObj;

@Repository
public class HelloRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SqlRowSet findT01picAndT01Comm(String t01id) {
		String sqlT01 = " SELECT T01_PICNAME, T01_COMMENT FROM MT01 WHERE T01_ID = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlT01, t01id);
		System.out.println(sqlT01);
		return result;
		
	}
	public List<Map<String, Object>> searchT02ID(String t01id) {
		String sqlT02 = " SELECT * FROM MT02 WHERE T02_T01ID = '"+ t01id +"' ORDER BY T02_ID DESC ";
		System.out.println(sqlT02);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlT02);
		return list;
		
	}
	public List<Map<String, Object>> searchByname(String name,PagePaginationObj ppObj) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TB.* FROM ");
		sql.append(" (SELECT TA.*, ROWNUM RN FROM( ");
		sql.append(" SELECT * FROM MT01 WHERE T01_NAME='" + name + "' ORDER BY T01_ID) TA) TB ");
		sql.append(" WHERE RN BETWEEN " + ppObj.getStartRecord() + " AND "+ ppObj.getEndRecord());
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
		return list;
		
	}
	
	public List<Map<String, Object>> search(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TB.* FROM ");
		sql.append(" (SELECT TA.*, ROWNUM RN FROM( ");
		sql.append(" SELECT * FROM MT01 WHERE T01_NAME='" + name + "' ORDER BY T01_ID) TA) TB ");
		sql.append(" WHERE RN BETWEEN 1 AND 10 ");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
		return list;
		
	}
	
	public List<Map<String, Object>> findAll() {
		String sql = " SELECT * FROM MT01 ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}
	
	public void insertMT01Data(Object[] parasT01) {
		String sql = " INSERT INTO MT01 VALUES (?,?,?,?,?,?) ";
		jdbcTemplate.update(sql, parasT01);
		System.out.println(sql);
	}
	
	public void insertMT02Data(Object[] parasT02) {
		String sqlT2 =" INSERT INTO MT02 VALUES (?,?,?,?,?,?,?) ";
		jdbcTemplate.update(sqlT2, parasT02);
		System.out.println(sqlT2);
	}
	
	public void updateMT01Data(Object[] parasT01) {
		String sqlT1 = " UPDATE MT01 SET T01_NAME=?, T01_PRICE=?, T01_STORE=?, T01_PICNAME=?, T01_COMMENT=? WHERE T01_ID=? ";
		jdbcTemplate.update(sqlT1, parasT01);
		System.out.println(sqlT1);
	}

	public int selectRepeatMT01Data(Object[] parasT01) {
		String sqlRepeatPicName = " SELECT COUNT(*) AS CNT FROM MT01 " +
				" WHERE T01_ID <> '"+ parasT01[0] + "' AND T01_PICNAME = '"+ parasT01[1] +"' ";
		int reResult = (Integer) jdbcTemplate.queryForObject(sqlRepeatPicName, Integer.class);
		return reResult;
	}
	
	public void deleteMT01Data(String id) {
		String sql = " DELETE FROM MT01 WHERE T01_ID='" + id + "' ";
		jdbcTemplate.update(sql);
		System.out.println(sql);
	}
	
}
