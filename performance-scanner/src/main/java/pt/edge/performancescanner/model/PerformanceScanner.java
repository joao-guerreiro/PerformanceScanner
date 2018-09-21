package pt.edge.performancescanner.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.web.context.annotation.SessionScope;

import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.utils.DateTools;
import pt.edge.performancescanner.utils.PropertiesSingleton;


@SessionScope
public class PerformanceScanner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1015653590865569044L;
	private static PerformanceScanner instance = new PerformanceScanner();
	private User userInSession;

	private PerformanceScanner() {
		super();
	}

	public static PerformanceScanner getinstance() {
		return instance;
	}

	public String getMsgProperty(String propname) {
		return PropertiesSingleton.getInstance().getMsgProperty(propname);
	}

	public String getAppProperty(String propname) {
		return PropertiesSingleton.getInstance().getAppProperty(propname);
	}

	public boolean checkLogin(String username, String password) {
		setUserInSession(new User(username, true));
		return true;
	}

	public boolean isAdmin(String username) {
		// try {
		// User user = DatabaseInfo.getUser(username);
		// }catch (UserDoesNotExistException e) {
		// return false
		// }
		return false;
	}

	public User getUserInSession() {
		return userInSession;
	}

	private void setUserInSession(User userInSession) {
		this.userInSession = userInSession;
	}

	public String getCurrentWeek() {
		return DateTools.getCurrentWeek();
	}

	public boolean isCurrentWeek(String reportWeek) {
		return DateTools.isCurrentWeek(reportWeek);
	}

	public String previousWeek(String reportWeek) {
		return DateTools.previousWeek(reportWeek);
	}

	public String nextWeek(String reportWeek) {
		return DateTools.nextWeek(reportWeek);
	}

	public void reportSubmit(Map<String, Integer> report) {
		// TODO: build
	}
}