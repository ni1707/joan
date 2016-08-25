package com.teslagov.joan.server.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.TokenResponse;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ServerTokenResponse extends TokenResponse
{
}
