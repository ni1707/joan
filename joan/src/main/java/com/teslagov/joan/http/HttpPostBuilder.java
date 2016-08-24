package com.teslagov.joan.http;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Endpoint;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kevin Chen
 */
public class HttpPostBuilder
{
	private final ArcConfiguration arcConfiguration;

	private final Endpoint endpoint;

	private final Map<String, String> urlFormParams;

	public HttpPostBuilder( ArcConfiguration arcConfiguration, Endpoint endpoint )
	{
		this.arcConfiguration = arcConfiguration;
		this.endpoint = endpoint;
		this.urlFormParams = new TreeMap<>();
		this.urlFormParams.put( "f", "json" );
	}

	public HttpPostBuilder urlFormParam( String key, String value )
	{
		urlFormParams.put( key, value );
		return this;
	}

	public HttpPost build()
	{
		URI uri = PortalUriFactory.createURI( arcConfiguration, endpoint );
		HttpPost httpPost = new HttpPost( uri );

		List<NameValuePair> nameValuePairs = new ArrayList<>();
		for ( Map.Entry<String, String> entry : urlFormParams.entrySet() )
		{
			String key = entry.getKey();
			String value = entry.getValue();
			nameValuePairs.add( new BasicNameValuePair( key, value ) );
		}

		try
		{
			httpPost.setEntity( new UrlEncodedFormEntity( nameValuePairs ) );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw new RuntimeException( "Could not url encode form data... ", e );
		}

		return httpPost;
	}
}
