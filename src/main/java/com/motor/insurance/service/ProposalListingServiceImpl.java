package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.ProposalDao;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.exception.ResourceNotFoundException;
import com.motor.insurance.model.ProposalModel;

@Service
@Transactional
public class ProposalListingServiceImpl implements ProposalListingService {

		@Autowired
		EntityManager entityManager;

		@Autowired
		ProposalDao proposalDao;
		@Override
		public List<ProposalModel> proposalListing(User user) {
			
	        System.out.println("------------proposal Listing Dao -----------");
	        
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Proposal> cr = cb.createQuery(Proposal.class);
			Root<Proposal> root = cr.from(Proposal.class);
			System.out.println("----------user ID-----------"+user.getId());
			
			Predicate p = cb.equal(root.get("user"), user.getId());
			Predicate p2=cb.equal(root.get("active"), 1);
 
			
			cr.select(root).where(cb.and(p,p2));
			TypedQuery<Proposal> query = entityManager.createQuery(cr);
			List<Proposal> proposalList = new ArrayList<Proposal>();
			try {
				proposalList = query.getResultList();
				System.out.println("------------------Enity Return Proposal List-----------"+proposalList.size());

			} catch (NullPointerException e) {
                  System.out.println("NullPointerException");				
			}
			catch (NoResultException e) {
 				e.printStackTrace();
			}
			
			List<ProposalModel> pmodelList = new ArrayList<ProposalModel>();

			for (Proposal entity : proposalList) {
				System.out.println("------------------Enity Return Proposal List-----------"+pmodelList.size());
				ProposalModel model = new ProposalModel();
				
				System.out.println("---------------Proposal Main Info ------------");
				model.setpID(entity.getProposalId());
				model.setPremium(entity.getPremium());
				model.setSumInsure(entity.getSumInsure());
				model.setpID(entity.getProposalId());
				model.setStartDate(entity.getStartDate());
				model.setEndDate(entity.getEndDate());
				model.setStatus(entity.getStatus());
				model.setCoverageType(entity.getCoverageType());
				
				System.out.println("--------------- Holder Object ------------"+entity.getPolicyHolder().getName());
				model.setpHolderID(entity.getPolicyHolder().getId());
				model.setpHolderName(entity.getPolicyHolder().getName());
				model.setpHolderNrc(entity.getPolicyHolder().getNrc());
				model.setPholderAddress(entity.getPolicyHolder().getAddress());
				model.setpHolderDob(entity.getPolicyHolder().getDob());
				model.setpHolderGender(entity.getPolicyHolder().getGender());
				model.setpHolderOccupation(entity.getPolicyHolder().getOccupation());
				model.setpHolderPh(entity.getPolicyHolder().getPhno());
				
				System.out.println("--------------- Vehicle List ------------");
                model.setVehicleID(entity.getVehicle().getId());
				model.setVehicleExpiredDate(entity.getVehicle().getVehicleExpiredDate());
				model.setVehicleMake(entity.getVehicle().getVehicleMake());
				model.setVehicleRegNo(entity.getVehicle().getVehicleRegNo());
				model.setChassisNo(entity.getVehicle().getChassisNo());
				model.setColor(entity.getVehicle().getColor());
				//model.setCurrentValue(entity.getVehicle().getCurrentValue());
				model.setBodyType(entity.getVehicle().getBodyType());
				model.setEngineCC(entity.getVehicle().getEngineCC());
				model.setEngineNo(entity.getVehicle().getEngineNo());
				model.setEngineType(entity.getVehicle().getEngineType());
				//model.setManufactureYear(entity.getVehicle().getManufactureYear());
				model.setManufactureYear(entity.getVehicle().getManufactureYear());
				model.setModel(entity.getVehicle().getModel());
				
				try {
					System.out.println("--------------- Benificail List ------------");
					for (int i = 0; i < entity.getBenificials().size(); i++) {
						model.setbID(entity.getBenificials().get(i).getId());
						model.setbName(entity.getBenificials().get(i).getName());
						model.setbNrc(entity.getBenificials().get(i).getNrc());
						model.setbAddress(entity.getBenificials().get(i).getAddress());
						model.setbPhone(entity.getBenificials().get(i).getPhno());

						System.out.println("--------------- Benificail List ------------"+entity.getBenificials().get(i).getName());

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//model.setBenificials(entity.getBenificials());//return benificial list
				
				System.out.println("--------------- Driver List  ------------"+entity.getDrivers().size());
				
				try {
					for (int i = 0; i < entity.getDrivers().size(); i++) {
						System.out.println("arrive"+entity.getDrivers().get(i).getName());
						model.setdID(entity.getDrivers().get(i).getId());
						model.setdName(entity.getDrivers().get(i).getName());
						model.setdAddress(entity.getDrivers().get(i).getAddress());
						model.setdPhno(entity.getDrivers().get(i).getPhno());
						model.setdDrivingLicene(entity.getDrivers().get(i).getDrivingLicene());
						model.setdLiceneExpiredDate(entity.getDrivers().get(i).getLiceneExpiredDate());


					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//model.setDrivers(entity.getDrivers()); //return drivers list
				pmodelList.add(model);

			}

			return pmodelList;
		}

		

		@Override
		public List<ProposalModel> findProposalById(int getpID) {
			System.out.println("------------proposal Listing  search Dao -----------");
	        
			Optional<Proposal> proposalList = proposalDao.findById(getpID);

			//List<Proposal> proposalList = new ArrayList<Proposal>();
			
			List<ProposalModel> pmodelList = new ArrayList<ProposalModel>();

			
			if (proposalList.isPresent()) {
				      Proposal entity = proposalList.get();
				
				      
				      System.out.println("------------------Enity Return List for search id-----------");
						ProposalModel model = new ProposalModel();
						
						System.out.println("---------------Proposal Main Info  ------------");
						model.setpID(entity.getProposalId());
						model.setPremium(entity.getPremium());
						model.setSumInsure(entity.getSumInsure());
						model.setpID(entity.getProposalId());
						model.setStartDate(entity.getStartDate());
						model.setEndDate(entity.getEndDate());
						model.setStatus(entity.getStatus());
						model.setCoverageType(entity.getCoverageType());
						
						System.out.println("--------------- Holder Object ------------");
						model.setpHolderID(entity.getPolicyHolder().getId());
						model.setpHolderName(entity.getPolicyHolder().getName());
						model.setpHolderNrc(entity.getPolicyHolder().getNrc());
						model.setPholderAddress(entity.getPolicyHolder().getAddress());
						model.setpHolderDob(entity.getPolicyHolder().getDob());
						model.setpHolderGender(entity.getPolicyHolder().getGender());
						model.setpHolderOccupation(entity.getPolicyHolder().getOccupation());
						model.setpHolderPh(entity.getPolicyHolder().getPhno());
						
						System.out.println("--------------- Vehicle List ------------");
		                model.setVehicleID(entity.getVehicle().getId());
						model.setVehicleExpiredDate(entity.getVehicle().getVehicleExpiredDate());
						model.setVehicleMake(entity.getVehicle().getVehicleMake());
						model.setVehicleRegNo(entity.getVehicle().getVehicleRegNo());
						model.setChassisNo(entity.getVehicle().getChassisNo());
						model.setColor(entity.getVehicle().getColor());
					//	model.setCurrentValue(entity.getVehicle().getCurrentValue());
						model.setBodyType(entity.getVehicle().getBodyType());
						model.setEngineCC(entity.getVehicle().getEngineCC());
						model.setEngineNo(entity.getVehicle().getEngineNo());
						model.setEngineType(entity.getVehicle().getEngineType());
						model.setManufactureYear(entity.getVehicle().getManufactureYear());
						model.setModel(entity.getVehicle().getModel());
						
						try {
							System.out.println("--------------- Benificail List ------------");
							for (int i = 0; i < entity.getBenificials().size(); i++) {
								model.setbID(entity.getBenificials().get(i).getId());
								model.setbName(entity.getBenificials().get(i).getName());
								model.setbNrc(entity.getBenificials().get(i).getNrc());
								model.setbAddress(entity.getBenificials().get(i).getAddress());
								model.setbPhone(entity.getBenificials().get(i).getPhno());

								System.out.println("--------------- Benificail List ------------"+entity.getBenificials().get(i).getName());

							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//model.setBenificials(entity.getBenificials());//return benificial list
						
						System.out.println("--------------- Driver List  ------------"+entity.getDrivers().size());
						
						try {
							for (int i = 0; i < entity.getDrivers().size(); i++) {
								System.out.println("arrive"+entity.getDrivers().get(i).getName());
								model.setdID(entity.getDrivers().get(i).getId());
								model.setdName(entity.getDrivers().get(i).getName());
								model.setdAddress(entity.getDrivers().get(i).getAddress());
								model.setdPhno(entity.getDrivers().get(i).getPhno());
								model.setdDrivingLicene(entity.getDrivers().get(i).getDrivingLicene());
								model.setdLiceneExpiredDate(entity.getDrivers().get(i).getLiceneExpiredDate());


							}
						} catch (ArrayIndexOutOfBoundsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//model.setDrivers(entity.getDrivers()); //return drivers list
						pmodelList.add(model);

					} 
				      
				      
			else {
				 throw new ResourceNotFoundException(" Resource is not found given id");
			}
				      
				      
				      
				
			
				

			return pmodelList;
		}

		
		
		


	

	
	
	

}
