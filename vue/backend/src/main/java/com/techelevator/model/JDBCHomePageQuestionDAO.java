package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCHomePageQuestionDAO implements HomePageDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCHomePageQuestionDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<String> getHelp() {
		List<String> questions = new ArrayList<String>();
		String sql = "SELECT keyword FROM response ";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		while (rows.next()) {
			questions.add(rows.getString("keyword"));
		}

		return questions;
	}

	@Override
	public Response getAnswerByKeyword(String keyword) {
		String words = keyword.replaceAll("-", " ").toLowerCase();
		List<Response> answers = new ArrayList<Response>();

			String sql = "SELECT link, importance, message FROM response WHERE ? LIKE '%' || keyword || '%' ";
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, words);
			while(rows.next()) {
				answers.add(mapRowsToResponse(rows));
			}
			int mostImportant = 0;
			int count = 0;
			int answerIndex = 0;
			for (Response answer : answers) {
				if (answer.getImportance() > mostImportant) {
					mostImportant = answer.getImportance();
					answerIndex = count;
					
				}
				count++;
			}
			Response response = new Response();
			try {
				response = answers.get(answerIndex);
			} catch (Exception e) {
				response = new Response();
				response.setMessage("Not Found");
				response.setLink("");
			}

		return response;
	}
	
	@Override
	public void insertMessage(String message) {
		String sql = "INSERT INTO webhook VALUES (?) ";
		jdbcTemplate.update(sql, message);
		
	}
	
	public List<String> retrieveMessage() {
		List<String> messages = new ArrayList<String>();
		String sql = "SELECT message FROM webhook ";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		while (rows.next()) {
			messages.add(rows.getString("message"));
		}
		String delSql = "DELETE FROM webhook ";
		jdbcTemplate.batchUpdate(delSql);
		return messages;
	
	}
	
	private Response mapRowsToResponse(SqlRowSet row) {
		Response response = new Response();
		response.setImportance(row.getInt("importance"));
		response.setLink(row.getString("link"));
		response.setMessage(row.getString("message"));
		return response;
	}


}
