package com.dorm18.geekren.github.api.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Reference:
 * http://stackoverflow.com/questions/21322778/how-do-i-get-a-list-of-
 * all-the-github-projects-ive-contributed-to-in-the-last-y?lq=1
 * 
 * @author rosicky1985
 *
 */
public class ContributionRepos {
	static final Logger log = Logger.getLogger(ContributionRepos.class);

	public String repo;
	public Integer count;

	public String getRepo() {
		return repo;
	}

	public Integer getCount() {
		return count;
	}

	public ContributionRepos(String repo, Integer count) {
		super();
		this.repo = repo;
		this.count = count;
	}

	@Override
	public String toString() {
		return "ContributionRepos [repo=" + repo + ", count=" + count + "]";
	}

	public static Map<String, Integer> fetch(String login, Date startDate,
			Integer months) throws IOException {
		List<ContributionRepos> result = new ArrayList<ContributionRepos>();

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final Pattern p = java.util.regex.Pattern
				.compile("Pushed\\s(\\d+)\\scommits?\\sto\\s(.*)");

		DateTime start = new DateTime(startDate).withDayOfMonth(1);
		DateTime endDate = start.plusMonths(months);
		for (DateTime month = start; month.isBefore(endDate); month = month
				.plusMonths(1)) {
			String from = sdf.format(month.toDate());
			String to = sdf.format(month.dayOfMonth().withMaximumValue()
					.toDate());
			String url = String.format(
					"https://github.com/%s?tab=contributions&from=%s&to=%s",
					login, from, to);
			log.debug(String.format("fetching from %s", url));

			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements contributionActivity = doc
					.select("#site-container > div > div > div.column.three-fourths > div.tab-content.js-repo-filter > div > div.activity-listing.contribution-activity.js-contribution-activity > div.contribution-activity-listing > ul");
			if (contributionActivity != null) {
				Elements avtivity = contributionActivity.select("li > a.title");
				List<ContributionRepos> pairs = avtivity.stream()
						.map(new Function<Element, ContributionRepos>() {
							@Override
							public ContributionRepos apply(Element t) {
								String text = t.text().trim();
								log.debug(String.format(
										"line of activity is %s", text));

								Matcher m = p.matcher(text);
								if (m.find()) {
									String cms = m.group(1);
									String repo = m.group(2);
									log.debug(String.format("%s,%s", cms, repo));
									return new ContributionRepos(repo, Integer
											.valueOf(cms));
								}
								return null;
							}

						}).collect(Collectors.toList());
				log.debug(pairs);
				result.addAll(pairs);
			}
		}
		List<ContributionRepos> valid = result.stream().filter(c -> c != null)
				.collect(Collectors.toList());
		log.debug(valid);
		Map<String, Integer> summary = valid.stream()
				.collect(
						Collectors.groupingBy(ContributionRepos::getRepo,
								Collectors.reducing(0,
										ContributionRepos::getCount,
										Integer::sum)));
		log.debug(summary);
		return summary;
	}

	public static void main(String[] args) throws IOException,
			NumberFormatException, ParseException {
		if (args.length != 3) {
			String fullName = "java com.dorm18.geekren.github.api.user.ContributionRepos";
			System.out.println(String.format(
					"Usage: %s [githubid],[startTime],[months]", fullName));
			System.out.println(String.format(
					"Example: %s rosicky1985,2013-09-01,15", fullName));
			System.exit(1);
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String login = args[0];
		fetch(login, sdf.parse(args[1]), Integer.valueOf(args[2]));
	}
}
