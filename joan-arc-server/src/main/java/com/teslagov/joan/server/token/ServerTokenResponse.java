package com.teslagov.joan.server.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.Response;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ServerTokenResponse extends Response
{
	public String token;

	public long expires;
}
