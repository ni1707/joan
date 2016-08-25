package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.GroupAccess;
import com.teslagov.joan.portal.group.GroupCreator;
import com.teslagov.joan.portal.group.GroupResponse;
import com.teslagov.joan.portal.group.GroupSortField;
import com.teslagov.joan.portal.portal.PortalFetcher;
import com.teslagov.joan.portal.portal.PortalResponse;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import com.teslagov.joan.portal.user.UserFetcher;
import com.teslagov.joan.server.token.ServerTokenFetcher;
import com.teslagov.joan.server.token.ServerTokenResponse;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

/**
 * @author Kevin Chen
 */
public class Main
{
	private static final Logger logger = LoggerFactory.getLogger( Main.class );

	public static void main( String[] args )
	{
		ArcConfiguration arcConfiguration = ArcConfigurationFactory.createArcConfiguration();

		HttpClient httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient( arcConfiguration );

		PortalTokenFetcher portalTokenFetcher = new PortalTokenFetcher();

		PortalTokenResponse portalTokenResponse = portalTokenFetcher.fetchToken( httpClient, arcConfiguration );

		logger.debug( "PortalTokenResponse successful: {}", portalTokenResponse.isSuccess() );
		logger.debug( "PortalTokenResponse toString: {}", portalTokenResponse );

		long expiresEpochMs = portalTokenResponse.getExpires();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, ZoneOffset.UTC );
		logger.debug( "Current time = {}", LocalDateTime.now( ZoneOffset.UTC ) );
		logger.debug( "Token expires at {}", localDateTime );

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

		try
		{
			logger.debug( "TOKEN SERIALIZED = {}", objectMapper.writeValueAsString( portalTokenResponse ) );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}

		PortalFetcher portalFetcher = new PortalFetcher();
		PortalResponse portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, portalTokenResponse );
		logger.debug( "Portal ID = {}", portalResponse.id );

		UserFetcher userFetcher = new UserFetcher();
		userFetcher.fetchUsers( httpClient, arcConfiguration, portalTokenResponse, portalResponse );

		GroupCreator groupCreator = new GroupCreator();
		Group group = new Group();
		group.title = "Kevin's Test Group 4";
		group.description = "A test group owned by Kevin";
		group.snippet = "snippet...";
		group.tags = Arrays.asList( "tag1", "tag2" );
		group.phone = "Contact info";
		group.access = GroupAccess.PUBLIC;
		group.sortField = GroupSortField.TITLE;
		group.sortOrder = SortOrder.ASCENDING;
		group.isViewOnly = true;
		group.isInvitationOnly = false;
		group.thumbnail = "";
		GroupResponse groupResponse = groupCreator.createGroup( httpClient, arcConfiguration, portalTokenResponse, group );
		logger.info( "GROUP ACCESS = {}", groupResponse.group.access );

		// FETCH TOKEN FOR SERVER ADMIN API
//		ServerTokenFetcher serverTokenFetcher = new ServerTokenFetcher();
//		ServerTokenResponse serverTokenResponse = serverTokenFetcher.fetchServer( httpClient, arcConfiguration, 120 );
	}
}
