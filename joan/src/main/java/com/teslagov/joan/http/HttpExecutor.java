package com.teslagov.joan.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Kevin Chen
 */
public class HttpExecutor
{
	private static final Logger logger = LoggerFactory.getLogger( HttpExecutor.class );

	public static <T> T getResponse( HttpClient httpClient, HttpRequestBase httpPost, Class<T> clazz )
	{
		String response = getStringResponse( httpClient, httpPost );

		ObjectMapper objectMapper = new ObjectMapper();

		try
		{
			return objectMapper.readValue( response, clazz );
		}
		catch ( IOException e )
		{
			throw new RuntimeException( "Could not deserialize tokenResponse... ", e );
		}
	}

	private static String getStringResponse( HttpClient httpClient, HttpRequestBase httpPost )
	{
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
				String responseString = EntityUtils.toString( httpEntity );
				logger.debug( "{}", responseString );
				return responseString;
			}
		}
		catch ( IOException e )
		{
			throw new RuntimeException( "Could not fetch tokenResponse... ", e );
		}
		throw new RuntimeException( "No response found... " );
	}
}
