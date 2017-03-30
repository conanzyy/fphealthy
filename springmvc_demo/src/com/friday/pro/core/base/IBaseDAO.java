package com.friday.pro.core.base;

import java.util.List;
import java.util.Map;

public interface IBaseDAO {
	public List queryForList(String sql);
	public int update(String sql,Object[] args);
	public List queryForList(String sql, Object[] args);
	public int queryForInt(String sql, Object[] args);
	public Map queryForMap(String sql,Object[] args);
}
