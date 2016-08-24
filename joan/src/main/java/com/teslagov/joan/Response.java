package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Response
{
	protected Error error;

	public Error getError()
	{
		return error;
	}

	@JsonIgnore
	public boolean isSuccess()
	{
		return error == null;
	}
}
