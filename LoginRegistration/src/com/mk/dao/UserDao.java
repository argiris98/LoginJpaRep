package com.mk.dao;

import com.mk.bean.User;
import com.mk.util.DBUtil;

public class UserDao {
	public void registerUser(String firstname,String lastname,String username,String password) throws Exception {
		DBUtil.executeUpdate(firstname,lastname,username,password);
		
	}
	
	public boolean authenticate(String username,String password) throws Exception {
		boolean authenticated=false;
		User user= DBUtil.executeQuery(username);
		if(user != null && user.getPassword().trim().equals(password)) {
			authenticated = true;
		}
		
		return authenticated;
		
		
	}
	
	public boolean isExist(String username) throws Exception {
		boolean exist=false;
		User user=DBUtil.executeQuery(username);
		if(user != null) {
			exist = true;
		}
		
		return exist;
		
	}

}
