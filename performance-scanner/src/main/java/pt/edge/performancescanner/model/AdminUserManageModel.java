package pt.edge.performancescanner.model;

import java.io.Serializable;
import java.util.List;

import pt.edge.performancescanner.domain.User;

public class AdminUserManageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899067155120046447L;
	private List<User> usersList;
	private List<User> usersDistinct;

	private List<User> filteredList;
	private String pageName= PerformanceScanner.getinstance().getMsgProperty("ADMIN_USER_PAGE");
	private User tempUser;
	
	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<User> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<User> filteredList) {
		this.filteredList = filteredList;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}


	public User getTempUser() {
		return tempUser;
	}

	public void setTempUser(User tempUser) {
		this.tempUser = tempUser;
	}

	public List<User> getUsersDistinct() {
		return usersDistinct;
	}

	public void setUsersDistinct(List<User> usersDistinct) {
		this.usersDistinct = usersDistinct;
	}
}
