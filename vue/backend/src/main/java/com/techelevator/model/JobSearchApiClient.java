package com.techelevator.model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class JobSearchApiClient {

	private static final String API_URL = "https://jobs.github.com/positions.json?description=python&location=new+york";
	
	public String getClient() {
		
		String url = API_URL;
		
	     Client client = ClientBuilder.newClient();
	     
	      Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
	      
	      String responseAsString = response.readEntity(String.class);
	      
	      System.out.println(responseAsString);

	      response.close();
	      client.close();

	      return responseAsString;
		
	}
	
}
