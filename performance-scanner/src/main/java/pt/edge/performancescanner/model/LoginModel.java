package pt.edge.performancescanner.model;

import java.io.Serializable;

public class LoginModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -855134335626917912L;
	private String username;
	private String password;
	private String appName = PerformanceScanner.getinstance().getMsgProperty("APP_NAME");


	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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
	
	
}
