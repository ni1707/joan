package com.teslagov.joan.portal.content.upload;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.models.UploadItemModel;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joncrain on 9/20/16.
 */
public class UploadItem
{
    private static final Logger logger = LoggerFactory.getLogger( UploadItem.class );

    public UploadItemResponse uploadItem(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            TokenResponse tokenResponse,
            UploadItemModel uploadItemModel,
            String username
    )
    {
        String url = PortalEndpointFactory.SharingRest.Content.makeUploadItemPath( arcConfiguration, username );
        logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
        logger.debug( "Uploading: {}", uploadItemModel);

        HttpPost httpPost = new HttpPostBuilder( url ).build();

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
            .addTextBody("appCategories", nullToEmpty(uploadItemModel.getAppCategories()))
            .addTextBody("callback.html", nullToEmpty(uploadItemModel.getCallbackHtml()))
            .addTextBody("culture", nullToEmpty(uploadItemModel.getCulture()))
            .addTextBody("dataUrl", nullToEmpty(uploadItemModel.getDataUrl()))
            .addTextBody("description", nullToEmpty(uploadItemModel.getDescription()))
            .addTextBody("destinationItemId", nullToEmpty(uploadItemModel.getDestinationItemId()))
            .addTextBody("f", nullToEmpty(uploadItemModel.getF()))
            .addTextBody("languages", nullToEmpty(uploadItemModel.getLanguages()))
            .addTextBody("licenseInfo", nullToEmpty(uploadItemModel.getLicenseInfo()))
            .addTextBody("listingProperties", nullToEmpty(uploadItemModel.getListingProperties()))
            .addTextBody("originItemId", nullToEmpty(uploadItemModel.getOriginItemId()))
            .addTextBody("properties", nullToEmpty(uploadItemModel.getProperties()))
            .addTextBody("relationshipType", nullToEmpty(uploadItemModel.getRelationshipType()))
            .addTextBody("serviceCredentialsType", nullToEmpty(uploadItemModel.getServiceCredentialsType()))
            .addTextBody("servicePassword", nullToEmpty(uploadItemModel.getServicePassword()))
            .addTextBody("serviceProxyParams", nullToEmpty(uploadItemModel.getServiceProxyParams()))
            .addTextBody("serviceUsername", nullToEmpty(uploadItemModel.getServiceUsername()))
            .addTextBody("text", nullToEmpty(uploadItemModel.getText()))
            .addTextBody("title", nullToEmpty(uploadItemModel.getTitle()))
            .addTextBody("tags", nullToEmpty(uploadItemModel.getTags()))
            .addTextBody("thumbnailUrl", nullToEmpty(uploadItemModel.getThumbnailUrl()))
            .addTextBody("type", nullToEmpty(uploadItemModel.getType()))
            .addTextBody("typeKeywords", nullToEmpty(uploadItemModel.getTypeKeywords()))
            .addTextBody("url", nullToEmpty(uploadItemModel.getUrl()));

        if (uploadItemModel.getBanner() != null) multipartEntityBuilder.addBinaryBody("banner", uploadItemModel.getBanner());
        if (uploadItemModel.getFile() != null) multipartEntityBuilder.addBinaryBody("file", uploadItemModel.getFile());
        if (uploadItemModel.getLargeThumbnail() != null) multipartEntityBuilder.addBinaryBody("largeThumbnail", uploadItemModel.getLargeThumbnail());
        if (uploadItemModel.getThumbnail() != null) multipartEntityBuilder.addBinaryBody("thumbnail", uploadItemModel.getThumbnail());
        if (uploadItemModel.getScreenshot1() != null) multipartEntityBuilder.addBinaryBody("screenshot", uploadItemModel.getScreenshot1());
        if (uploadItemModel.getScreenshot2() != null) multipartEntityBuilder.addBinaryBody("screenshot", uploadItemModel.getScreenshot2());
        if (uploadItemModel.getScreenshot3() != null) multipartEntityBuilder.addBinaryBody("screenshot", uploadItemModel.getScreenshot3());
        if (uploadItemModel.getScreenshot4() != null) multipartEntityBuilder.addBinaryBody("screenshot", uploadItemModel.getScreenshot4());

        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

        return HttpExecutor.getResponse(httpClient, httpPost, UploadItemResponse.class);
    }

    private String nullToEmpty(String s)
    {
        if (s == null) return "";
        return s;
    }
}
