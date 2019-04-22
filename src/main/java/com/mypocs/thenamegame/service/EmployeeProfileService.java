/**
 * 
 */
package com.mypocs.thenamegame.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.mypocs.thenamegame.model.EmployeeProfile;


/**
 * @author Raj Thuppanna
 * Responsible for fetching Employee profiles + CRUD operations
 *	Ideally Profiles should be in database. They are locally cashed for quick development
 */
@Repository
public class EmployeeProfileService {

	Logger logger = LoggerFactory.getLogger(EmployeeProfileService.class);
	
	private static List<EmployeeProfile> employeeProfileCache = null;

	private static final String MATT_SEARCH_KEY = "MAT";

	@Value( "${employee.profile.source}" )
	 private String profileSource;
	
	 @PersistenceContext
	 private EntityManager entityManager;
	
	public List<EmployeeProfile> profiles()
	{
		if(EmployeeProfileService.employeeProfileCache == null) {
			this.downloadCacheEmployeeProfiles();
		}
		
		if(EmployeeProfileService.employeeProfileCache == null
				|| EmployeeProfileService.employeeProfileCache.isEmpty())
		{
			throw new ProfileNotFoundException("Employee profiles not found.");
		}
		
		// Keep original list private
		return new ArrayList<>(EmployeeProfileService.employeeProfileCache);
	}
	
	public List<EmployeeProfile> mattProfiles()
	{
		
		List<EmployeeProfile> allProfiles = profiles();
		return (List<EmployeeProfile>)allProfiles.stream()
				.filter((profile) -> 
					profile.getFirstName().toUpperCase().contains(MATT_SEARCH_KEY)
					|| profile.getLastName().toUpperCase().contains(MATT_SEARCH_KEY))
				.collect(Collectors.toList());
		
	}
	
	
	/**
	 * 
	 */
	private void downloadCacheEmployeeProfiles() {
		List<EmployeeProfile> profiles = downloadProfiles();
		EmployeeProfileService.employeeProfileCache = Collections.synchronizedList(profiles);
	}

	/**
	 * Download profiles from 
	 * @return
	 */
	private List<EmployeeProfile> downloadProfiles(){
		RestTemplate restTemplate = new RestTemplate();
		EmployeeProfile[] employeeProfiles = null;
		try{
			employeeProfiles = restTemplate.getForObject(profileSource, EmployeeProfile[].class);
			return Arrays.asList(employeeProfiles);
		}
		catch(RestClientResponseException ex) {
			logger.error("Error while downloading profiles: ", ex);
		}
		return null;
	}
	
/*	
	@Autowired
	EmployeeProfileRepository profileRepository;
	
	@Autowired
	HeadshotRepository headshotRepository;

	public void save(List<EmployeeProfile> empProfileList) {
		entityManager.persist(empProfileList);
	}
	
	
	public void save(EmployeeProfile profile) {
		headshotRepository.save(profile.getHeadshot());
		profileRepository.save(profile);
	}
	
	
	
	public void downloadAndSaveProfiles() {
		EmployeeProfile[] profiles = getProfiles();
		for(EmployeeProfile profile : profiles) {
			save(profile);
		}
	}
*/	

	
}
