package com.motor.insurance.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.User;
@Repository
public interface UserDao extends CrudRepository<User, Integer>,JpaRepository<User, Integer>{


}
