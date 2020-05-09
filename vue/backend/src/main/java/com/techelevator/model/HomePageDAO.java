package com.techelevator.model;

import java.util.List;

public interface HomePageDAO {

	List<String> getHelp();
	Response getAnswerByKeyword(String keyword);
	void insertMessage(String message);
}
