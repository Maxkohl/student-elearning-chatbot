package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Chatbot {

	@Autowired
	private JDBCHomePageQuestionDAO homepageDao;

	@Autowired
	private JobSearchApiClient apiClient;

	public Chatbot(DataSource dataSource) {
		homepageDao = new JDBCHomePageQuestionDAO(dataSource);
	}

	public List<String> getHomePagehelp() {
		List<String> questions = homepageDao.getHelp();
		return questions;
	}

	public Response getAnswerFromQuestion(String keyword) {
		Response answer = homepageDao.getAnswerByKeyword(keyword);
		return answer;

	}

	public String getJobListings() {
		String listings = apiClient.getClient();
		return listings;

	}

	public void submitText(String message) {
		homepageDao.insertMessage(message);
	}
	
	public List<String> getText() {
		return homepageDao.retrieveMessage();
	}

}
