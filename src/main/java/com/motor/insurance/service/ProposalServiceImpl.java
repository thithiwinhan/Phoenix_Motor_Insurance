package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
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

@Transactional
@Service
public class ProposalServiceImpl implements ProposalService {

	@Autowired
	PolicyHolderDao policyHolderDao;

	@Autowired
	ProposalDao proposalDao;

	@Autowired
	BenificialDao benificailDao;

	@Autowired
	VehicleDao vehicleDao;

	@Autowired
	DriverDao driverDao;

	@Autowired
	UserDao userDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(ProposalModel proposal) {

		System.out.println("-------------user-----------");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		User user = new User();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		int id = (int) session.getAttribute("id");
		user.setId(id);

		/*
		 * User user = new User(); user.setName("hla");
		 * user.setEmail("thithi@gmail>com"); user.setPassword("324mdkfk");
		 * userDao.save(user);
		 */

		System.out.println("-------------Vehicle-----------");
		Vehicle vehicle = new Vehicle();
		vehicle.setBodyType(proposal.getBodyType());
		vehicle.setChassisNo(proposal.getChassisNo());
		vehicle.setColor(proposal.getColor());
		// vehicle.setCurrentValue(proposal.getCurrentValue());
		vehicle.setEngineCC(proposal.getEngineCC());
		vehicle.setEngineNo(proposal.getEngineNo());
		vehicle.setEngineType(proposal.getEngineType());
		vehicle.setManufactureYear(proposal.getManufactureYear());
		vehicle.setModel(proposal.getModel());
		vehicle.setVehicleExpiredDate(proposal.getVehicleExpiredDate());
		vehicle.setVehicleRegNo(proposal.getVehicleRegNo());
		vehicle.setVehicleMake(proposal.getVehicleMake());
		vehicleDao.save(vehicle);

		System.out.println("-------------PolicyHolder-----------");
		PolicyHolder policyHolder = new PolicyHolder();
		policyHolder.setName(proposal.getpHolderName());
		policyHolder.setAddress(proposal.getPholderAddress());
		policyHolder.setDob(proposal.getpHolderDob());
		policyHolder.setGender(proposal.getpHolderGender() == "Male" ? "M" : "F");
		policyHolder.setNrc(proposal.getpHolderNrc());
		policyHolder.setOccupation(proposal.getpHolderOccupation());
		policyHolder.setPhno(proposal.getpHolderPh());
		policyHolderDao.save(policyHolder);

		System.out.println("-------------proposal-----------");
		Proposal p = new Proposal();
		p.setCoverageType(proposal.getCoverageType());
		p.setCreateDate(new Date());
		p.setStartDate(proposal.getStartDate());

		// start End date Calculation
		Calendar cal = Calendar.getInstance();
		System.out.println("*********Start Date************" + p.getStartDate());
		cal.setTime(proposal.getStartDate());
		cal.add(Calendar.YEAR, 1);
		p.setEndDate(cal.getTime());
		// End of End date Calculation
		p.setStatus("pending");
		p.setPremium(proposal.getPremium());
		p.setSumInsure(proposal.getSumInsure());
		p.setActive(1);

		p.setVehicle(vehicle);
		p.setUser(user);
		p.setPolicyHolder(policyHolder);
		proposalDao.save(p);

		// start ! get today date and add 1 year to current date

		System.out.println("-------------Benificial-----------");
		Benificial benificial = new Benificial();
		benificial.setName(proposal.getbName());
		benificial.setNrc(proposal.getbNrc());
		benificial.setPhno(proposal.getbPhone());
		benificial.setAddress(proposal.getbAddress());
		benificial.setProposal(p);
		benificailDao.save(benificial);

		System.out.println("-------------Driver-----------");
		Driver driver = new Driver();
		driver.setName(proposal.getdName());
		driver.setAddress(proposal.getdAddress());
		driver.setDrivingLicene(proposal.getdDrivingLicene());
		driver.setPhno(proposal.getdPhno());
		driver.setLiceneExpiredDate(proposal.getdLiceneExpiredDate());
		driver.setProposal(p);
		driverDao.save(driver);

	}

	@Override
	public ProposalModel updateProduct(ProposalModel proposal) {
		Optional<Proposal> updateDB = this.proposalDao.findById(proposal.getpID());

		if (updateDB.isPresent()) {

			Proposal updateProposal = updateDB.get();
			System.out.println("********************************Update Proposal Dao*********************");

			System.out.println("-------------user-----------" + updateProposal.getUser().getId());
			User user = new User();
			user.setId(updateProposal.getUser().getId());
			user.setName(updateProposal.getUser().getName());
			user.setEmail(updateProposal.getUser().getEmail());
			user.setPassword(updateProposal.getUser().getPassword());
			userDao.save(user);
			userDao.save(user);

			System.out.println("-------------Vehicle-----------" + updateProposal.getVehicle().getId()
					+ "db<<<<>>>>>>ui" + proposal.getVehicleID());

			Vehicle vehicle = new Vehicle();
			vehicle.setId(updateProposal.getVehicle().getId());
			vehicle.setBodyType(proposal.getBodyType());
			vehicle.setChassisNo(proposal.getChassisNo());
			vehicle.setColor(proposal.getColor());
			// vehicle.setCurrentValue(proposal.getCurrentValue());
			vehicle.setEngineCC(proposal.getEngineCC());
			vehicle.setEngineNo(proposal.getEngineNo());
			vehicle.setEngineType(proposal.getEngineType());
			vehicle.setManufactureYear(proposal.getManufactureYear());
			vehicle.setModel(proposal.getModel());
			vehicle.setVehicleExpiredDate(proposal.getVehicleExpiredDate());
			vehicle.setVehicleRegNo(proposal.getVehicleRegNo());
			vehicle.setVehicleMake(proposal.getVehicleMake());
			updateProposal.setVehicle(vehicle);
			vehicleDao.save(vehicle);

			System.out.println("-------------PolicyHolder-----------" + updateProposal.getPolicyHolder().getId()
					+ "db>>>>>>>>>.ui" + proposal.getpHolderID());
			PolicyHolder policyHolder = new PolicyHolder();
			policyHolder.setId(updateProposal.getPolicyHolder().getId());
			policyHolder.setName(proposal.getpHolderName());
			policyHolder.setAddress(proposal.getPholderAddress());
			policyHolder.setDob(proposal.getpHolderDob());
			policyHolder.setGender(proposal.getpHolderGender() == "Male" ? "M" : "F");
			policyHolder.setNrc(proposal.getpHolderNrc());
			policyHolder.setOccupation(proposal.getpHolderOccupation());
			policyHolder.setPhno(proposal.getpHolderPh());
			updateProposal.setPolicyHolder(policyHolder);
			policyHolderDao.save(policyHolder);

			System.out.println("-------------proposal -----------" + updateProposal.getPolicyHolder().getId()
					+ "db>>>>>>>>>.ui" + proposal.getpHolderID());
			Proposal p = new Proposal();
			p.setProposalId(proposal.getpID());
			p.setCoverageType(proposal.getCoverageType());

			// start ! get today date and add 1 year to current date
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			p.setCreateDate(today);

			// start End date Calculation
			p.setStartDate(proposal.getStartDate());
			Calendar cal1 = Calendar.getInstance();
			System.out.println("*********Start Date************" + p.getStartDate());
			cal1.setTime(proposal.getStartDate());
			cal1.add(Calendar.YEAR, 1);
			p.setEndDate(cal1.getTime());
			// End of End date Calculation

			p.setStatus("pending");
			p.setPremium(proposal.getPremium());
			p.setSumInsure(proposal.getSumInsure());
			p.setActive(1);
			p.setVehicle(vehicle);
			p.setPolicyHolder(policyHolder);
			p.setUser(user);
			proposalDao.save(p);

			System.out.println("-------------Benificial-----------");
			// System.out.println("-------------Benificial-----------"+updateProposal.getBenificials()+"db>>>>>>>>>.ui"+proposal.getbID());
			Benificial benificial = new Benificial();
			List<Benificial> benificialList = new ArrayList<Benificial>();
			benificial.setId(proposal.getbID());
			benificial.setName(proposal.getbName());
			benificial.setNrc(proposal.getbNrc());
			benificial.setPhno(proposal.getbPhone());
			benificial.setAddress(proposal.getbAddress());
			benificial.setProposal(p);
			benificialList.add(benificial);
			updateProposal.setBenificials(benificialList);
			benificailDao.save(benificial);

			System.out.println("-------------Driver-----------");

			// System.out.println("-------------Driver-----------"+updateProposal.getDrivers().get(0).getId()+"db>>>>>>>>>.ui"+proposal.getdID());
			Driver driver = new Driver();
			List<Driver> driverList = new ArrayList<Driver>();
			driver.setId(proposal.getdID());
			driver.setName(proposal.getdName());
			driver.setAddress(proposal.getdAddress());
			driver.setDrivingLicene(proposal.getdDrivingLicene());
			driver.setPhno(proposal.getdPhno());
			driver.setLiceneExpiredDate(proposal.getdLiceneExpiredDate());
			driver.setProposal(p);
			driverList.add(driver);
			updateProposal.setDrivers(driverList);

			driverDao.save(driver);

			return proposal;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + proposal.getpID());
		}

	}

//check if status pending  according to given pid , if pending is found in proposal table  ,return true
	@Override
	public boolean findStatusbyProposalId(int propodalID) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Proposal> cq = cb.createQuery(Proposal.class);
		System.out.println("---------proposal status Repo-------------" + propodalID);
		Root<Proposal> proposal = cq.from(Proposal.class);

		Predicate p = cb.equal(proposal.get("proposalId"), propodalID);
		// Predicate p2=cb.nequal(proposal.get("status"), "pending");
		Predicate p2 = cb.notEqual(proposal.get("status"), "pending");
		Predicate p3 = cb.notEqual(proposal.get("status"), "reject");

		Predicate p4 = cb.equal(proposal.get("active"), 1);

		cq.where(cb.and(p, p2, p3, p4)).distinct(true);

		TypedQuery<Proposal> typedQuery = entityManager.createQuery(cq);
		List<Proposal> resultList = typedQuery.getResultList();

		if (resultList.isEmpty() || resultList.size() == 0) {
			return false;
		}

		else {
			return true;
		}

	}

	@Override
	public Proposal findProposalById(int proposalId) {
		Optional<Proposal> entity = proposalDao.findById(proposalId);
		Proposal p = new Proposal();

		if (entity.isPresent()) {

			p = entity.get();
		} else
			System.out.println(" Resource is not found");
		return p;
	}

	@Override
	public boolean delete(ProposalModel proposal) {
		boolean delFlag = false;
		Optional<Proposal> updateDB = this.proposalDao.findById(proposal.getpID());
		System.out.println("delete id----------------" + proposal.getpID());
		if (updateDB.isPresent()) {
			Proposal deleteProposal = updateDB.get();
			deleteProposal.setActive(0);
			proposalDao.saveAndFlush(deleteProposal);
			delFlag = true;

		} else {
			throw new ResourceNotFoundException("Record not found with id : ");
		}
		return delFlag;

	}

//email service for notification for propoal accepting for active user

	@Override
	public List<PolicyHolder> findAllProposaLOwnerActive() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PolicyHolder> cq = cb.createQuery(PolicyHolder.class);
		Root<Proposal> proposal = cq.from(Proposal.class);
		Join<Proposal, PolicyHolder> policyHolder = proposal.join("policyHolder");

		Predicate p1 = cb.equal(proposal.get("active"), 1);
		Predicate p2 = cb.equal(proposal.get("status"), "accept");

		cq.select(policyHolder).where(cb.and(p1, p2));
		TypedQuery<PolicyHolder> tq = entityManager.createQuery(cq);
		List<PolicyHolder> pHolderList = new ArrayList<PolicyHolder>();
		pHolderList = tq.getResultList();

		if (pHolderList.isEmpty() || pHolderList.size() <= 0) {
			System.out.println("empty of phoder that is accept");
		}

		else {
			return pHolderList;
		}
		return pHolderList;

	}

	@Override
	public String findId() {

		StringBuilder sb = new StringBuilder();
		System.out.println("1");

		System.out.println("2");
		// TypedQuery<String> qe=em.createNamedQuery(sb);

		System.out.println("3");
		String id = null;
		try {
			sb.append("SELECT MAX(p.id) FROM proposal p ");
			TypedQuery<String> q = entityManager.createQuery(sb.toString(), String.class);
			id = q.getSingleResult();
		} catch (NoResultException nre) {
			id = "p";
			System.out.println("---ERROR1----");

		} catch (Exception e) {
			id = "p";
			System.out.println("----ERROR1----");
		}

		return id;

	}
}
