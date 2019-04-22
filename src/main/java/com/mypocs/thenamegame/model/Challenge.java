/**
 * 
 */
package com.mypocs.thenamegame.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raj Thuppanna
 *
 */
@AllArgsConstructor
public class Challenge {

	
	@Getter @Setter private String challengeId;
	@Getter @Setter private ChallengeOption[] challengeOptions = new ChallengeOption[5];
	@Getter @Setter private String subjectLastName;
	@Getter @Setter private String subjectFirstName;
}
