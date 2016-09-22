package com.teslagov.joan.portal.content.delete;

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
 * {}/sharing/rest/content/users/{id}/items/{id}/delete
 * Created by joncrain on 9/21/16.
 */
public class ItemDeleter
{
    private static final Logger logger = LoggerFactory.getLogger( ItemDeleter.class );

    public ItemDeleteResponse deleteItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            String id,
            String username
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makeDeleteItemPath( arcConfiguration, id, username );
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Deleting Item: {}", id);

        HttpPost httpPost =
                new HttpPostBuilder( url )
                        .urlFormParam( "f", "pjson" )
                        .build();

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, ItemDeleteResponse.class);
    }
}
