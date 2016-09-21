package com.teslagov.joan.portal.content.publish;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.models.PublishItemModel;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joncrain on 9/20/16.
 */
public class PublishItem
{
    private static final Logger logger = LoggerFactory.getLogger( PublishItem.class );

    public PublishItemResponse publishItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            PublishItemModel publishItemModel,
            String username
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makePublishItemPath( arcConfiguration, username );
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Publishing: {}", publishItemModel);

        HttpPost httpPost =
                new HttpPostBuilder( url )
                        .urlFormParam( "f", "json" )
                        .urlFormParam( "itemid", publishItemModel.getId() )
                        .urlFormParam( "filetype", "csv" )
                        .urlFormParam( "publishParameters", "{\"name\":\"" + publishItemModel.getId() + "\"}" )
                        .build();

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, PublishItemResponse.class);
    }
}
