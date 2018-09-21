package pt.edge.performancescanner.model;

import java.io.Serializable;

import pt.edge.performancescanner.domain.User;

public class ReportModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4448956948288107797L;

	private User currentUser;
	private String pageName= PerformanceScanner.getinstance().getMsgProperty("SUBMIT_REPORT");

	private String greetingMessage =PerformanceScanner.getinstance().getMsgProperty("GREETING_STRING");
	private String weekSelectionTag = PerformanceScanner.getinstance().getMsgProperty("WEEK_SELECTION");
	private String saveWeekTag = PerformanceScanner.getinstance().getMsgProperty("SAVE_WEEK_CHANGES");
	private String analyseText =PerformanceScanner.getinstance().getMsgProperty("ANALYSE");
	private String logoutText = PerformanceScanner.getinstance().getMsgProperty("LOGOUT");
	private String weekLabel;
	private String appName=PerformanceScanner.getinstance().getMsgProperty("APP_NAME");
	private int [] kpiValues;


	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}


	public int [] getKpiValues() {
		return kpiValues;
	}

	public void setKpiValues(int [] kpiValues) {
		this.kpiValues = kpiValues;
	}

	public String getWeekSelectionTag() {
		return weekSelectionTag;
	}

	public void setWeekSelectionTag(String weekSelectionTag) {
		this.weekSelectionTag = weekSelectionTag;
	}

	public String getGreetingMessage() {
		return greetingMessage;
	}

	public void setGreetingMessage(String greetingMessage) {
		this.greetingMessage = greetingMessage;
	}

	public String getAnalyseText() {
		return analyseText;
	}

	public void setAnalyseText(String analyseText) {
		this.analyseText = analyseText;
	}

	public String getLogoutText() {
		return logoutText;
	}

	public void setLogoutText(String logoutText) {
		this.logoutText = logoutText;
	}

	public String getSaveWeekTag() {
		return saveWeekTag;
	}

	public void setSaveWeekTag(String saveWeekTag) {
		this.saveWeekTag = saveWeekTag;
	}

	public String getWeekLabel() {
		return weekLabel;
	}

	public void setWeekLabel(String weekLabel) {
		this.weekLabel = weekLabel;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}


}


