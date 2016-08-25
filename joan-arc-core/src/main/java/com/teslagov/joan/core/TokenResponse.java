package com.teslagov.joan.core;

/**
 * @author Kevin Chen
 */
public abstract class TokenResponse extends Response
{
	private String token;

	private long expires;

	public String getToken()
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}

	public long getExpires()
	{
		return expires;
	}

	public void setExpires( long expires )
	{
		this.expires = expires;
	}

	@Override
	public String toString()
	{
		return "TokenResponse{" +
			"token='" + token + '\'' +
			", expires=" + expires +
			'}';
	}
}
