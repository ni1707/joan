package com.teslagov.joan.http;

import com.teslagov.joan.ArcConfiguration;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Kevin Chen
 */
class PortalUriFactory
{
	private static final Logger logger = LoggerFactory.getLogger( PortalUriFactory.class );

	static URI createURI( ArcConfiguration arcConfiguration, String path )
	{
		String url = arcConfiguration.getPortalUrl() + path;
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
