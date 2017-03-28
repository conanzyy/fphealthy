package org.course.DBdao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;


public class DBMysql {
	
	private static Connection conn = null;

	private  Statement stm = null;

	private  ResultSet rs = null;
	
	private static DBMysql dboracle=null;
	
	private static DataSource ds;
	
	private DBMysql(){}
	
	public static DBMysql getInstance(){
		   if (dboracle == null) {
			   dboracle = new DBMysql();
	        }
	        return dboracle;
	}
	static{
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("dao-context.xml");
		    ds = (DataSource) ctx.getBean("dataSource");
		    try {
				DBMysql.conn = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean createConn() {
		boolean b = false;
		try {
			conn = ds.getConnection();
			if(conn != null){
				System.out.println("数据库连接成功");
				b=true;
			}else{
				System.out.println("数据库连接不成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public boolean update(String sql) {
		boolean b = false;
		try {
			stm = conn.createStatement();
			stm.execute(sql);
			b = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return b;
	}

	public void query(String sql) {
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception e) {
		}
	}
	
	public ResultSet queryInfo(String sql){
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception e) {
		}
		return rs;
	}
	
	public boolean next() {
		boolean b = false;
		try {
			if(rs.next())b = true;
		} catch (Exception e) {
		}
		return b;		
	}
	
	public String getValue(String field) {
		String value = null;
		try {
			if(rs!=null)value = rs.getString(field);
		} catch (Exception e) {
		}
		return value;
	}

	public void closeConn() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}

	public synchronized void closeStm() {
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException e) {
		}
	}

	public void closeRs() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
	}

	public Connection getConn() {
		return conn;
	}

	public ResultSet getRs() {
		return rs;
	}

	public Statement getStm() {
		return stm;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	

}
