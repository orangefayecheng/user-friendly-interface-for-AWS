package value_entity;

public class MessageModelS3 {
    private Integer status_code = 1;
    private String message= "S3 bucket created";
    private Object message_object;
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
	public Object getMessage_object() {
		return message_object;
	}
	public void setMessage_object(Object message_object) {
		this.message_object = message_object;
	}
    
    
    
}
