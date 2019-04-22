/**
 * 
 */
package com.mypocs.thenamegame.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Raj Thuppanna
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ChallengeNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChallengeNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
