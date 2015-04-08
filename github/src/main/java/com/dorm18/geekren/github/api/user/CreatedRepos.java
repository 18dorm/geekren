package com.dorm18.geekren.github.api.user;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.dorm18.geekren.github.api.Repo;
import com.dorm18.geekren.github.api.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
public class CreatedRepos {
	static final Logger log = Logger.getLogger(CreatedRepos.class);

	public User user;
	public Set<Repo> repos;

	public Integer createdPoints() {
		return repos.stream().mapToInt(v -> v.getWeight()).sum();
	}

	// https://api.github.com/users/rosicky1985/repos
	public static CreatedRepos fetch(User user) throws URISyntaxException,
			JsonParseException, JsonMappingException, IOException {
		CreatedRepos repos = new CreatedRepos();
		repos.user = user;
		URI uri = new URIBuilder().setScheme("https").setHost("api.github.com")
				.setPath("/users/" + user.login + "/repos").build();
		log.trace("fetching from " + uri.toString());

		StringWriter writer = new StringWriter();
		IOUtils.copy(HttpClients.createDefault().execute(new HttpGet(uri))
				.getEntity().getContent(), writer, "utf-8");
		String content = writer.toString();
		log.trace("returned from github " + content);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));

		Set<Repo> rps = mapper.readValue(content,
				new TypeReference<Set<Repo>>() {
				});
		repos.repos = rps;
		return repos;
	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, URISyntaxException, IOException {
		User rosicky1985 = new User();
		rosicky1985.login = "rosicky1985";
		CreatedRepos r = fetch(rosicky1985);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		System.out.println(mapper.writeValueAsString(r));
	}
}
