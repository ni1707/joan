package com.teslagov.joan;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Kevin Chen
 */
public class TokenFetcher
{
	private static final Logger logger = LoggerFactory.getLogger( TokenFetcher.class );

	public Token fetchToken( ArcConfiguration arcConfiguration )
	{
		HttpClient httpClient = createPortalHttpClient( arcConfiguration );

		URI uri = createURI( arcConfiguration, "/sharing/rest/generateToken" );
		HttpGet httpGet = new HttpGet( uri );
		try
		{
			HttpResponse response = httpClient.execute( httpGet );
			if ( response != null )
			{
				int status = response.getStatusLine().getStatusCode();
				if ( status != 200 )
				{
					logger.warn( "Non-200 status: {}", status );
				}
				HttpEntity httpEntity = response.getEntity();
				String responseString = EntityUtils.toString( httpEntity );
				logger.info( "{}", responseString );
			}
		}
		catch ( IOException e )
		{
			throw new RuntimeException( "Could not fetch token... ", e );
		}
		return null;
	}

	private static HttpClient createPortalHttpClient( ArcConfiguration arcConfiguration )
	{
		int portNumber = arcConfiguration.getPort();
		return HttpClientFactory.createHttpClient( portNumber );
	}

	private static URI createURI( ArcConfiguration arcConfiguration, String path )
	{
		String url = arcConfiguration.getPortalUrl() + path;
		logger.info( "Creating URL to {}", url );

		URIBuilder uriBuilder;
		URI uri;
		try
		{
			uriBuilder = new URIBuilder( url );
			uri = uriBuilder
				.addParameter( "username", arcConfiguration.getUsername() )
				.addParameter( "password", arcConfiguration.getPassword() )
				.addParameter( "client", "referer" )
				.addParameter( "expiration", "60" ) // TODO make configurable?
				.addParameter( "f", "json" )
				.build();
			logger.info( "Built URI: {}", uri.toString() );
			return uri;
		}
		catch ( URISyntaxException e )
		{
			throw new RuntimeException( "Could not build URI... ", e );
		}
	}
}
