package com.motor.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.Proposal;
import com.motor.insurance.model.ProposalModel;

@Service
public interface VehicleService {

	boolean serachVehicleByChassicNoAndRegNum(ProposalModel proposal);

	

}
