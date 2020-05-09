package com.techelevator.controller;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.ChatMessage;
import com.techelevator.model.Chatbot;
import com.techelevator.model.Email;
import com.techelevator.model.Message;
import com.techelevator.model.MessageContainer;
import com.techelevator.model.Response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

	@Autowired
	private Chatbot chatbot;
	
	Email email = new Email();
	
	List<ChatMessage> messageList = new ArrayList<ChatMessage>();

//	@Autowired
//	private AuthProvider authProvider;

//	@RequestMapping(path = "/", method = RequestMethod.GET)
//	public String authorizedOnly() throws UnauthorizedException {
//		return "Success";
//	}

	@GetMapping("/homepage/questions")
	public List<String> getHomepageReviews() throws Exception {
		List<String> questions = chatbot.getHomePagehelp();
		if (questions != null) {
			return questions;
		} else {
			throw new Exception("Help not found");
		}
	}

	@GetMapping("/homepage/answers")
	public Response getHomepageAnswers(@RequestParam String question) throws Exception {
		Response answer = chatbot.getAnswerFromQuestion(question);
		if (answer != null) {
			return answer;
		} else {
			throw new Exception("Help not found");
		}
	}

	@GetMapping("/homepage/jobsearch")
	public String getJobListings() {
		String answer = chatbot.getJobListings();
		return answer;
	}

	@PostMapping(
			  value = "homepage/texts", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void receiveText(@RequestBody Message message) {
		chatbot.submitText(message.getText());
	}
	
	@GetMapping("/homepage/getText")
	public List<String> getText() {
		return chatbot.getText();
	}
	
	@PostMapping(
			  value = "homepage/sendEmail", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendEmail(@RequestBody ChatMessage[] chatMessage) {
		String htmlString = "";
		for (ChatMessage message : chatMessage) {
			if (message.getIsBot()) {
				if (message.getLink() != null) {
				htmlString += "<p>ChatBot: " + message.getText() + "<a href=\"" + message.getLink() + "\"> Click Here </a> </p>";
				} else {
					htmlString += "<p>ChatBot: " + message.getText() + "</p>";
				}
			} else {
				htmlString += "<p>User: " + message.getText() + "</p>";
			}
		}
		try {
			email.sendEmail("maxmondays@gmail.com", htmlString);
		} catch (Exception e) {
			System.out.println("ERROR SENDING EMAIL");
			e.printStackTrace();
		}		
	
	}
	

}