package pt.edge.performancescanner.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pt.edge.performancescanner.model.LoginModel;
import pt.edge.performancescanner.model.PerformanceScanner;
import pt.edge.performancescanner.repository.UserRepository;

@Controller(value = "loginController")
@ViewScoped
public class LoginController  implements Serializable {


	private static final long serialVersionUID = 995906980076374351L;
	private LoginModel model;
	PerformanceScanner performanceScanner = PerformanceScanner.getinstance();
	//	String loginText = performanceScanner.getMsgProperty("LOGIN_STRING");
	//	String waitText = performanceScanner.getMsgProperty("WAIT_STRING");


	@Inject
	private UserRepository repository;


	@PostConstruct
	private void onInit(){
		setModel(new LoginModel());
	}

	public LoginModel getModel() {
		return model;
	}



	public void setModel(LoginModel model) {
		this.model = model;
	}


	public void loginSubmit() {

	}


}
