package value_entity;

import entity.user;

public class MessageModel {
    private Integer status_code = 1;
    private String message= "Log in success";
    private user message_object;
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public user getMessage_object() {
		return message_object;
	}
	public void setMessage_object(user message_object) {
		this.message_object = message_object;
	}
    
    
    
}
