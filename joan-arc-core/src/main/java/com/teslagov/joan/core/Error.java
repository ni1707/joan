package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class Error
{
	private int code;

	private String message;

	private List<String> details;

	public int getCode()
	{
		return code;
	}

	public void setCode( int code )
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public List<String> getDetails()
	{
		return details;
	}

	public void setDetails( List<String> details )
	{
		this.details = details;
	}

	@Override
	public String toString()
	{
		return "Error{" +
			"code=" + code +
			", message='" + message + '\'' +
			", details=" + details +
			'}';
	}
}
