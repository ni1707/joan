package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class TokenResponse extends Response
{
	private String token;

	private long expires;

	private boolean ssl;

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

	public boolean isSsl()
	{
		return ssl;
	}

	public void setSsl( boolean ssl )
	{
		this.ssl = ssl;
	}

	@Override
	public String toString()
	{
		return "TokenResponse{" +
			"error=" + error +
			", token='" + token + '\'' +
			", expires=" + expires +
			", ssl=" + ssl +
			'}';
	}
}
