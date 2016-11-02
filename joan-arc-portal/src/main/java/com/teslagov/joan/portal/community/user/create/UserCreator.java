package com.teslagov.joan.portal.community.user.create;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.UserAdminRequestModel;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * {}/sharing/rest/community/createUser
 *
 * @author Kevin Chen
 */
public class UserCreator {
	private static final Logger logger = LoggerFactory.getLogger(UserCreator.class);

	public UserCreateResponse createUser(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		UserRequestModel userRequestModel
	) {
		String url = PortalEndpointFactory.SharingRest.Community.makeCreateUserPath(arcConfiguration);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Adding user: {}", userRequestModel);

		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "json")
				.urlFormParam("provider", "arcgis")
				.urlFormParam("username", userRequestModel.getUsername())
				.urlFormParam("password", userRequestModel.getPassword())
				.urlFormParam("fullname", userRequestModel.getFullname())
				.urlFormParam("description", userRequestModel.getDescription())
				.urlFormParam("email", userRequestModel.getEmail())
				.urlFormParam("accountId", userRequestModel.getUsername())
				.urlFormParam("role", userRequestModel.getRole())
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, UserCreateResponse.class);
	}

	public UserCreateResponse adminCreateUser(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		UserAdminRequestModel userAdminRequestModel,
	    CookieStore cookieStore
	) {
		String path = PortalEndpointFactory.PortalAdmin.makeLoginPath(arcConfiguration);
		HttpPost httpPost =
			new HttpPostBuilder(path)
				.urlFormParam("username", arcConfiguration.getPortalAdminUsername())
				.urlFormParam("password", arcConfiguration.getPortalAdminPassword())
				.urlFormParam("redirect", "/")
				.build();

		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpPost);
			logger.debug("Http status code " + httpResponse.getStatusLine());
		} catch (Exception e) {

		} finally {
			httpPost.releaseConnection();
		}

		for (Cookie cookie : cookieStore.getCookies()) {
			logger.debug("Retrieved cookie " + cookie);
		}

		String url = PortalEndpointFactory.PortalAdmin.Security.makeCreateUserPath(arcConfiguration);
		logger.debug("Adding user: {}", userAdminRequestModel);

		httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", userAdminRequestModel.getF())
				.urlFormParam("provider", userAdminRequestModel.getProvider())
				.urlFormParam("username", userAdminRequestModel.getUsername())
				.urlFormParam("email", userAdminRequestModel.getEmail())
				.urlFormParam("role", userAdminRequestModel.getRole())
				.urlFormParam("firstname", userAdminRequestModel.getFirstname())
				.urlFormParam("lastname", userAdminRequestModel.getLastname())
				.build();

		String host = arcConfiguration.getPortalUrl().substring(8);
		httpPost.addHeader("Host", host);
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("Cache-Control", "max-age=0");
		httpPost.addHeader("Origin", arcConfiguration.getPortalUrl());
		httpPost.addHeader("Upgrade-Insecure-Requests", "1");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.addHeader("Referer", PortalEndpointFactory.PortalAdmin.makeLoginPath(arcConfiguration));
		httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.addHeader("Accept-Language", "en-US,en;q=0.8");

		if (!cookieStore.getCookies().isEmpty()) {
			httpPost.addHeader("Cookie", "PORTAL_ADMIN_TOKEN=" + cookieStore.getCookies().get(0).getValue());
		}

		return HttpExecutor.getResponse(httpClient, httpPost, UserCreateResponse.class);
	}

	private static SSLContext getSslContext() {
		try {
			return SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not make default SSLContext", e);
		}
	}

	private static SSLConnectionSocketFactory getSslConnectionSocketFactory() {
		return new SSLConnectionSocketFactory(getSslContext(), new NoopHostnameVerifier());
	}
}
