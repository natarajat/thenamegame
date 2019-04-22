/**
 * 
 */
package com.mypocs.thenamegame.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Raj Thuppanna
 *
 */
public class ValidatedUserGuess {
	@Getter @Setter String challengeId;
	@Getter @Setter String selectedProfileId;
	@Getter @Setter boolean isAttemptSuccessful;

	public ValidatedUserGuess(UserGuess clientResponse) {
		this.challengeId = clientResponse.challengeId;
		this.selectedProfileId = clientResponse.selectedProfileId;
	}
	
}
