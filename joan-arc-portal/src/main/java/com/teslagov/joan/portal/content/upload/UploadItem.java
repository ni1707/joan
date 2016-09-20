package com.teslagov.joan.portal.content.upload;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.admin.security.user.create.UserCreator;
import com.teslagov.joan.portal.models.ItemUploadModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joncrain on 9/20/16.
 */
public class UploadItem
{
    private static final Logger logger = LoggerFactory.getLogger( UserCreator.class );

    public UploadItemResponse uploadItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            ItemUploadModel itemUploadModel,
            String username
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makeUploadItemPath( arcConfiguration, username );
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Uploading: {}", itemUploadModel );

        HttpPost httpPost =
                new HttpPostBuilder( url )
                        .urlFormParam( "f", "json" )
                        .urlFormParam( "description", itemUploadModel.getDescription() )
                        .build();

        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody( "file",
                itemUploadModel.getFile() ).build();

        httpPost.setEntity(httpEntity);

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, UploadItemResponse.class);
    }
}
