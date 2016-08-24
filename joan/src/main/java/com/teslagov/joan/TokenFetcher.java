package com.teslagov.joan;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Chen
 */
public class TokenFetcher
{
	private static final Logger logger = LoggerFactory.getLogger( TokenFetcher.class );

	public TokenResponse fetchToken( ArcConfiguration arcConfiguration )
	{
		HttpClient httpClient = createPortalHttpClient( arcConfiguration );

		URI uri = createURI( arcConfiguration, "/sharing/rest/generateToken" );
		HttpPost httpPost = new HttpPost( uri );

		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add( new BasicNameValuePair( "username", arcConfiguration.getUsername() ) );
		urlParameters.add( new BasicNameValuePair( "password", arcConfiguration.getPassword() ) );
		urlParameters.add( new BasicNameValuePair( "referer", arcConfiguration.getReferer() ) );
		urlParameters.add( new BasicNameValuePair( "f", "json" ) );

		try
		{
			httpPost.setEntity( new UrlEncodedFormEntity( urlParameters ) );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw new RuntimeException( "Could not url encode params", e );
		}

		String responseString = null;

		try
		{
			HttpResponse response = httpClient.execute( httpPost );
			if ( response != null )
			{
				int status = response.getStatusLine().getStatusCode();
				if ( status != 200 )
				{
					logger.warn( "Non-200 status: {}", status );
				}
				else
				{
					logger.debug( "200 OK" );
				}
				HttpEntity httpEntity = response.getEntity();
				responseString = EntityUtils.toString( httpEntity );
				logger.info( "{}", responseString );
			}
		}
		catch ( IOException e )
		{
			throw new RuntimeException( "Could not fetch tokenResponse... ", e );
		}

		ObjectMapper objectMapper = new ObjectMapper();
		TokenResponse tokenResponse = null;
		try
		{
			tokenResponse = objectMapper.readValue( responseString, TokenResponse.class );
		}
		catch ( IOException e )
		{
			throw new RuntimeException( "Could not deserialize tokenResponse... ", e );
		}

		logger.info( "TokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.info( "TokenResponse toString: {}", tokenResponse );

		return tokenResponse;
	}

	private static HttpClient createPortalHttpClient( ArcConfiguration arcConfiguration )
	{
		int portNumber = arcConfiguration.getPort();
		return HttpClientFactory.createHttpClient( portNumber );
	}

	private static URI createURI( ArcConfiguration arcConfiguration, String path )
	{
		String url = arcConfiguration.getPortalUrl() + path;
		logger.debug( "Creating URL to {}", url );

		URIBuilder uriBuilder;
		URI uri;
		try
		{
			uriBuilder = new URIBuilder( url );
			uri = uriBuilder
				.build();
			logger.debug( "Built URI: {}", uri.toString() );
			return uri;
		}
		catch ( URISyntaxException e )
		{
			throw new RuntimeException( "Could not build URI... ", e );
		}
	}
}
