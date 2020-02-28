package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.Proposal;


@Repository
public interface ProposalListingDao extends JpaRepository<Proposal, Integer> {

}
