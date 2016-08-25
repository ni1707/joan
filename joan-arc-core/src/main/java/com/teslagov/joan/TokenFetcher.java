package com.teslagov.joan;

import org.apache.http.client.HttpClient;

/**
 * @author Kevin Chen
 */
public interface TokenFetcher
{
	TokenResponse fetchToken( HttpClient httpClient, ArcConfiguration arcConfiguration );
}
