package com.teslagov.joan.portal.models;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;

/**
 * Created by joncrain on 9/20/16.
 *
 * Essentially a wrapper builder class for multipart entity builder that we can expose in the API
 *
 * The minimum required items are file and type or url and type, so we put those in the constructor.
 */
public class ItemUploadModel
{
    private MultipartEntityBuilder multipartEntityBuilder;

    public ItemUploadModel(File file, String type)
    {
        multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file", file);
        multipartEntityBuilder.addTextBody("type", type);
    }

    public ItemUploadModel(String url, String type)
    {
        multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addTextBody("url", url);
        multipartEntityBuilder.addTextBody("type", type);
    }

    public ItemUploadModel url(String url)
    {
        multipartEntityBuilder.addTextBody("url", url);
        return this;
    }

    public ItemUploadModel text(String text)
    {
        multipartEntityBuilder.addTextBody("text", text);
        return this;
    }

    public ItemUploadModel dataUrl(String dataUrl)
    {
        multipartEntityBuilder.addTextBody("dataUrl", dataUrl);
        return this;
    }

    public ItemUploadModel title(String title)
    {
        multipartEntityBuilder.addTextBody("title", title);
        return this;
    }

    public ItemUploadModel thumbnail(File thumbnail)
    {
        multipartEntityBuilder.addBinaryBody("thumbnail", thumbnail);
        return this;
    }

    public ItemUploadModel typeKeywords(String typeKeywords)
    {
        multipartEntityBuilder.addTextBody("typeKeywords", typeKeywords);
        return this;
    }

    public ItemUploadModel description(String description)
    {
        multipartEntityBuilder.addTextBody("description", description);
        return this;
    }

    public ItemUploadModel tags(String tags)
    {
        multipartEntityBuilder.addTextBody("tags", tags);
        return this;
    }

    public ItemUploadModel snippet(String snippet)
    {
        multipartEntityBuilder.addTextBody("snippet", snippet);
        return this;
    }

    public ItemUploadModel licenseInfo(String licenseInfo)
    {
        multipartEntityBuilder.addTextBody("licenseInfo", licenseInfo);
        return this;
    }

    public ItemUploadModel culture(String culture)
    {
        multipartEntityBuilder.addTextBody("culture", culture);
        return this;
    }

    public ItemUploadModel properties(String properties)
    {
        multipartEntityBuilder.addTextBody("properties", properties);
        return this;
    }

    public ItemUploadModel extent(String extent)
    {
        multipartEntityBuilder.addTextBody("extent", extent);
        return this;
    }

    public ItemUploadModel callbackHtml(String callbackHtml)
    {
        multipartEntityBuilder.addTextBody("callback.html", callbackHtml);
        return this;
    }

    public ItemUploadModel originItemId(String originItemId)
    {
        multipartEntityBuilder.addTextBody("originItemId", originItemId);
        return this;
    }

    public ItemUploadModel destinationItemId(String destinationItemId)
    {
        multipartEntityBuilder.addTextBody("destinationItemId", destinationItemId);
        return this;
    }

    public ItemUploadModel relationshipType(String relationshipType)
    {
        multipartEntityBuilder.addTextBody("relationshipType", relationshipType);
        return this;
    }

    public ItemUploadModel serviceUsername(String serviceUsername)
    {
        multipartEntityBuilder.addTextBody("serviceUsername", serviceUsername);
        return this;
    }

    public ItemUploadModel servicePassword(String servicePassword)
    {
        multipartEntityBuilder.addTextBody("servicePassword", servicePassword);
        return this;
    }

    public ItemUploadModel serviceCredentialsType(String serviceCredentialsType)
    {
        multipartEntityBuilder.addTextBody("serviceCredentialsType", serviceCredentialsType);
        return this;
    }

    public ItemUploadModel serviceProxyParams(String serviceProxyParams)
    {
        multipartEntityBuilder.addTextBody("serviceProxyParams", serviceProxyParams);
        return this;
    }

    public ItemUploadModel appCategories(String appCategories)
    {
        multipartEntityBuilder.addTextBody("appCategories", appCategories);
        return this;
    }

    public ItemUploadModel industries(String industries)
    {
        multipartEntityBuilder.addTextBody("industries", industries);
        return this;
    }

    public ItemUploadModel largeThumbnail(File largeThumbnail)
    {
        multipartEntityBuilder.addBinaryBody("largeThumbnail", largeThumbnail);
        return this;
    }

    public ItemUploadModel banner(File banner)
    {
        multipartEntityBuilder.addBinaryBody("banner", banner);
        return this;
    }

    public ItemUploadModel screenshot(File screenshot)
    {
        multipartEntityBuilder.addBinaryBody("screenshot", screenshot);
        return this;
    }

    public ItemUploadModel listingProperties(String listingProperties)
    {
        multipartEntityBuilder.addTextBody("listingProperties", listingProperties);
        return this;
    }

    public ItemUploadModel format(String format)
    {
        multipartEntityBuilder.addTextBody("f", format);
        return this;
    }

    public ItemUploadModel languages(String languages)
    {
        multipartEntityBuilder.addTextBody("languages", languages);
        return this;
    }

    public HttpEntity getHttpEntity()
    {
        return multipartEntityBuilder.build();
    }
}
