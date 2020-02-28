package com.motor.insurance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.BenificialDao;
import com.motor.insurance.dao.DriverDao;
import com.motor.insurance.dao.PolicyHolderDao;
import com.motor.insurance.dao.ProposalDao;
import com.motor.insurance.dao.UserDao;
import com.motor.insurance.dao.VehicleDao;
import com.motor.insurance.entity.Benificial;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.Driver;
import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.entity.Vehicle;
import com.motor.insurance.exception.ResourceNotFoundException;
import com.motor.insurance.model.ProposalModel;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleDao vehicleDao;
@Autowired
EntityManager em;
	
	
	@Override
	public boolean serachVehicleByChassicNoAndRegNum(ProposalModel proposal) {

			boolean flag = false;
			
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Vehicle> cq=cb.createQuery(Vehicle.class);
			Root<Vehicle> root=cq.from(Vehicle.class);
			
			
				Predicate p;
				//Predicate p2=cb.nequal(proposal.get("status"), "pending");
				Predicate p2;
				Predicate p3;
				try {
					p = cb.equal(root.get("chassisNo"),proposal.getChassisNo());
					p2 = cb.equal(root.get("vehicleRegNo"),proposal.getVehicleRegNo());
					
			/*
			 * we need to check status ,if deleted car is found , allow to insurace p3 =
			 * cb.equal(root.get("status"), 1);
			 */
				
				cq.where(cb.or(p,p2)).distinct(true);
			
				TypedQuery<Vehicle> typedQuery =em.createQuery(cq);
				List<Vehicle> resultList = typedQuery.getResultList();
				
		        if (resultList.isEmpty()||resultList.size()==0) {
					 flag=false;  // given chassic number and reg no is not found in db ;no duplicate vehicle
				}
		        else {
					flag= true; //given chassic number and reg  is not found in db :duplicate
				}
          
	}
	catch (IllegalArgumentException e) {
		System.out.println("stop Illegal!!!!!!!!!!!!!!!!");				
		e.printStackTrace();
						}
				
				return flag;
	

}
}


	




	

