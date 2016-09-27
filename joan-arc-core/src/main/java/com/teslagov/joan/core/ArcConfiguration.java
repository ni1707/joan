package com.teslagov.joan.core;

/**
 * Configuration for hitting ArcGIS Portal and Server (contains info about credentials, ports, and context paths).
 * <p>
 * NOTE: If you bypass the Web Adaptor (i.e., if you provide a port number in the URL) and go straight to the Portal or Server instance, then the context path is {@code /}
 * If you hit the Web Adaptor (i.e., if you DO NOT provide a port number in the URL), then the context path is the one the sysadmin chose.
 *
 * @author Kevin Chen
 */
public interface ArcConfiguration {

	ArcServerConfiguration getArcServerConfiguration();

	ArcPortalConfiguration getArcPortalConfiguration();
}
