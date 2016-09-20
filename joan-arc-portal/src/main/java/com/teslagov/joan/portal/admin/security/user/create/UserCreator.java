package com.teslagov.joan.portal.admin.security.user.create;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
