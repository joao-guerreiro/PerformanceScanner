package pt.edge.performancescanner.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.model.AdminUserManageModel;
import pt.edge.performancescanner.model.PerformanceScanner;
import pt.edge.performancescanner.model.ReportModel;
import pt.edge.performancescanner.repository.ReportRepository;
import pt.edge.performancescanner.repository.UserRepository;

@Named(value = "adminUserManageController")
@ViewScoped
public class AdminUserManageController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7301071310745134657L;

	private AdminUserManageModel model;


	public AdminUserManageModel getModel() {
		return model;
	}

	public void setModel(AdminUserManageModel model) {
		this.model = model;
	}

	@Inject
	private UserRepository userRep;

	@Inject
	private ReportRepository reportRep;

	@PostConstruct
	public void init() {
		model = new AdminUserManageModel();
		model.setUsersList(new ArrayList<User>());
		model.setFilteredList(new ArrayList<User>());
		model.setTempUser(new User());
		model.getUsersList().addAll((Collection<? extends User>) userRep.findAll());
		model.setUsersDistinct(new ArrayList<User>());
		model.getUsersDistinct().addAll(model.getUsersList().stream().distinct().collect(Collectors.toList()));
	}

	public void addNewUser() {
		Random random = new Random();
		User aux = new User();
		aux.setId(-random.nextLong());
		model.getUsersList().add(0,aux);
	}


	public void saveUsers() {
		if(model.getUsersList()==null)
			model.setUsersList(new ArrayList<User>());

		for(User us: model.getUsersList()) {
			if(us.getId()==null || us.getId()<0)
				us.setId(null);
			userRep.save(us);
		}
	}

	public void deleteUser(User us) {
		try {
			if(us.getId()>0)
				userRep.delete(us);
			model.getUsersList().remove(us);
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}




}
