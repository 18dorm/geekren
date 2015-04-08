package com.dorm18.geekren.github.pages;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	private String id;
	private String follows;
	private String starred;
	private String following;
	private String joinedOn;
	private String city;
	private String country;
	private String company;
	private Set<String> repositoriesContributedTo;

	public Set<String> getRepositoriesContributedTo() {
		return repositoriesContributedTo;
	}

	public void setRepositoriesContributedTo(
			Set<String> repositoriesContributedTo) {
		this.repositoriesContributedTo = repositoriesContributedTo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

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

	public static User fetch(String userId) throws IOException {
		User user = new User();
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

		Elements homeLocation = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > ul > li[itemprop=\"homeLocation\"]");
		if (homeLocation != null) {
			String homeLocationText = homeLocation.text();
			String[] split = homeLocationText.split(",");
			if (split.length == 2) {
				user.setCity(split[1]);
				user.setCountry(split[0]);
			}
		}

		Elements worksFor = doc
				.select("#site-container > div > div > div.column.one-fourth.vcard > ul > li[itemprop=\"worksFor\"]");
		if (worksFor != null) {
			user.setCompany(worksFor.text());
		}

		Elements reposContri = doc
				.select("#site-container > div > div > div.column.three-fourths > div.tab-content.js-repo-filter > div > div.columns.popular-repos > div:nth-child(2) > div > ul");
		if (reposContri != null) {
			user.setRepositoriesContributedTo(new HashSet<String>());
			reposContri.first().children().forEach(new Consumer<Element>() {
				@Override
				public void accept(Element node) {
					user.getRepositoriesContributedTo()
							.add(node
									.select("a > span.repo-and-owner.css-truncate-target")
									.text().trim());
				}
			});
		}
		return user;
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out
					.println("Usage: java com.dorm18.geekren.github.pages.User [githubid]");
			System.out
					.println("Example: java com.dorm18.geekren.github.pages.User rosicky1985");
			System.exit(1);
		}
		User user = fetch(args[0]);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(user));
	}
}
