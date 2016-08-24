package com.teslagov.joan.http;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Endpoint;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Kevin Chen
 */
public class PortalUriFactory
{
	private static final Logger logger = LoggerFactory.getLogger( PortalUriFactory.class );

	public static URI createURI( ArcConfiguration arcConfiguration, Endpoint endpoint )
	{
		String path = endpoint.getEndpointPath();
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
