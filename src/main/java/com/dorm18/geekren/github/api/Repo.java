package com.dorm18.geekren.github.api;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
public class Repo {
	static final Logger log = Logger.getLogger(Repo.class);
	public String name;
	public String full_name;
	public User owner;
	public Integer stargazers_count;
	public Integer watchers_count;
	public String language;
	public Integer watchers;
	public Integer network_count;
	public Integer subscribers_count;
	public Integer forks;
	public Integer forks_count;
	public Integer size;
	public Date created_at;
	public Date updated_at;
	public Date pushed_at;

	public Integer getWeight() {
		if (stargazers_count == null || forks_count == null) {
			return null;
		}
		if (forks_count + stargazers_count < 10) {
			return 1;
		} else if (forks_count + stargazers_count >= 10
				&& forks_count + stargazers_count < 20) {
			return 2;
		} else if (forks_count + stargazers_count >= 20
				&& forks_count + stargazers_count < 500) {
			return 5;
		} else if (forks_count + stargazers_count >= 500
				&& forks_count + stargazers_count < 1000) {
			return 10;
		} else {
			return 20;
		}

	}

	/**
	 * GET /repos/:owner/:repo
	 * 
	 * @param owner
	 * @param name
	 * 
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws IllegalStateException
	 */
	public static Repo fetch(String owner, String name)
			throws URISyntaxException, IllegalStateException,
			ClientProtocolException, IOException {
		URI uri = new URIBuilder().setScheme("https").setHost("api.github.com")
				.setPath("/repos/" + owner + "/" + name).build();
		log.trace("fetching from " + uri.toString());

		StringWriter writer = new StringWriter();
		IOUtils.copy(HttpClients.createDefault().execute(new HttpGet(uri))
				.getEntity().getContent(), writer, "utf-8");
		String content = writer.toString();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));

		return mapper.readValue(content, Repo.class);
	}

	public static void main(String[] args) throws IllegalStateException,
			ClientProtocolException, URISyntaxException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		Repo repo = fetch("18dorm", "geekren");
		System.out.println(mapper.writeValueAsString(repo));

		repo = fetch("ksky521", "nodePPT");
		System.out.println(mapper.writeValueAsString(repo));
	}
}
