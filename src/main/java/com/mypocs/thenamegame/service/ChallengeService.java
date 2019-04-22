/**
 * 
 */
package com.mypocs.thenamegame.service;

import com.mypocs.thenamegame.model.Challenge;
import com.mypocs.thenamegame.model.ChallengeWithAnswer;
import com.mypocs.thenamegame.model.UserGuess;
import com.mypocs.thenamegame.model.UserStats;
import com.mypocs.thenamegame.model.ValidatedUserGuess;

/**
 * @author Raj Thuppanna
 *
 */
public interface ChallengeService {

	public Challenge challenge();
	public ChallengeWithAnswer mattChallenge();
	public ValidatedUserGuess validateUserGuess(UserGuess clientResponse);
	public UserStats sessionStats();
	public UserStats userStats();
}
