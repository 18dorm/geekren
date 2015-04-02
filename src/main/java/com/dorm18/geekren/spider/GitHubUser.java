package com.dorm18.geekren.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubUser {

	private String id;
	private String follows;
	private String starred;
	private String following;
	private String joinedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFollows() {
		return follows;
	}

	public void setFollows(String follows) {
		this.follows = follows;
	}

	public String getStarred() {
		return starred;
	}

	public void setStarred(String starred) {
		this.starred = starred;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(String joinedOn) {
		this.joinedOn = joinedOn;
	}

	public static GitHubUser fetch(String userId) throws IOException {
		GitHubUser user = new GitHubUser();
		user.setId(userId);

		Document doc = Jsoup.connect("https://github.com/" + userId + "/")
				.get();
		Elements follows = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > div.vcard-stats > a:nth-child(1) > strong");
		user.setFollows(follows.text());

		Elements starred = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > div.vcard-stats > a:nth-child(2) > strong");
		user.setStarred(starred.text());

		Elements following = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > div.vcard-stats > a:nth-child(3) > strong");
		user.setFollowing(following.text());

		Elements joinedOn = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > ul > li > time");
		user.setJoinedOn(joinedOn.text());

		return user;
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out
					.println("Usage: java com.dorm18.geekren.spider.GitHubUser [githubid]");
			System.out
					.println("Example: java com.dorm18.geekren.spider.GitHubUser [githubid]");
		}
		GitHubUser user = fetch(args[0]);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(user));
	}
}
