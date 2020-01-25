package util;

import java.util.Hashtable;

public class AllUser {
	private String username;
	private String password;
	private Hashtable<String, MyDir> userlist = new Hashtable<String, MyDir>();
	private Hashtable<String, String> user_password = new Hashtable<String, String>();
    private static AllUser inst=new AllUser();
    
	private AllUser(){
		
	}
	
	public static synchronized AllUser getInstance(){
		return inst;
	}
	
	public void addUser(MyDir a) {
		this.userlist.put(a.getName(), a);
		this.user_password.put(a.getName(), a.getPassword());
	}
	
	public boolean whetherExist(String username){
		return userlist.containsKey(username);
	}
	
	public boolean isOk(String username,String password){
		return password.equals(this.user_password.get(username));
	}
	
    public MyDir getUserDir(String username){
    	return this.userlist.get(username);
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Hashtable<String, MyDir> getUserlist() {
		return userlist;
	}
	
	public void setUserlist(Hashtable<String, MyDir> userlist) {
		this.userlist = userlist;
	}
	
	public Hashtable<String, String> getUser_Password() {
		return user_password;
	}
	
	public void setUser_Password(Hashtable<String, String> user_password) {
		this.user_password = user_password;
		
	}
	
}