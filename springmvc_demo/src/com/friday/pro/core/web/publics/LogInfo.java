package com.friday.pro.core.web.publics;

import java.util.List;

import javax.annotation.Resource;

import com.friday.pro.core.base.IBaseDAO;
import com.friday.pro.core.web.util.Utils;

public class LogInfo {
	@Resource
	private IBaseDAO baseDao;
	private static LogInfo log = new LogInfo();
	private static String cTime = DateConverter.getToday() + " " + DateConverter.getCurrentTime();
	
	/**
	 * 记录日志信息接口
	 * author lxie
	 */
	public static int insertLog(String type,String title,String detail,String userid) {
		int eff = 0;
		String remark = "";
		try {
			log.insertLogInfos(cTime, type, title, detail, userid, remark);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eff;
	}
	
	/**
	 * 查询日志信息接口
	 * author lxie
	 */
	public static List findLog(String title, String detail, int pageId,int pageSize) {
		List datas = null;
		try {
			if(null == title || title.trim().equals("")){
				title = "";
			}else if(null == detail || detail.trim().equals("")){
				detail = "";
			}
			datas = log.findLogInfos(title, detail, pageId, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
	
	/**
	 * 记录日志信息
	 * author lxie
	 */
	private int insertLogInfos(String createTime,String type,String title,String detail,String userid,String remark) throws Exception {
		String sql = "insert into base_loginfo (logSN,createTime,type,title,detail,userid,remark) values (?, ?, ?,?, ?, ?,?)";
		return baseDao.update(sql, new Object[]{Utils.getUUID(),createTime,type,title,detail,userid,remark});
	}
	
	/**
	 * 查询日志信息
	 * author lxie
	 */
	private List findLogInfos(String title, String detail, int pageId,int pageSize) throws Exception {
		String sql = "select logSN,createTime,type,title,detail,userid,remark from base_loginfo where title like '%"+title+"%' and detail like '%"+detail+"%'  order by createTime desc limit "+(pageId-1)*pageSize+","+pageSize;
		List list = baseDao.queryForList(sql, new Object[]{});
		return list;
	}
}
