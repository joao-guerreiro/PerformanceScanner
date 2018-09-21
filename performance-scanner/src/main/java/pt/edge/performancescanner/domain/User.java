package pt.edge.performancescanner.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "admin")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean admin;

	@Column(name = "active")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;

	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Report> reports;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User() {
	}

	public User(String username, boolean isAdmin) {
		this.admin = isAdmin;
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s', isAdmin='%s']", id, username, admin);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

}
