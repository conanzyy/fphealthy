package org.fphealthy.model;

public class UserAll {
	private String user_id;
	private String user_name;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public UserAll(String userId, String userName) {
		super();
		user_id = userId;
		user_name = userName;
	}
	
}
