package com.teslagov.joan.example;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.ArcPortalConfiguration;
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
 * This creates a HttpClient that trusts all self-signed certs. You shouldn't actually write code like this. It's just an example.
 *
 * @author Kevin Chen
 */
public class TrustingHttpClientFactory {
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

	public static HttpClient createVeryUnsafePortalHttpClient(ArcConfiguration arcConfiguration) {
		ArcPortalConfiguration arcPortalConfiguration = arcConfiguration.getArcPortalConfiguration();
		int portNumber = arcPortalConfiguration.getPortalPort();
		return createVeryUnsafeHttpClient(portNumber);
	}

	public static HttpClient createVeryUnsafeHttpClient(int portNumber) {
		return HttpClientBuilder.create()
			// The NO_OP HostnameVerifier essentially turns hostname verification off.
			// This implementation is a no-op, and never throws the SSLException.
			.setSSLHostnameVerifier(new NoopHostnameVerifier())
			.setSSLContext(getSslContext())
			.setDefaultRequestConfig(
				RequestConfig.custom()
					.setSocketTimeout(SOCKET_TIMEOUT)
					.setConnectTimeout(CONNECTION_TIMEOUT)
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
					.build()
			)
			.setSchemePortResolver(host -> portNumber)
			.setConnectionManager(
				new BasicHttpClientConnectionManager(
					RegistryBuilder.<ConnectionSocketFactory>create()
						.register("https", getSslConnectionSocketFactory())
						.build()
				)
			)
			.setSSLSocketFactory(getSslConnectionSocketFactory())
			.build();
	}

	private static SSLContext getSslContext() {
		try {
			return SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not make default SSLContext", e);
		}
	}

	private static SSLConnectionSocketFactory getSslConnectionSocketFactory() {
		return new SSLConnectionSocketFactory(getSslContext(), new NoopHostnameVerifier());
	}
}
