package com.teslagov.joan.portal.portal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.core.Response;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class PortalResponse extends Response
{
	public String id;
}
