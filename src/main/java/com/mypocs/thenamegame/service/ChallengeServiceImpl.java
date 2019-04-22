/**
 * 
 */
package com.mypocs.thenamegame.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mypocs.thenamegame.model.Challenge;
import com.mypocs.thenamegame.model.ChallengeOption;
import com.mypocs.thenamegame.model.ChallengeWithAnswer;
import com.mypocs.thenamegame.model.EmployeeProfile;
import com.mypocs.thenamegame.model.UserGuess;
import com.mypocs.thenamegame.model.UserStats;
import com.mypocs.thenamegame.model.ValidatedUserGuess;

/**
 * @author Raj Thuppanna
 * Responsible for handling the game logic
 *
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	EmployeeProfileService profileService;
	
	@Autowired
	CachingService cachingService;
	
	
	/**
	 * 	Answer next challenge 
	 */
	public Challenge challenge() {

		ChallengeWithAnswer challengeWithAnswer = this.generateRandomChallengeWithAnswer();
		cachingService.setCurrentChallenge(challengeWithAnswer);
		return challengeWithAnswer.getChallenge();
	}

	/**
	 * 	Answer next Matt challenge 
	 */
	public ChallengeWithAnswer mattChallenge() {

		ChallengeWithAnswer challengeWithAnswer = this.generateRandomMattChallengeWithAnswer();
		cachingService.setCurrentChallenge(challengeWithAnswer);
		return challengeWithAnswer;
	}

	
	/**
	 * Validate response to the challenge, update stats and answer if client waas correct (ValidatedClientResponse)
	 */
	@Override
	public ValidatedUserGuess validateUserGuess(UserGuess userGuess) {
		Long responseReceivedTime = System.currentTimeMillis();
		
		ChallengeWithAnswer challenegWithAnswer = this.cachingService.getCurrentChallenge();
		
		if(challenegWithAnswer == null) {
			throw new ChallengeNotFound("Challenge not found for user guess.");
		}
		
		ValidatedUserGuess validatedClientResponse = new ValidatedUserGuess(userGuess);
		
		long timeTakenToRespond = responseReceivedTime - challenegWithAnswer.getChallengeStartTime();
		boolean isClientSelectionCorrect = challenegWithAnswer.getSubjectProfileId().equals(userGuess.getSelectedProfileId());
		validatedClientResponse.setAttemptSuccessful(isClientSelectionCorrect);
		
		UserStats sessionStats = this.cachingService.getSessionStats();
		UserStats userStats = this.cachingService.getUserStats(SecurityContextHolder.getContext().getAuthentication().getName());

		// Handling concurrency on User stats (shared across sessions)
		synchronized(userStats){
			sessionStats.updateStats(isClientSelectionCorrect, timeTakenToRespond );
			userStats.updateStats(isClientSelectionCorrect, timeTakenToRespond );
			this.cachingService.setCurrentChallenge(null);
			this.cachingService.setSessionStats(sessionStats);
			this.cachingService.setUserStats(SecurityContextHolder.getContext().getAuthentication().getName(), userStats);
		}
				
		return validatedClientResponse;
	}	

	/**
	 * 	Answer user stats for current session
	 */
	public UserStats sessionStats() {
		return this.cachingService.getSessionStats();
	}
	
	/**
	 * Answer user stats 
	 */
	public UserStats userStats() {
		return this.cachingService.getUserStats(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	/**
	 * Create a Name game challenge with the answer
	 * @return
	 */
	private ChallengeWithAnswer generateRandomChallengeWithAnswer() {
		
		ChallengeOption[] challengeOptions =  new ChallengeOption[6];
		Random rand = new Random(); 
		int randomIndexOfSubject = rand.nextInt(6);
		
		List<EmployeeProfile> profiles =  this.identifyProfilesForChallenge(profileService.profiles());
				
		for(int index = 0; index < profiles.size(); index++) {
			challengeOptions[index] = 
					new ChallengeOption(
						profiles.get(index).getId(),
						profiles.get(index).getHeadshot()
					);
		}
		
		ChallengeWithAnswer challenge = 
				new ChallengeWithAnswer(
						UUID.randomUUID().toString(),
						challengeOptions,
						challengeOptions[randomIndexOfSubject].getProfileId(),
						profiles.get(randomIndexOfSubject).getLastName(),
						profiles.get(randomIndexOfSubject).getFirstName()
						);
		
		return challenge;
	}

	private ChallengeWithAnswer generateRandomMattChallengeWithAnswer() {
		ChallengeOption[] challengeOptions =  new ChallengeOption[6];
		Random rand = new Random(); 
		int randomIndexOfSubject = rand.nextInt(6);
		
		List<EmployeeProfile> profiles =  this.identifyProfilesForChallenge(profileService.mattProfiles());
				
		for(int index = 0; index < profiles.size(); index++) {
			challengeOptions[index] = 
					new ChallengeOption(
						profiles.get(index).getId(),
						profiles.get(index).getHeadshot()
					);
		}
		
		ChallengeWithAnswer challenge = 
				new ChallengeWithAnswer(
						UUID.randomUUID().toString(),
						challengeOptions,
						challengeOptions[randomIndexOfSubject].getProfileId(),
						profiles.get(randomIndexOfSubject).getLastName(),
						profiles.get(randomIndexOfSubject).getFirstName()
						);
		
		return challenge;
		
	}
	
	/**
	 * Identify and return 6 unique profiles to be used in a challenge
	 * @return A list of 6 EmployeeProfiles
	 */
	private List<EmployeeProfile> identifyProfilesForChallenge(List<EmployeeProfile> profiles) {
		
		if(profiles.size() < 6) {
			throw new ProfileNotFoundException("Not enough employee profiles to form a challenge.");
		}
		Random rand = new Random(); 
		Set<EmployeeProfile> selectedProfiles = new HashSet<EmployeeProfile>();

		// Chose 6 unique profiles
		while(selectedProfiles.size() < 6) {
			selectedProfiles.add( profiles.get( rand.nextInt(profiles.size()) ) );
		}
		return new ArrayList<EmployeeProfile>(selectedProfiles);
	}


}
