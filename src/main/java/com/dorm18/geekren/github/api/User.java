package com.dorm18.geekren.github.api;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class User {
	static final Logger log = Logger.getLogger(User.class);
	public String login;
	public Integer public_repos;
	public Integer followers;
	public Integer following;
	public Date created_at;
	public Date updated_at;
	public String comany;
	public String location;
	public String email;

	public static User fetch(String login) throws URISyntaxException,
			ClientProtocolException, IOException {
		URI uri = new URIBuilder().setScheme("https").setHost("api.github.com")
				.setPath("/users/" + login).build();
		log.trace("fetching from " + uri.toString());
		HttpGet httpget = new HttpGet(uri);
		StringWriter writer = new StringWriter();
		IOUtils.copy(HttpClients.createDefault().execute(httpget).getEntity()
				.getContent(), writer, "utf-8");

		String content = writer.toString();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		User user = mapper.readValue(content, User.class);
		return user;
	}

	public static void main(String[] args) throws ClientProtocolException,
			URISyntaxException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = fetch("astaxie");
		System.out.println(mapper.writeValueAsString(user));
	}
}
