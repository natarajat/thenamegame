/**
 * 
 */
package com.mypocs.thenamegame.model;

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
public class ChallengeOption {
	
	@Getter @Setter private String profileId;
	@Getter @Setter private Headshot headshot;
		
}
