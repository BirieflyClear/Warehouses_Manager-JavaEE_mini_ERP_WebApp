// tutorial 1
package pl.kti.dbservlet;

public class LoggedUser {
	
	private int uID;
	private String username;
	private String password;
	private String function;
	
	public LoggedUser(int uID, String username, String password, String function) {
		
		this.uID = uID;
		this.username = username;
		this.password = password;
		this.function = function;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public String toString() {
		return "User [uID=" + uID + ", username=" + username + ", password=" + password + ", function=" + function
				+ "]";
	}
	

}
