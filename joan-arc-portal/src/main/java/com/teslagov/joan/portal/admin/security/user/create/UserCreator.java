package com.teslagov.joan.portal.admin.security.user.create;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Kevin Chen
 */
public class UserCreator
{
	private static final Logger logger = LoggerFactory.getLogger( UserCreator.class );

	public UserCreateResponse createUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		UserRequestModel userRequestModel
	)
	{
		String url = PortalEndpointFactory.SharingRest.Community.makeCreateUserPath( arcConfiguration );
		logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
		logger.debug( "Adding user: {}", userRequestModel );

		HttpPost httpPost =
			new HttpPostBuilder( url )
				.urlFormParam( "f", "pjson" )
				.urlFormParam( "provider", arcConfiguration.getArcServerAdminUsername() )
				.urlFormParam( "username", userRequestModel.getUsername() )
				.urlFormParam( "password", userRequestModel.getPassword() )
				.urlFormParam( "fullName", userRequestModel.getFullname() )
				.urlFormParam( "description", userRequestModel.getDescription() )
				.urlFormParam( "email", userRequestModel.getEmail() )
				.urlFormParam( "accountId", userRequestModel.getAccountId())
				.urlFormParam( "role", userRequestModel.getRole() )
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, UserCreateResponse.class);
	}
}
