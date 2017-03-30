package com.friday.pro.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.friday.pro.project.dao.ITestDao;
import com.friday.pro.project.service.ITestService;

/**
 * 业务逻辑层处理
 * 每个方法都自动视为事务来处理
 * @author lxie
 * **/
@Service
public class TestServiceImpl implements ITestService {
	@Resource
	private ITestDao testDao;
	
	public List test() {
		List datas = testDao.test();
		return datas;
	}
	
}
