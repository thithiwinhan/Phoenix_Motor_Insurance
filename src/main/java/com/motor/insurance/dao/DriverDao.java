package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.Driver;

@Repository
public interface DriverDao extends JpaRepository<Driver, Integer>{

}
