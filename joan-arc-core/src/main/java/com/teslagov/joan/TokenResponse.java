package com.teslagov.joan;

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
}
