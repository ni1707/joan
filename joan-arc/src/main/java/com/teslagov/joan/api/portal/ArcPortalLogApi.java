package com.teslagov.joan.api.portal;

import com.teslagov.joan.api.AbstractArcRestApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class ArcPortalLogApi extends AbstractArcRestApi
{
	public ArcPortalLogApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager,
		String apiName
	)
	{
		super( httpClient, arcConfiguration, zoneOffset, tokenManager, apiName );
	}

	/**
	 * Deletes all the log files on the machine hosting Portal for ArcGIS. This operation allows you to free up disk space. The logs cannot be
	 * recovered after executing this operation.
	 */
	public void cleanLogs()
	{

	}

	/**
	 * Returns the current log settings for the portal.
	 */
	public void getLogSettings()
	{

	}

	/**
	 * Updates the log settings for the portal.
	 */
	public void editLogSettings()
	{

	}

	/**
	 * The query operation allows you to aggregate, filter, and page through logs written by the portal.
	 */
	public void queryLogs()
	{

	}
}
