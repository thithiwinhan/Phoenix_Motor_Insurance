package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.PolicyHolder;
@Repository
public interface PolicyHolderDao extends JpaRepository<PolicyHolder, Integer> {

}
