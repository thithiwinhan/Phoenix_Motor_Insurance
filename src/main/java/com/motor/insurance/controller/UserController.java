package com.motor.insurance.controller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.motor.insurance.model.UserModel;
import com.motor.insurance.service.UserService;

@Named
@ViewScoped
public class UserController{

	private UserModel userModel = new UserModel();
	private List<UserModel> userList = new ArrayList<UserModel>();

	@Autowired
	public UserService userservice;

	/*
	 * @PostConstruct public void test() { if (userModel!=null) { boolean flag =
	 * userservice.searchByUserEmail(userModel);
	 * System.out.println("testing====================="+flag); } else
	 * System.out.println("null caught"); }
	 * 
	 */
	public String save() {
		System.out.println("===============Save=====================");
		userList = new ArrayList<UserModel>();
		boolean flag = userservice.searchByUserEmail(userModel);
		if (flag) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Email is already Exit ", "Failed to register"));
			return "";

		} else {
			if (userModel.getUserPassword().trim().equals(userModel.getUserConfirmPassword().trim())) {
				userservice.createUser(userModel);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Registration is successful ", "Welcome to Phonex Insurance"));
				return "home.xhtml?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password did not match ", "Try Again!"));
				return "";
			}

		}

	}

	public String userLogin() {
		System.out.println("arrivelogin!!!!!!!!!!!!");

		System.out.println("----login----" + userModel.getUserEmail());
		System.out.println("----password----" + userModel.getUserPassword());
		userList = new ArrayList<UserModel>();

		userList = userservice.checkAuthentication(userModel);

		if (userList.isEmpty()) {
			System.out.println("Empty list");

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failed!", "Try Again"));
			return "";

		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SuccessFul Login!", "info Messages"));
			System.out.println("success");

			System.out.println("---id---" + userList.get(0).getUserId());

			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("user", userList.get(0).getUserName());
			session.setAttribute("email", userList.get(0).getUserEmail());
			session.setAttribute("id", userList.get(0).getUserId());
			session.setAttribute("password", userList.get(0));
			System.out.println(session.getAttribute("user"));
			userModel = new UserModel();
			return "home.xhtml?faces-redirect=true";

		} 
		
	
	}
	public String logout() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			fc.getExternalContext().getSessionMap().clear();
			session.invalidate();
		} catch (ConcurrentModificationException e) {
			System.out.println(e);
		}
		return "login.xhtml?faces-redirect=true";

	}
	public String getProfile() {
		return "profile.xhtml?faces-redirect=true";
		
	}
	
	
	public void profile() {
		
	}

	public void userProfile() {
		userList = userservice.findUserById(userModel.getUserId());
	}

	public void userProfileUpdate() {
		userservice.updateProfile(userModel);
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

}
