package com.teslagov.joan.core.http;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Kevin Chen
 */
class UriFactory
{
	private static final Logger logger = LoggerFactory.getLogger( UriFactory.class );

	static URI createURI( String url )
	{
		logger.debug( "Creating URL to {}", url );

		URIBuilder uriBuilder;
		URI uri;
		try
		{
			uriBuilder = new URIBuilder( url );
			uri = uriBuilder.build();
			logger.debug( "Built URI: {}", uri.toString() );
			return uri;
		}
		catch ( URISyntaxException e )
		{
			throw new RuntimeException( "Could not build URI... ", e );
		}
	}
}
