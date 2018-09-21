package pt.edge.performancescanner.model;

import java.io.Serializable;
import java.util.ArrayList;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;

public class SingleReviewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1332989503975252164L;
	private ArrayList<Report> reportList;
	private User currentUser;
	private String pageName= PerformanceScanner.getinstance().getMsgProperty("MY_REPORT");

	public ArrayList<Report> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<Report> reportList) {
		this.reportList = reportList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
