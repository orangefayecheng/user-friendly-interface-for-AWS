package entity;

public class user {
    private Integer userID;
    private String  username;
    private String  userpwd;
    private String  usermasterkey;
    private String  useraccesskey;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String uName) {
		this.username = uName;
	}
	public String getuserpwd() {
		return userpwd;
	}
	public void setuserpwd(String uPwd) {
		this.userpwd = uPwd;
	}
	public String getUsermasterkey() {
		return usermasterkey;
	}
	public void setUsermasterkey(String usermasterkey) {
		this.usermasterkey = usermasterkey;
	}
	public String getUseraccesskey() {
		return useraccesskey;
	}
	public void setUseraccesskey(String useraccesskey) {
		this.useraccesskey = useraccesskey;
	}
	
}
