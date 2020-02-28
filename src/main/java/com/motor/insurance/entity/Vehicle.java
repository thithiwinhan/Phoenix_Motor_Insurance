package com.motor.insurance.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

	@Column(name = "model")
	private String model;
	
	@Column(name = "manufacture_year")
	private int manufactureYear;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "chassis_no")
	private String chassisNo;
	
	

	/*
	 * @Column(name = "current_value") private Double currentValue;
	 */

	@Column(name = "enginecc")
	private String engineCC;
	
	@Column(name = "engine_no")
	private String engineNo;
	
	
	@Column(name = "vehicle_registration_no")
	private String vehicleRegNo;
	
	
	@Column(name = "vehicle_expired_date")
	private Date vehicleExpiredDate;
	

	@Column(name = "vehicle_make")
	private String vehicleMake;
	
	
	@Column(name = "engine_type")
	private String engineType;
	

	@Column(name = "body_type")
	private String bodyType;
	
	
	
	/*
	 * @OneToOne(mappedBy = "vehicle") private Proposal proposal;
	 */
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	
	public int getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	/*
	 * public Double getCurrentValue() { return currentValue; } public void
	 * setCurrentValue(Double currentValue) { this.currentValue = currentValue; }
	 */
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
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public Date getVehicleExpiredDate() {
		return vehicleExpiredDate;
	}
	public void setVehicleExpiredDate(Date vehicleExpiredDate) {
		this.vehicleExpiredDate = vehicleExpiredDate;
	}
	
	
	
	
	
	
}
