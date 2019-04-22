/**
 * 
 */
package com.mypocs.thenamegame.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Raj Thuppanna
 *
 */

@ToString
@AllArgsConstructor
public class ChallengeWithAnswer {
	
	@Getter @Setter private String challengeId;
	@Getter @Setter private ChallengeOption[] challengeOptions = new ChallengeOption[5];
	@Getter @Setter private String subjectProfileId;
	@Getter @Setter private String subjectLastName;
	@Getter @Setter private String subjectFirstName;
	
	/** start time of challenge - initialized to current time */
	@Getter @Setter private long challengeStartTime = System.currentTimeMillis();
	

	public ChallengeWithAnswer(String challengeId, ChallengeOption[] challengeOptions, String subjectProfileId,
			String subjectLastName, String subjectFirstName) {
		super();
		this.challengeId = challengeId;
		this.challengeOptions = challengeOptions;
		this.subjectProfileId = subjectProfileId;
		this.subjectLastName = subjectLastName;
		this.subjectFirstName = subjectFirstName;
	}
	
	public Challenge getChallenge() {
		return new Challenge(
				this.challengeId,
				this.challengeOptions,
				this.subjectLastName,
				this.subjectFirstName
				);
	}

}
