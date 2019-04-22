/**
 * 
 */
package com.mypocs.thenamegame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Raj Thuppanna
 *
 */
@AllArgsConstructor
public class ErrorResponse {

	@Getter private String errorCode;
	@Getter private String errorMessage;
}
