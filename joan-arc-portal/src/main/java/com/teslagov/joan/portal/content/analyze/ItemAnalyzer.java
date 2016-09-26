package com.teslagov.joan.portal.content.analyze;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/features/analyze
 * Created by joncrain on 9/23/16.
 */
public class ItemAnalyzer
{
    private static final Logger logger = LoggerFactory.getLogger( ItemAnalyzer.class );

    public ItemAnalyzeResponse analyzeItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            String id
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makeAnalyzeItemPath( arcConfiguration);
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Analyzing Item: {}", id);

        HttpPost httpPost =
                new HttpPostBuilder( url )
                        .urlFormParam( "f", "json" )
                        .urlFormParam( "itemid", id )
                        .urlFormParam( "filetype", "csv" )
                        .build();

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, ItemAnalyzeResponse.class);
    }
}
