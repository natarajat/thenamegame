/**
 * 
 */
package com.mypocs.thenamegame.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author Raj Thuppanna
 *	Overriding default implementation (that redirects to login page) by responding 
 *	with http response code UNAUTHORIZED
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// current request unauthorized
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
          "Unauthorized");
	}

}
