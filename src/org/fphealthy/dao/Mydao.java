package org.fphealthy.dao;

import java.util.List;
import java.util.Map;

import rule.bean.Rule;

public interface Mydao {
	public int update(String sql,Object[] objs);
	public List<Map<String,Object>> queryForList(String sql,Object[] objs);
	public int queryForInt(String sql);
}
