package com.techelevator.integrationtest;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCHomePageQuestionDAO;
import com.techelevator.model.Response;

public class JDBCHomePageDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCHomePageQuestionDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		dao = new JDBCHomePageQuestionDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void get_help_amount_of_questions() {
		List<String> questions = dao.getHelp();
		String sql = "INSERT INTO homepage VALUES(DEFAULT, 'HOOGA BOOGA', 'YES HOOGA BOOGA')";
		jdbcTemplate.update(sql);
		List<String> results = dao.getHelp();
		Assert.assertNotNull(results);
		Assert.assertTrue(questions.size() + 1 == results.size());
	}
	
	@Test
	public void get_answer_by_question() {
		String sql = "INSERT INTO response VALUES('pathway', 'HOOGA BOOGA', 2,  'YES HOOGA BOOGA', 'ITS MY SAFE WORD', '{\"WAKAWAKA\"}')";
		jdbcTemplate.update(sql);
		String sql2 = "INSERT INTO response VALUES('pathway', 'safe', 1,  'YES HOOGA BOOGA', 'chocolate', '{\"WAKAWAKA\"}')";
		jdbcTemplate.update(sql2);
		Response answer = dao.getAnswerByKeyword("HOOGA-BOOGA-its-my-safe-word");
		Assert.assertNotNull(answer.getLink());
		
		Assert.assertTrue(answer.getLink().equals("ITS MY SAFE WORD"));
		
		
	}
	
	@Test
	public void check_catch_on_exception() {
		String sql = "INSERT INTO response VALUES('pathway', 'HOOGA BOOGA', 2,  'YES HOOGA BOOGA', 'ITS MY SAFE WORD', '{\"WAKAWAKA\"}')";
		jdbcTemplate.update(sql);
		String sql2 = "INSERT INTO response VALUES('pathway', 'safe', 1,  'YES HOOGA BOOGA', 'chocolate', '{\"WAKAWAKA\"}')";
		jdbcTemplate.update(sql2);
		Response redHerring = dao.getAnswerByKeyword("cucumber melon");
		Assert.assertEquals("sorry that makes no goddamn sense", redHerring.getMessage());
	}
}
