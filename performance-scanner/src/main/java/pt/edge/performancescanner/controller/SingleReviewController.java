package pt.edge.performancescanner.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.model.SingleReviewModel;
import pt.edge.performancescanner.repository.ReportRepository;
import pt.edge.performancescanner.repository.UserRepository;

@Named(value = "singleReviewBean")
@ViewScoped
@Controller
public class SingleReviewController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1866238076115152251L;

	String greetingMessage = "Welcome, Test User";
	String appName = "Performance Scanner";
	String username = "Duarte Lopes";
	String analyseText = "Report";
	String logoutText = "Logout";





	private LineChartModel lineModel1;
	private SingleReviewModel model;

	@Inject
	private UserRepository userRep;

	@Inject
	private ReportRepository reportRep;

	@PostConstruct
	public void init() {
		model= new SingleReviewModel();
		startUserValues();
		try {
			createLineModels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startUserValues() {
		model.setCurrentUser(userRep.findByUsername("teste"));
		model.setReportList(new ArrayList<Report>());
		model.getReportList().addAll(reportRep.findByUserOrderByWeekDesc(model.getCurrentUser()));
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

	public void fill() {

		// TODO:
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("NewWeek.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void check() {
		// TODO:
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("WeekReview.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logout() {
		// TODO: Code for logout

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createLineModels() throws ParseException {
		lineModel1 = initLinearModel();
		lineModel1.setLegendPosition("e");
		lineModel1.setLegendPlacement(LegendPlacement.OUTSIDE);
		lineModel1.setLegendRows(4);
		lineModel1.setLegendCols(1);
		lineModel1.setAnimate(true);
		lineModel1.setShowDatatip(true);
		lineModel1.setShowPointLabels(true);

		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("Quantity");
		yAxis.setMin(0);


		DateAxis axis = new DateAxis();
		axis.setTickAngle(30);
		axis.setTickCount(10);
		lineModel1.getAxes().put(AxisType.X, axis);

	}

	private LineChartModel initLinearModel() throws ParseException {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		LineChartSeries series2 = new LineChartSeries();
		LineChartSeries series3 = new LineChartSeries();
		LineChartSeries series4 = new LineChartSeries();

		series1.setLabel("Meetings");
		series2.setLabel("Qualifications");
		series3.setLabel("Monitorings");
		series4.setLabel("Opportunities");


		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));


		for(Report r: this.model.getReportList()) {	
			String dateAsString = getFormatWeek(r); 
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter.setTimeZone(java.util.TimeZone.getTimeZone( "GMT" ) );

			Date dateFromString = formatter.parse(dateAsString);
			series1.set(dateFromString, r.getMeetings());
			series2.set(dateFromString, r.getQualifications());
			series3.set(dateFromString, r.getMonitorings());
			series4.set(dateFromString, r.getOpportunities());

		}

		model.addSeries(series1);
		model.addSeries(series2);
		model.addSeries(series3);
		model.addSeries(series4);

		return model;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public SingleReviewModel getModel() {
		return model;
	}

	public void setModel(SingleReviewModel model) {
		this.model = model;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}
}
