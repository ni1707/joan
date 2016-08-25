package com.teslagov.joan.http;

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
	private final String url;

	private final Map<String, String> urlFormParams;

	public HttpPostBuilder( String url )
	{
		this.url = url;
		this.urlFormParams = new TreeMap<>();
	}

	public HttpPostBuilder urlFormParam( String key, String value )
	{
		urlFormParams.put( key, value );
		return this;
	}

	public HttpPostBuilder urlFormParam( String key, boolean value )
	{
		urlFormParams.put( key, value + "" );
		return this;
	}

	public HttpPostBuilder urlFormParam( String key, int value )
	{
		urlFormParams.put( key, value + "" );
		return this;
	}

	public HttpPost build()
	{
		URI uri = UriFactory.createURI( url );

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
