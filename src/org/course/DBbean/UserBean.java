package org.course.DBbean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.course.DBdao.DBMysql;
import org.course.DBdao.DBMysql2;
import org.course.entity.AddressBook;


public class UserBean {

	/**
	 * 查询通讯录
	 * @param username
	 * @param password
	 * @return
	 */
	public List<AddressBook> getAddressBookInfo(String username) {
		DBMysql2 db = new DBMysql2();
		List<AddressBook> addressBookList =null;
		try {
			if(db.createConn()) {
				String sql = "select * from address_book where username='"+username+"'";
				ResultSet re=db.queryInfo(sql);
				addressBookList = new ArrayList<AddressBook>();			
				if(db.next()) {
					AddressBook  addressBook=new AddressBook();
					addressBook.setUserName(re.getString(1));
					addressBook.setMail(re.getString(2));
					addressBook.setMobile(re.getLong(3));
					addressBook.setJobNumber(re.getLong(4));
					addressBookList.add(addressBook);
				}
			} 
		}catch (SQLException e) {
			System.err.println(e.getMessage());	
		}finally{
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return addressBookList;
	}
	
	public boolean isExist(String username) {
		boolean isExist = false;
		DBMysql2 db = new DBMysql2();
		if(db.createConn()) {
			String sql = "select * from user where username='"+username+"'";
			db.query(sql);
			if(db.next()) {
				isExist = true;
			}
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return isExist;
	}
	
	public void add(String username, String password, String email) {
		DBMysql2 db = new DBMysql2();
		if(db.createConn()) {
			String sql = "insert into user(username,password,email) values('"+username+"','"+password+"','"+email+"')";
			System.out.println(sql);
			db.update(sql);
			db.closeStm();
			db.closeConn();
		}
	}
}
