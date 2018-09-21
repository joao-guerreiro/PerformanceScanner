package pt.edge.performancescanner.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.event.SelectEvent;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.model.PerformanceScanner;
import pt.edge.performancescanner.model.ReportModel;
import pt.edge.performancescanner.model.UsersReportModel;
import pt.edge.performancescanner.repository.ReportRepository;
import pt.edge.performancescanner.repository.UserRepository;

@Named(value = "usersReportController")
@ViewScoped
public class UsersReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984578084023006353L;

	/**
	 * 
	 */


	private UsersReportModel model;

	public UsersReportModel getModel() {
		return model;
	}

	public void setModel(UsersReportModel model) {
		this.model = model;
	}




	@Inject
	private UserRepository userRep;

	@Inject
	private ReportRepository reportRep;

	@PostConstruct
	public void init() {
		model = new UsersReportModel();
		model.setFullList(new ArrayList<Report>());
		model.setFilteredtList(new ArrayList<Report>());

		model.getFullList().addAll((Collection<? extends Report>) reportRep.findAll());
		model.setUserList(new ArrayList<User>());
		model.getUserList().addAll(model.getFullList().stream().map(x->x.getUser()).distinct().collect(Collectors.toList()));
	}

	public String getFormatWeek(Report rep) {
		// format date: dd/mm
		int a = rep.getWeek() / 100;
		int b = rep.getWeek() % 100;


		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,a);
		c.set(Calendar.WEEK_OF_YEAR, b); 
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		return format.format(c.getTime());
	}


	public void calculateWeeks(SelectEvent event) {
		Date start;
		try {
			start = (Date) event.getObject();

		}catch(NullPointerException e) {
			start = model.getCurrentDate();
		}

		Date end;

		Calendar c = Calendar.getInstance();
		c.setTime(start);

		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		start = c.getTime();
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		end = c.getTime();

		model.setCurrentDate(end);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		model.setWeekLabel(format.format(start)+" - "+format.format(end));
	}

	
	  public boolean filterByDate(Object value, Object filter, Locale locale) {
	        String filterText = (filter == null) ? null : filter.toString().trim();
	        if(filterText == null||filterText.equals("")) {
	            return true;
	        }
	         
	        if(value == null) {
	            return false;
	        }
	         
	        Calendar c = Calendar.getInstance();
			c.setTime((Date) filter);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			int week = c.get(Calendar.WEEK_OF_YEAR);
			int year = c.get(Calendar.YEAR);
			
			String result = year+""+week;

			
	       
	        return ((Comparable) value).compareTo(Integer.valueOf(result)) == 0;
	    }
	
	
}
