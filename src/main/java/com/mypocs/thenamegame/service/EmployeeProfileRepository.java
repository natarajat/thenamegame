package com.mypocs.thenamegame.service;

import org.springframework.data.repository.CrudRepository;

import com.mypocs.thenamegame.model.EmployeeProfile;

/**
 * 
 * @author Raj Thuppanna
 *
 */
public interface EmployeeProfileRepository extends CrudRepository<EmployeeProfile, String>{

}
