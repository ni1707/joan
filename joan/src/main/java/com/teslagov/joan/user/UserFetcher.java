package com.teslagov.joan.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.token.TokenResponse;
import org.apache.http.client.HttpClient;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class UserFetcher
{
	public List<User> fetchUsers( HttpClient httpClient, ArcConfiguration arcConfiguration, TokenResponse tokenResponse )
	{
		throw new UnsupportedOperationException( "" );
	}
}
