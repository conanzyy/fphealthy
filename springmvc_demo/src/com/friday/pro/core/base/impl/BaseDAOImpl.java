package com.friday.pro.core.base.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.friday.pro.core.base.IBaseDAO;

/**
 * DAO工厂类
 * 可以在此类中增加更多jdbcTemplate中的方法
 * @author lxie
 * **/
@Repository
public class BaseDAOImpl implements IBaseDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List queryForList(String sql) {
		System.out.println("执行语句：" + sql);
		List list = this.jdbcTemplate.queryForList(sql);
		return list;
	}

	public List queryForList(String sql, Object[] args) {
		System.out.println("执行语句：" + sql);
		List list = this.jdbcTemplate.queryForList(sql, args);
		return list;
	}

	public Map queryForMap(String sql, Object[] args) {
		System.out.println("执行语句：" + sql);
		Map map = this.jdbcTemplate.queryForMap(sql, args);
		return map;
	}

	public int queryForInt(String sql, Object[] args) {
		System.out.println("执行语句：" + sql);
		int count = this.jdbcTemplate.queryForInt(sql, args);
		return count;
	}

	public int update(String sql, Object[] args) {
		System.out.println("执行语句：" + sql);
		int eff = this.jdbcTemplate.update(sql, args);
		System.out.println("影响行数：" + eff);
		return eff;
	}
}
