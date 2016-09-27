package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;

/**
 * Parent API
 *
 * @author Kevin Chen
 */
public class ArcPortalApi extends AbstractArcRestApi {
	private static final Logger logger = LoggerFactory.getLogger(ArcPortalApi.class);

	public final UserApi userApi;

	public final GroupApi groupApi;

	public final ItemApi itemApi;

	public ArcPortalApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager
	) {
		super(httpClient, arcConfiguration, zoneOffset, tokenManager, "Portal Api");

		//Initialize all sub apis
		userApi = new UserApi(httpClient, arcConfiguration, zoneOffset, tokenManager);
		groupApi = new GroupApi(httpClient, arcConfiguration, zoneOffset, tokenManager);
		itemApi = new ItemApi(httpClient, arcConfiguration, zoneOffset, tokenManager);
	}
}
