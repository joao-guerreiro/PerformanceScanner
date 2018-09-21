package pt.edge.performancescanner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reports")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long id;

	@Column(name = "week")
	private int week;
	
	@Column(name = "meetings")
	private int meetings;
	
	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getMeetings() {
		return meetings;
	}

	public void setMeetings(int meetings) {
		this.meetings = meetings;
	}

	public int getQualifications() {
		return qualifications;
	}

	public void setQualifications(int qualifications) {
		this.qualifications = qualifications;
	}

	public int getMonitorings() {
		return monitorings;
	}

	public void setMonitorings(int monitorings) {
		this.monitorings = monitorings;
	}

	public int getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(int opportunities) {
		this.opportunities = opportunities;
	}

	@Column(name = "qualifications")
	private int qualifications;
	
	@Column(name = "monitorings")
	private int monitorings;
	
	@Column(name = "opportunities")
	private int opportunities;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Report() {

	}

	public Report(int week, int reunions, int qualifications, int monitorizations, int oportunities, User user) {
		super();
		this.week = week;
		this.meetings = reunions;
		this.qualifications = qualifications;
		this.monitorings = monitorizations;
		this.opportunities = oportunities;
		this.user = user;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", week=" + week + ", reunions=" + meetings + ", qualifications=" + qualifications
				+ ", monitorizations=" + monitorings + ", oportunities=" + opportunities + ", user=" + user + "]";
	}

}
