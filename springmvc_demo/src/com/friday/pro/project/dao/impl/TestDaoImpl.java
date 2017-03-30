package com.friday.pro.project.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.friday.pro.core.base.IBaseDAO;
import com.friday.pro.project.dao.ITestDao;

/**
 * 数据访问层（DAO）
 * 注入的baseDao属性为DAO工厂类
 * 可以在IBaseDAO接口中增加更多的基础方法
 * 目前只提供几个常用方法供参考
 * @author lxie
 * **/
@Repository
public class TestDaoImpl implements ITestDao{
	@Resource
	private IBaseDAO baseDao;
	
	public List test(){
		String sql = "select userId,userName from web_userinfo";
		List list = baseDao.queryForList(sql);
		return list;
	}
}
