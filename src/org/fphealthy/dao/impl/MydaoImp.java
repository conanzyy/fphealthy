package org.fphealthy.dao.impl;

import java.util.List;
import java.util.Map;



import org.fphealthy.dao.Mydao;
import org.springframework.jdbc.core.JdbcTemplate;
public class MydaoImp implements  Mydao{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public int update(String sql,Object[] objs){
		return jdbcTemplate.update(sql, objs);
	}
	public List<Map<String,Object>> queryForList(String sql,Object[] objs){
		return jdbcTemplate.queryForList(sql,objs);
	}
	@SuppressWarnings("deprecation")
	public int queryForInt(String sql){
		return jdbcTemplate.queryForInt(sql);
	}
}
