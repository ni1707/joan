package com.teslagov.joan.core;

/**
 * @author Kevin Chen
 */
public class TokenManager
{
	private final TokenRefresher tokenRefresher;

	private TokenResponse tokenResponse;

	public TokenManager( TokenRefresher tokenRefresher )
	{
		this.tokenRefresher = tokenRefresher;
	}

	public TokenResponse getTokenResponse()
	{
		return tokenResponse;
	}

	public void setTokenResponse( TokenResponse tokenResponse )
	{
		this.tokenResponse = tokenResponse;
	}

	public void refreshTokenIfNecessary()
	{
		if ( isTokenExpired( tokenResponse ) )
		{
			tokenResponse = tokenRefresher.fetchToken();
		}
	}

	private boolean isTokenExpired( TokenResponse tokenResponse )
	{
		return tokenRefresher.isTokenExpired( tokenResponse );
	}
}
