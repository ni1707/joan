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
	 * Hits /arcgis/portaladmin/logs/clean
	 */
	public void cleanLogs()
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * Returns the current log settings for the portal.
	 * Hits /arcgis/portaladmin/logs/settings
	 */
	public void getLogSettings()
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * Updates the log settings for the portal.
	 * Hits /arcgis/portaladmin/logs/settings/edit
	 */
	public void editLogSettings()
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * The query operation allows you to aggregate, filter, and page through logs written by the portal.
	 * Hits /arcgis/portaladmin/logs/query
	 */
	public void queryLogs()
	{
		throw new UnsupportedOperationException( "" );
	}
}
