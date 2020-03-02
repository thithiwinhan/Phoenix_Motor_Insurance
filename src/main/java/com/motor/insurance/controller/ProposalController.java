package com.motor.insurance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.entity.Vehicle;
import com.motor.insurance.model.ProposalModel;
import com.motor.insurance.service.ClaimService;
import com.motor.insurance.service.ProposalListingService;
import com.motor.insurance.service.ProposalService;
import com.motor.insurance.service.VehicleService;
import com.motor.insurance.service.VehicleServiceImpl;

@Named
@ViewScoped
public class ProposalController {

	ProposalModel proposal = new ProposalModel(); // create propsal model object for save proposal data from ui
	List<ProposalModel> proposalList = new ArrayList<ProposalModel>(); // retrieve propsal data from database

	@Autowired
	VehicleService vehicleService;

	
	@Autowired
	ClaimService claimService;
	@Autowired
	ProposalService proposalService;
	
	
	@Autowired
	ProposalListingService listingService;

	HashMap<Integer, Integer> proposalIdList = new HashMap<Integer, Integer>(); // for show proposal list in combobox

	double premium;

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	
	public void ajaxEvent() {
		double sI = proposal.getSumInsure();
		premium = (sI / 100) * 1;

	}
	 
	
	
	/*
	 * public void ajaxEvent() { List<Vehicle> flag =
	 * vehicleService.serachVehicleByChassicNoAndRegNum(proposal); if
	 * (!flag.isEmpty()) { int count =
	 * claimService.countClaimNumber(flag.get(0).getId()); if(count>=2) { double sI
	 * = proposal.getSumInsure(); premium = (sI / 100) * 1; }
	 * 
	 * else if (count==0){ double sI = proposal.getSumInsure(); premium = (sI / 100)
	 * * 1; } }
	

	} */

	// save propsal form according to user id,current set fixed user id 1
	public void proposalSave() {
		/*
		 * to check same car is not to allow insurance >>>unique key of car is chassic
		 * no and reg number
		 */
		System.out.println("p9999999999999999999999999999roposal Save *************************************8888");
		List<Vehicle> flag = vehicleService.serachVehicleByChassicNoAndRegNum(proposal);
		if (!flag.isEmpty()||flag.size()>=1) {
			System.out.println("stage 1=======================");
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry" + "Your Car is already insurance", ""));
		} else {
			System.out.println("stage 1=======================");
			System.out.println("premium" + premium);
			System.out.println("pid" + proposal.getpID());
			System.out.println("arrive");
			proposal.setPremium(premium);// calcuated premuim based on Sum Issure
			proposalService.save(proposal);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Congradulation" + "Your proposal is successfully Saved", ""));
		}
		//proposal = new ProposalModel();// to clear form data
	}

	public void proposalList() {
		System.out.println("===========proposal List arrive=========");
		User user = new User();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		try {
			if (session!=null) {
			int id = (int) session.getAttribute("id");
			user.setId(id);
			try {
				System.out.println("9999999999999999999999999 ID"+user.getId());
				proposalList = listingService.proposalListing(user);
				   if (!proposalList.isEmpty()||proposalIdList.size()==0) {
					    proposalIdList = new HashMap<Integer, Integer>();
						for (int i = 0; i < proposalList.size(); i++) {
						this.proposalIdList.put(proposalList.get(i).getpID(), proposalList.get(i).getpID());
				        }
			       }
				   else {
					   proposalIdList= new HashMap<Integer, Integer>();
				   }
			} 
			catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
			}
		 }
		} catch (NullPointerException e) {
		}

	}

	/*
	 * when click proposal submenu ,redirect proposal form
	 */
	  public String getProposalForm(){
		   	System.out.println("*******getProposalForm()********");
		   proposal = new ProposalModel();
		 return "proposal.xhtml?faces-redirect=true";
			
		}

	
	  public String getProposalListForm() {
		  System.out.println("*******getProposalListForm()********");
		 proposalList();
		 return "proposalStatusChecking.xhtml?faces-redirect=true";
		
	}
	
	

	/*
	 * load proposal data into proposal view according to clicked proposal id when
	 * click edit button
	 */
	public String edit(ProposalModel model) {

		// before allow user to edit proposal , check status is pending
		boolean status = proposalService.findStatusbyProposalId(model.getpID());
		System.out.println("-----------edit Proposal ------------");
		System.out.println("-----------status Proposal is accept ?------------" + status);

		if (status == false) {

			System.out.println("---- for owner info reloading-----" + model.getpHolderID());
			proposal.setpID(model.getpID());
			proposal.setpHolderID(model.getpHolderID());
			proposal.setpHolderName(model.getpHolderName());
			proposal.setPholderAddress(model.getPholderAddress());
			proposal.setpHolderGender(model.getpHolderGender());
			proposal.setpHolderNrc(model.getpHolderNrc());
			proposal.setpHolderOccupation(model.getpHolderOccupation());
			proposal.setpHolderPh(model.getpHolderPh());
			proposal.setpHolderDob(model.getpHolderDob());
			System.out.println("---- for ownerID check-----" + proposal.getpHolderID());

			System.out.println("---- for proposal main info reloading-----");
			proposal.setPremium(premium);
			proposal.setCoverageType(model.getCoverageType());
			proposal.setSumInsure(model.getSumInsure());
			proposal.setStartDate(model.getStartDate());

			System.out.println("---- for Vehicle main info reloading-----" + model.getVehicleID());
			proposal.setVehicleID(model.getVehicleID());
			proposal.setVehicleExpiredDate(model.getVehicleExpiredDate());
			proposal.setVehicleMake(model.getVehicleMake());
			proposal.setVehicleRegNo(model.getVehicleRegNo());
			proposal.setChassisNo(model.getChassisNo());
			proposal.setColor(model.getChassisNo());
			proposal.setCurrentValue(model.getCurrentValue());
			proposal.setBodyType(model.getBodyType());
			proposal.setEngineCC(model.getEngineCC());
			proposal.setEngineNo(model.getEngineNo());
			proposal.setEngineType(model.getEngineType());
			proposal.setManufactureYear(model.getManufactureYear());
			proposal.setModel(model.getModel());

			System.out.println("---- for Benificial  info reloading-----" + model.getbID());
			proposal.setbID(model.getbID());
			proposal.setbName(model.getbName());
			proposal.setbNrc(model.getbNrc());
			proposal.setbPhone(model.getbPhone());
			proposal.setbAddress(model.getbAddress());

			System.out.println("---- for Driver  info reloading-----" + model.getdID());
			proposal.setdID(model.getdID());
			proposal.setdName(model.getdName());
			proposal.setdDrivingLicene(model.getdDrivingLicene());
			proposal.setdLiceneExpiredDate(model.getdLiceneExpiredDate());
			proposal.setdPhno(model.getdPhno());
			proposal.setdAddress(model.getdAddress());

			return "updateProposal.xhtml?faces-redirect=true";
		} else

			return "";

	}

	// After *update* button ;when they finished changes in ui ,update data
	public void updateProposal() {

		System.out.println("-------update------" + proposal.getpID());
		/*
		 * List<Vehicle> flag =
		 * vehicleService.serachVehicleByChassicNoAndRegNum(proposal); if
		 * (!flag.isEmpty()||flag.size()>=0) { FacesContext context =
		 * FacesContext.getCurrentInstance();
		 * 
		 * context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Sorry" + "Your Car is already insurance", "")); } else {
		 */
			proposal.setPremium(premium);
			proposalService.updateProduct(proposal);
			proposal = new ProposalModel();// to clear form data
			FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Update!", "info Messages"));
		
	}

	/*
	 * default nothing record is show: if user's input ==0 or just click search,show
	 * all data if user give proposal id , only related record is shown
	 */
	public void search() {
		System.out.println("====Proposal Search Controller======" + proposal.getpID());
		proposalList = new ArrayList<ProposalModel>();
		if (proposal.getpID() != 0) {
			// Proposal proposalEntiyList=new Proposal();
			System.out.println("ID>>>>>>>>>>" + proposal.getpID());
			this.proposalList = listingService.findProposalById(proposal.getpID());
			System.out.println(proposalList.get(0).getpHolderGender());
			// proposal = new ProposalModel();
		}
		else {
			User user = new User();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

			try {
				if (session!=null) {
					int id = (int) session.getAttribute("id");
					user.setId(id);

					try {
						proposalList = listingService.proposalListing(user);
					} catch (IndexOutOfBoundsException e) {
						proposalList= new ArrayList<ProposalModel>();
					}
				}
					else {
						System.out.println("session is null");
				}
			} catch (NullPointerException e) {
				System.out.println("session is null");
			}
			

		}

	}

	
	public String cancelUpdate() {
		 return "proposalStatusChecking.xhtml?faces-redirect=true";
		
	}
	public String cancel() {
      proposal= new ProposalModel();
		 return "proposal.xhtml?faces-redirect=true";
      
	}

	// save propsal form according to user id,current set fixed user id 1
	public void proposalDelete(ProposalModel model) {

		User user = new User();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		try {
			
			if (session != null) {
				int id = (int) session.getAttribute("id");
				user.setId(id);

				boolean deleteflag = proposalService.delete(model);
				if (deleteflag == true) {
					this.proposalList = listingService.proposalListing(user);
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Delete!!" + "", ""));
				} 
				else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry" + "Failed to delete", ""));
				}
			}
		} catch (NullPointerException e) {
			System.out.println("null in proposal delete");
		}
		
	}

	
	
	
	
	public ProposalModel getProposal() {
		return proposal;
	}

	public void setProposal(ProposalModel proposal) {
		this.proposal = proposal;
	}

	public List<ProposalModel> getProposalList() {
		return proposalList;
	}

	public void setProposalList(List<ProposalModel> proposalList) {
		this.proposalList = proposalList;
	}

	public HashMap<Integer, Integer> getProposalIdList() {
		return proposalIdList;
	}

	public void setProposalIdList(HashMap<Integer, Integer> proposalIdList) {
		this.proposalIdList = proposalIdList;
	}
}
