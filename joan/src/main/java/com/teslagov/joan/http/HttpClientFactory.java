package com.teslagov.joan.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Kevin Chen
 */
public class HttpClientFactory
{
	/**
	 * Timeout in milliseconds until a connection is established.
	 */
	private static final int CONNECTION_TIMEOUT = 120000;

	/**
	 * Timeout in milliseconds for waiting for data (i.e., a maximum period inactivity between two consecutive data packets).
	 */
	private static final int SOCKET_TIMEOUT = 120000;

	/**
	 * Timeout in milliseconds used when requesting a connection from the connection manager.
	 */
	private static final int CONNECTION_REQUEST_TIMEOUT = 30000;

	// TODO mark this method as an example-only
	public static HttpClient createHttpClient( int portNumber )
	{
		return HttpClientBuilder.create()
			// The NO_OP HostnameVerifier essentially turns hostname verification off.
			// This implementation is a no-op, and never throws the SSLException.
			.setSSLHostnameVerifier( new NoopHostnameVerifier() )
			.setSSLContext( getSslContext() )
			.setDefaultRequestConfig(
				RequestConfig.custom()
					.setSocketTimeout( SOCKET_TIMEOUT )
					.setConnectTimeout( CONNECTION_TIMEOUT )
					.setConnectionRequestTimeout( CONNECTION_REQUEST_TIMEOUT )
					.build()
			)
			.setSchemePortResolver( host -> portNumber )
			.setConnectionManager(
				new BasicHttpClientConnectionManager(
					RegistryBuilder.<ConnectionSocketFactory>create()
						.register( "https", getSslConnectionSocketFactory() )
						.build()
				)
			)
			.setSSLSocketFactory( getSslConnectionSocketFactory() )
			.build();
	}

	private static SSLContext getSslContext()
	{
		try
		{
			return SSLContexts.custom().loadTrustMaterial( null, new TrustSelfSignedStrategy() ).build();
		}
		catch ( KeyStoreException | KeyManagementException | NoSuchAlgorithmException e )
		{
			throw new RuntimeException( "Could not make default SSLContext", e );
		}
	}

	private static SSLConnectionSocketFactory getSslConnectionSocketFactory()
	{
		return new SSLConnectionSocketFactory( getSslContext(), new NoopHostnameVerifier() );
	}
}
