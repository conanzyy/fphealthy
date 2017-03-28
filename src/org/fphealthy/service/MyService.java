package org.fphealthy.service;

import java.util.List;

import org.fphealthy.dao.Mydao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rule.bean.Rule;
import rule.dao.RuleDAO;

@Service
public class MyService {
	private Mydao dao;
	@Autowired
	public void setRuleDAO(Mydao dao) {
		this.dao = dao;
	}
	
	public List queryForList(String sql,Object[] objs){
		return dao.queryForList(sql,objs);
	}
	
	public int update(String sql,Object[] objs){
		return dao.update(sql,objs);
	}
}
