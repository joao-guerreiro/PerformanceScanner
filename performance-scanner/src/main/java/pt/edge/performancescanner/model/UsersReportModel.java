package pt.edge.performancescanner.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;

public class UsersReportModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8488738749506544741L;


	private String pageName= PerformanceScanner.getinstance().getMsgProperty("ADMIN_REPORT_PAGE");
	
	
	private List<Report> fullList;
    private List<Report> filteredtList;
	private String weekLabel;
	private Date currentDate;
	private List<User> userList;



    
	public List<Report> getFullList() {
		return fullList;
	}

	public void setFullList(List<Report> fullList) {
		this.fullList = fullList;
	}

	public List<Report> getFilteredtList() {
		return filteredtList;
	}

	public void setFilteredtList(List<Report> filteredtList) {
		this.filteredtList = filteredtList;
	}

	public String getWeekLabel() {
		return weekLabel;
	}

	public void setWeekLabel(String weekLabel) {
		this.weekLabel = weekLabel;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


}
