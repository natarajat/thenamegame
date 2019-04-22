/**
 * 
 */
package com.mypocs.thenamegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mypocs.thenamegame.model.Challenge;
import com.mypocs.thenamegame.model.ChallengeWithAnswer;
import com.mypocs.thenamegame.model.UserGuess;
import com.mypocs.thenamegame.model.UserStats;
import com.mypocs.thenamegame.model.ValidatedUserGuess;
import com.mypocs.thenamegame.service.ChallengeService;

/**
 * @author Raj Thuppanna
 *
 */
@RestController
@RequestMapping(value = "/v1") 
public class NameGameController {

	@Autowired 
	ChallengeService challengeService;
	
    @RequestMapping(value = "/challenge", produces = {"application/JSON"}, method=RequestMethod.GET)
    public ResponseEntity<Challenge> getChallenge()
    {
    	
    	Challenge challenge = challengeService.challenge();
        if (challenge == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(challenge);
        }
    }

    @RequestMapping(value = "/mattchallenge", produces = {"application/JSON"}, method=RequestMethod.GET)
    public ResponseEntity<Challenge> getMattChallenge()
    {
    	
    	ChallengeWithAnswer challengeWithAnswer = challengeService.mattChallenge();
        if (challengeWithAnswer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(challengeWithAnswer.getChallenge());
        }
    }

    @RequestMapping(value = "/guess", consumes = {"application/JSON"}, produces = {"application/JSON"},  method=RequestMethod.POST)
    public ResponseEntity<ValidatedUserGuess> postGuess(@RequestBody UserGuess userGuess)
    {
    	ValidatedUserGuess validatedGuess = challengeService.validateUserGuess(userGuess);
        if (validatedGuess == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(validatedGuess);
        }
    }

    @RequestMapping(value = "/sessionStats", produces = {"application/JSON"},  method=RequestMethod.GET)
    public ResponseEntity<UserStats> getSessionStats()
    {
    	 //= new ClientSelection();
    	UserStats stats = challengeService.sessionStats();
        if (stats == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(stats);
        }
    }

    @RequestMapping(value = "/userStats", produces = {"application/JSON"},  method=RequestMethod.GET)
    public ResponseEntity<UserStats> getUserStats()
    {
    	 //= new ClientSelection();
    	UserStats stats = challengeService.userStats();
        if (stats == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(stats);
        }
    }
    
   
}
