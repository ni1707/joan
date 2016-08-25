package com.teslagov.joan.portal.token;

import com.teslagov.joan.core.TokenResponse;

/**
 * @author Kevin Chen
 */
public class PortalTokenResponse extends TokenResponse
{
	private boolean ssl;

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
		return "PortalTokenResponse{" +
			"error=" + error +
			", token='" + super.getToken() + '\'' +
			", expires=" + super.getExpires() +
			", ssl=" + ssl +
			'}';
	}
}
