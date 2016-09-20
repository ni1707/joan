package com.teslagov.joan.portal.content.publish;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.admin.security.user.create.UserCreator;
import com.teslagov.joan.portal.models.PublishItemModel;
import com.teslagov.joan.portal.models.UploadItemModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joncrain on 9/20/16.
 */
public class PublishItem
{
    private static final Logger logger = LoggerFactory.getLogger( UserCreator.class );

    public PublishItemResponse publishItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            PublishItemModel publishItemModel,
            String username
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makeUploadItemPath( arcConfiguration, username );
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Publishing: {}", publishItemModel);

        HttpPost httpPost =
                new HttpPostBuilder( url )
                        .urlFormParam( "f", "pjson" )
                        .urlFormParam( "itemID", publishItemModel.getId() )
                        .build();

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, PublishItemResponse.class);
    }
}
