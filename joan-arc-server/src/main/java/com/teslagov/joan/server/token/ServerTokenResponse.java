package com.teslagov.joan.server.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.Response;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ServerTokenResponse extends Response
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
