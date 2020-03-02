/*
 * package com.motor.insurance;
 * 
 * import java.util.ArrayList; import java.util.HashMap; import java.util.List;
 * 
 * import javax.annotation.PostConstruct; import
 * javax.faces.application.FacesMessage; import javax.faces.bean.ManagedBean;
 * import javax.faces.bean.ViewScoped; import javax.faces.context.FacesContext;
 * import javax.inject.Named;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.motor.insurance.entity.User; import
 * com.motor.insurance.model.ProposalModel; import
 * com.motor.insurance.service.ProposalListingService; import
 * com.motor.insurance.service.ProposalService; import
 * com.motor.insurance.service.VehicleService;
 * 
 * @ViewScoped
 * 
 * @Named public class ListenerBean {
 * 
 * double text=0.0; double num=0.0;
 * 
 * 
 * 
 * ProposalModel proposal = new ProposalModel(); // create propsal model object
 * for save proposal data from ui List<ProposalModel> proposalList = new
 * ArrayList<ProposalModel>(); // retrieve propsal data from database
 * 
 * @Autowired VehicleService vehicleService;
 * 
 * @Autowired ProposalService proposalService;
 * 
 * @Autowired ProposalListingService listingService;
 * 
 * HashMap<Integer, Integer> proposalIdList = new HashMap<Integer, Integer>();
 * //for show proposal list in combobox
 * 
 * 
 * // save propsal form according to user id,current set fixed user id 1 public
 * void proposalSave() {
 * 
 * to check same car is not to allow insurance >>>unique key of car is chassic
 * no and reg number
 * 
 * boolean flag = vehicleService.serachVehicleByChassicNoAndRegNum(proposal); if
 * (flag) { FacesContext context = FacesContext.getCurrentInstance();
 * 
 * context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
 * "Sorry" + "Your Car is already insurance", "")); } else {
 * System.out.println("user" + proposal.getpID()); System.out.println("arrive");
 * proposalService.save(proposal); } proposal = new ProposalModel();// to clear
 * form data }
 * 
 * 
 * 
 * 
 * get all proposal list by given user id , in current I set user id 1.
 * 
 * 
 * 
 * currenty block cmt for testing list of proposal id
 * 
 * 
 * @PostConstruct public void proposalList() {
 * 
 * 
 * User user = new User(); FacesContext facesContext =
 * FacesContext.getCurrentInstance(); HttpSession session = (HttpSession)
 * facesContext.getExternalContext().getSession(true); int id=(int)
 * session.getAttribute("id"); user.setId(id);
 * 
 * 
 * User user = new User(); user.setId(1); try { proposalList =
 * listingService.proposalListing(user);
 * 
 * boolean status = proposalService.findStatusbyProposalId(1);
 * System.out.println("staus=========================" + status); if
 * (!proposalList.isEmpty()) { proposalIdList = new HashMap<Integer, Integer>();
 * for (int i = 0; i < proposalList.size(); i++) {
 * this.proposalIdList.put(proposalList.get(i).getpID(),
 * proposalList.get(i).getpID()); } } }catch (IndexOutOfBoundsException e) {
 * e.printStackTrace(); } catch (NullPointerException e) { e.printStackTrace();
 * }
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * when click proposal submenu ,redirect proposal form
 * 
 * public String getProposalForm() { proposal = new ProposalModel(); // new obj
 * to save data return "proposal.xhtml?faces-redirect=true";
 * 
 * }
 * 
 * 
 * public void search() {
 * System.out.println("====Proposal Search Controller======"+proposal.getpID());
 * proposalList = new ArrayList<ProposalModel>(); if (proposal.getpID() != 0) {
 * // Proposal proposalEntiyList=new Proposal();
 * System.out.println("ID>>>>>>>>>>" + proposal.getpID()); this.proposalList =
 * listingService.findProposalById(proposal.getpID());
 * System.out.println(proposalList.get(0).getpHolderGender()); //proposal = new
 * ProposalModel(); }
 * 
 * else { User user = new User(); user.setId(1);
 * 
 * proposalList = listingService.proposalListing(user);
 * 
 * }
 * 
 * } public void cancel() { // proposalList = new ArrayList<ProposalModel>();
 * 
 * }
 * 
 * // save propsal form according to user id,current set fixed user id 1 public
 * void proposalDelete(ProposalModel model) {
 * 
 * 
 * User user = new User(); user.setId(1);
 * 
 * boolean deleteflag = proposalService.delete(model); this.proposalList =
 * listingService.proposalListing(user); }
 * 
 * public ProposalModel getProposal() { return proposal; }
 * 
 * public void setProposal(ProposalModel proposal) { this.proposal = proposal; }
 * 
 * public List<ProposalModel> getProposalList() { return proposalList; }
 * 
 * public void setProposalList(List<ProposalModel> proposalList) {
 * this.proposalList = proposalList; }
 * 
 * public HashMap<Integer, Integer> getProposalIdList() { return proposalIdList;
 * }
 * 
 * public void setProposalIdList(HashMap<Integer, Integer> proposalIdList) {
 * this.proposalIdList = proposalIdList; }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * double text1;
 * 
 * public double getText1() { return text1; } public void setText1(double text1)
 * { this.text1 = text1; }
 * 
 * 
 * 
 * public double getNum() { return num; }
 * 
 * public void setNum(double num) { this.num = num; }
 * 
 * public double getText() { return text; }
 * 
 * public void setText(double text) { this.text = text; }
 * 
 * public void ajaxEvent() { num=0;
 * 
 * num= text+2 +this.text1;
 * 
 * 
 * }
 * 
 * public void save() {
 * System.out.println("Listener bean>>>>>>>>>>>>>>>>>>>>>.."+num+"text"+text);
 * 
 * } }
 */