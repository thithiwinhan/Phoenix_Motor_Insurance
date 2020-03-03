package com.motor.insurance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.motor.insurance.entity.Benificial;
import com.motor.insurance.entity.Driver;

import lombok.Data;

@Data
public class ProposalModel {
	
	
	private int pID;
	
	//policy holder info
	private int pHolderID;
	private String pHolderName;
	private String pHolderNrc;
	private Date pHolderDob;
	private String pHolderPh;
	private String pHolderGender;
	private String pholderAddress;
	private String pHolderOccupation;
	private String pHolderEmail;

	//proposal main info
	private Double sumInsure;
	private String status;
	private String coverageType;
	private Double premium;
	private Date startDate;
	private Date endDate;
	private Date createDate;
	
	
	//vehicle info
	private int vehicleID;
	private String model;
	private int manufactureYear;
	private String color;
	private String chassisNo;
	private Double currentValue;
	private String engineCC;
	private String engineNo;
	private String vehicleRegNo;
	private Date vehicleExpiredDate;
	private String vehicleMake;
	private String engineType;
	private String bodyType;
	
	


	//Beneficial detail
	
	private int bID;
	private String bName;
	private String bPhone;
	private String bNrc;
	private String bAddress;
	
	private List<Benificial> benificials = new  ArrayList<Benificial>();
	
	public List<Benificial> getBenificials() {
		return benificials;
	}
	public void setBenificials(List<Benificial> benificials) {
		this.benificials = benificials;
	}
	
	//driver detail
	private int dID;
	private String dName;
	private String dAddress;
	private String dDrivingLicene;
	private String dPhno;
	private Date dLiceneExpiredDate;
	private List<Driver> drivers = new  ArrayList<Driver>();
	
	public List<Driver> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	
	
	/*
	 * policy holder getter/setter
	 * 
	 */
	public int getpHolderID() {
		return pHolderID;
	}
	public void setpHolderID(int pHolderID) {
		this.pHolderID = pHolderID;
	}

	public String getpHolderNrc() {
		return pHolderNrc;
	}
	public String getpHolderName() {
		return pHolderName;
	}
	public void setpHolderName(String pHolderName) {
		this.pHolderName = pHolderName;
	}
	public void setpHolderNrc(String pHolderNrc) {
		this.pHolderNrc = pHolderNrc;
	}
	public Date getpHolderDob() {
		return pHolderDob;
	}
	public void setpHolderDob(Date pHolderDob) {
		this.pHolderDob = pHolderDob;
	}
	public String getpHolderPh() {
		return pHolderPh;
	}
	public void setpHolderPh(String pHolderPh) {
		this.pHolderPh = pHolderPh;
	}
	public String getpHolderGender() {
		return pHolderGender;
	}
	public void setpHolderGender(String pHolderGender) {
		this.pHolderGender = pHolderGender;
	}
	public String getPholderAddress() {
		return pholderAddress;
	}
	public void setPholderAddress(String pholderAddress) {
		this.pholderAddress = pholderAddress;
	}
	public String getpHolderOccupation() {
		return pHolderOccupation;
	}
	public void setpHolderOccupation(String pHolderOccupation) {
		this.pHolderOccupation = pHolderOccupation;
	}
	
	public String getpHolderEmail() {
		return pHolderEmail;
	}
	public void setpHolderEmail(String pHolderEmail) {
		this.pHolderEmail = pHolderEmail;
	}

	
	
	/*
	 * proposal getter/setter
	 * 
	 */
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public Double getSumInsure() {
		return sumInsure;
	}
	public void setSumInsure(Double sumInsure) {
		this.sumInsure = sumInsure;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/*
	 * vehicle getter/setter
	 * 
	 */
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	public Double getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	public String getEngineCC() {
		return engineCC;
	}
	public void setEngineCC(String engineCC) {
		this.engineCC = engineCC;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public Date getVehicleExpiredDate() {
		return vehicleExpiredDate;
	}
	public void setVehicleExpiredDate(Date vehicleExpiredDate) {
		this.vehicleExpiredDate = vehicleExpiredDate;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	
	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	
	
	/*
	 * benificial getter/setter
	 * 
	 */
	public int getbID() {
		return bID;
	}
	public void setbID(int bID) {
		this.bID = bID;
	}

	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbPhone() {
		return bPhone;
	}
	public void setbPhone(String bPhone) {
		this.bPhone = bPhone;
	}
	public String getbNrc() {
		return bNrc;
	}
	public void setbNrc(String bNrc) {
		this.bNrc = bNrc;
	}
	public String getbAddress() {
		return bAddress;
	}
	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}
	
	
	

	/*
	 * driver getter/setter
	 * 
	 */
	public int getdID() {
		return dID;
	}
	public void setdID(int dID) {
		this.dID = dID;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	
	public String getdAddress() {
		return dAddress;
	}
	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}
	public String getdDrivingLicene() {
		return dDrivingLicene;
	}
	public void setdDrivingLicene(String dDrivingLicene) {
		this.dDrivingLicene = dDrivingLicene;
	}
	public String getdPhno() {
		return dPhno;
	}
	public void setdPhno(String dPhno) {
		this.dPhno = dPhno;
	}
	public Date getdLiceneExpiredDate() {
		return dLiceneExpiredDate;
	}
	public void setdLiceneExpiredDate(Date dLiceneExpiredDate) {
		this.dLiceneExpiredDate = dLiceneExpiredDate;
	}
	
	
	



	
	
	











	





	
	
	
	

}
