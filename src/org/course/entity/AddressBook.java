package org.course.entity;

/**
 * 通讯录基类
 * 
 * @author zhukejia
 * @date 2013
 */
public class AddressBook {
	// 通讯人姓名
	private String UserName;
	// 邮箱地址
	private String Mail;
	// 手机号码
	private long Mobile;
	// 工号
	private long JobNumber;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public long getMobile() {
		return Mobile;
	}

	public void setMobile(long mobile) {
		Mobile = mobile;
	}

	public long getJobNumber() {
		return JobNumber;
	}

	public void setJobNumber(long jobNumber) {
		JobNumber = jobNumber;
	}
}