package com.teslagov.joan.api.server;

import com.teslagov.joan.core.ArcConfiguration;
import org.apache.http.client.HttpClient;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class ClusterApi
{
	private final HttpClient httpClient;
	private final ArcConfiguration arcConfiguration;

	public ClusterApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	public void addMachinesToCluster( List<String> machineNames )
	{
		throw new UnsupportedOperationException( "" );
	}

	public void listClusters()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void createCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void deleteCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void editProtocol()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void getAvailableMachines()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void getMachinesInCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void removeMachinesFromCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void getServicesInCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void startCluster()
	{
		throw new UnsupportedOperationException( "" );
	}

	public void stopCluster()
	{
		throw new UnsupportedOperationException( "" );
	}
}
