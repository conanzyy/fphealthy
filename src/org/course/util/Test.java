package org.course.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.course.DBdao.DBMysql;
import org.course.DBdao.DBMysql2;

public class Test {
	public static void main(String[] args)  {

	/*	String sql="select * from user_all";
//		DBMysql2 sss=new DBMysql2();
//		sss.createConn();
//		System.out.println("0000"+sss.queryInfo(sql));
		DBMysql db=DBMysql.getInstance();
		db.createConn();
		ResultSet rs=db.queryInfo(sql);
		try {
			if(rs.next()){
				System.out.println("====="+rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}*/
	}
	public static void test(){
		String sql="select * from user_all";
		DBMysql db=DBMysql.getInstance();
		db.createConn();
		ResultSet rs=db.queryInfo(sql);
		try {
			if(rs.next()){
				System.out.println("====="+rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
	}
}
