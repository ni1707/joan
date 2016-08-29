package com.teslagov.joan.api.portal;

import com.teslagov.joan.core.ArcConfiguration;
import org.apache.http.client.HttpClient;

/**
 * @author Kevin Chen
 */
public class ArcPortalLogApi
{
	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	public ArcPortalLogApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	/**
	 * Deletes all the log files on the machine hosting Portal for ArcGIS. This operation allows you to free up disk space. The logs cannot be
	 * recovered after executing this operation.
	 */
	public void cleanLogs()
	{

	}
}
