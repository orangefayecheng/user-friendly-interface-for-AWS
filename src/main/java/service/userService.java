package service;

import org.apache.ibatis.session.SqlSession;

import entity.user;
import mapper.UserMapping;
import util.GetsqlSession;
import util.Stringnull;
import value_entity.MessageModel;

public class userService {

	public MessageModel userLogin(String username, String userpwd) {
		MessageModel messagemodel  = new MessageModel();
		user u = new user();
		u.setusername(username);
		u.setuserpwd(userpwd);
		messagemodel.setMessage_object(u);
		if(Stringnull.isEmpty(username) || Stringnull.isEmpty(userpwd)) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("username and password cannot be empty");	
			return messagemodel;
		}
		SqlSession session = GetsqlSession.createSqlSession();
		UserMapping userMapping = session.getMapper(UserMapping.class);
		user us = userMapping.findUserbyName(username);
		if(us == null) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("user does not exist");
			return messagemodel;
		}
		if(!userpwd.equals(us.getuserpwd())) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("user password does not match");
			return messagemodel;
		}
		messagemodel.setMessage_object(us);
		return messagemodel;
	}
    public MessageModel userRegister(String username, String userpwd, String repeatpwd){
    	MessageModel messagemodel  = new MessageModel();
		user u = new user();
		u.setusername(username);
		u.setuserpwd(userpwd);
		messagemodel.setMessage_object(u);
		if(Stringnull.isEmpty(username) || Stringnull.isEmpty(userpwd)) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("username and password cannot be empty");	
			return messagemodel;
		}
		if(!userpwd.equals(repeatpwd)) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("The password does not match in two part");
			return messagemodel;
		}
		SqlSession session = GetsqlSession.createSqlSession();
		UserMapping userMapping = session.getMapper(UserMapping.class);
		user us = userMapping.findUserbyName(username);
		if(us != null) {
			messagemodel.setStatus_code(0);
			messagemodel.setMessage("username has already been taken.");
			return messagemodel;
		}
		userMapping.createUser(username, userpwd);
		session.commit();
		messagemodel.setMessage_object(u);
		return messagemodel;
    }
}
