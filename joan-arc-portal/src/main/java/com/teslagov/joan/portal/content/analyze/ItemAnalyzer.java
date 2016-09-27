package com.teslagov.joan.portal.content.analyze;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/features/analyze
 * Created by joncrain on 9/23/16.
 */
public class ItemAnalyzer {
	private static final Logger logger = LoggerFactory.getLogger(ItemAnalyzer.class);

	//We return a string because we need publish params to pass back to analyze in unaltered JSON format
	public String analyzeItem(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String id
	) {
		String url = PortalEndpointFactory.SharingRest.Content.makeAnalyzeItemPath(arcConfiguration);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Analyzing Item: {}", id);

		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "json")
				.urlFormParam("itemid", id)
				.urlFormParam("filetype", "csv")
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		String analyzeResponse = HttpExecutor.getStringResponse(httpClient, httpPost);

		//TODO: find more elegant way to grab JSON response for this object
		String jsonObject = "\"publishParameters\":";
		int start = analyzeResponse.indexOf(jsonObject) + jsonObject.length();
		int bracketCount = 0;
		int end = start;

		for (int i = start; i < analyzeResponse.length(); i++) {
			if (analyzeResponse.charAt(i) == '{') {
				bracketCount++;
			} else if (analyzeResponse.charAt(i) == '}') {
				bracketCount--;
			}

			if (bracketCount == 0) {
				end = i + 1;
				break;
			}
		}

		return analyzeResponse.substring(start, end);
	}
}
