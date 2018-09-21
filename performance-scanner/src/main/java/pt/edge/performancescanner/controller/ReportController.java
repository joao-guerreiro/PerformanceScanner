
package pt.edge.performancescanner.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.model.PerformanceScanner;
import pt.edge.performancescanner.model.ReportModel;
import pt.edge.performancescanner.repository.ReportRepository;
import pt.edge.performancescanner.repository.UserRepository;



@Controller(value = "reportController")
@ViewScoped
public class ReportController implements Serializable {


	private static final long serialVersionUID = 8671204149131133641L;
	PerformanceScanner performanceScanner = PerformanceScanner.getinstance();
	private ReportModel model;
	private Date currentDate;
	private User currentUser;
	private Report currentRep;
	private String[] array;


	private int weekNumber() {
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int weekNum = c.get(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		String result = year+""+weekNum;
		return Integer.parseInt(result);
	}


	@Inject
	private UserRepository userRep;

	@Inject
	private ReportRepository reportRep;

	@PostConstruct
	public void init() {
		model = new ReportModel();
		currentUser =  userRep.findByUsername("joao");
		this.array=PerformanceScanner.getinstance().getAppProperty("KPI_NAMES").split(",");
		model.setKpiValues(new int[this.array.length]);
		currentDate = new Date();
		refreshCalendar();
	}



	private void retrieveWeekValues() {
		Report aux = reportRep.findByUserAndWeek(currentUser, weekNumber());
		if(aux!=null)
			currentRep = aux;
		else {
			currentRep = new Report();
			currentRep.setUser(currentUser);
			currentRep.setWeek(weekNumber());
		}
	}


	private void refreshCalendar() {
		Date start;
		Date end;
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		start = c.getTime();
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		end = c.getTime();
		currentDate = end;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		model.setWeekLabel(format.format(start)+" - "+format.format(end));
		retrieveWeekValues();
	}




	public void calculateWeeks(SelectEvent event) {
		Date start;
		try {
			start = (Date) event.getObject();
		}catch(NullPointerException e) {
			start = currentDate;
		}
		Date end;
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		start = c.getTime();
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		end = c.getTime();
		currentDate = end;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		model.setWeekLabel(format.format(start)+" - "+format.format(end));
		retrieveWeekValues();
	}

	public void reportSubmit() {
		currentRep.setUser(currentUser);
		reportRep.save(currentRep);
		refreshCalendar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Data has been updated"));
	}


	public void updateWeek(int mode) {
		if(mode==0) {
			//previous
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date start = c.getTime();
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			Date end = c.getTime();
			currentDate = end;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			model.setWeekLabel(format.format(start)+" - "+format.format(end));
		}
		else if (mode==1) {
			//next
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.WEEK_OF_MONTH, 1);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date start = c.getTime();
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			Date end = c.getTime();
			currentDate = end;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			model.setWeekLabel(format.format(start)+" - "+format.format(end));
		}
		retrieveWeekValues();
	}


	public ReportModel getModel() {
		return model;
	}


	public void setModel(ReportModel model) {
		this.model = model;
	}


	public String[] getArray() {
		return array;
	}


	public void setArray(String[] array) {
		this.array = array;
	}

	public Report getCurrentRep() {
		return currentRep;
	}


	public void setCurrentRep(Report currentRep) {
		this.currentRep = currentRep;
	}
}
