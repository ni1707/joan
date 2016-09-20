package com.teslagov.joan.portal.models;

import java.io.File;

/**
 * Created by joncrain on 9/20/16.
 */
public class UploadItemModel
{
    private File file;
    private String url;
    private String text;
    private String dataUrl;
    private String title;
    private File thumbnail;
    private String thumbnailUrl;
    private String type;
    private String typeKeywords;
    private String description;
    private String tags;
    private String snippet;
    private String licenseInfo;
    private String culture;
    private String properties;
    private String extent;
    private String callbackHtml;
    private String originItemId;
    private String destinationItemId;
    private String relationshipType;
    private String serviceUsername;
    private String servicePassword;
    private String serviceCredentialsType;
    private String serviceProxyParams;
    private String appCategories;
    private String industries;
    private String languages;
    private File largeThumbnail;
    private File banner;
    private File screenshot1;
    private File screenshot2;
    private File screenshot3;
    private File screenshot4;
    private String listingProperties;
    private String f;

    public UploadItemModel()
    {

    }

    public UploadItemModel(File file, String url, String text, String dataUrl, String title, File thumbnail,
                           String thumbnailUrl, String type, String typeKeywords, String description, String tags,
                           String snippet, String licenseInfo, String culture, String properties, String extent,
                           String callbackHtml, String originItemId, String destinationItemId, String relationshipType,
                           String serviceUsername, String servicePassword, String serviceCredentialsType,
                           String serviceProxyParams, String appCategories, String industries, String languages,
                           File largeThumbnail, File banner, File screenshot1, File screenshot2, File screenshot3,
                           File screenshot4, String listingProperties, String f)
    {
        this.file = file;
        this.url = url;
        this.text = text;
        this.dataUrl = dataUrl;
        this.title = title;
        this.thumbnail = thumbnail;
        this.thumbnailUrl = thumbnailUrl;
        this.type = type;
        this.typeKeywords = typeKeywords;
        this.description = description;
        this.tags = tags;
        this.snippet = snippet;
        this.licenseInfo = licenseInfo;
        this.culture = culture;
        this.properties = properties;
        this.extent = extent;
        this.callbackHtml = callbackHtml;
        this.originItemId = originItemId;
        this.destinationItemId = destinationItemId;
        this.relationshipType = relationshipType;
        this.serviceUsername = serviceUsername;
        this.servicePassword = servicePassword;
        this.serviceCredentialsType = serviceCredentialsType;
        this.serviceProxyParams = serviceProxyParams;
        this.appCategories = appCategories;
        this.industries = industries;
        this.languages = languages;
        this.largeThumbnail = largeThumbnail;
        this.banner = banner;
        this.screenshot1 = screenshot1;
        this.screenshot2 = screenshot2;
        this.screenshot3 = screenshot3;
        this.screenshot4 = screenshot4;
        this.listingProperties = listingProperties;
        this.f = f;
    }

    public File getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public String getTitle() {
        return title;
    }

    public File getThumbnail() {
        return thumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getType() {
        return type;
    }

    public String getTypeKeywords() {
        return typeKeywords;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getLicenseInfo() {
        return licenseInfo;
    }

    public String getCulture() {
        return culture;
    }

    public String getProperties() {
        return properties;
    }

    public String getExtent() {
        return extent;
    }

    public String getCallbackHtml() {
        return callbackHtml;
    }

    public String getOriginItemId() {
        return originItemId;
    }

    public String getDestinationItemId() {
        return destinationItemId;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getServiceUsername() {
        return serviceUsername;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public String getServiceCredentialsType() {
        return serviceCredentialsType;
    }

    public String getServiceProxyParams() {
        return serviceProxyParams;
    }

    public String getAppCategories() {
        return appCategories;
    }

    public String getIndustries() {
        return industries;
    }

    public String getLanguages() {
        return languages;
    }

    public File getLargeThumbnail() {
        return largeThumbnail;
    }

    public File getBanner() {
        return banner;
    }

    public File getScreenshot1() {
        return screenshot1;
    }

    public File getScreenshot2() {
        return screenshot2;
    }

    public File getScreenshot3() {
        return screenshot3;
    }

    public File getScreenshot4() {
        return screenshot4;
    }

    public String getListingProperties() {
        return listingProperties;
    }

    public String getF() {
        return f;
    }

    @Override
    public String toString() {
        return "UploadItemModel{" +
                "file=" + file +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", dataUrl='" + dataUrl + '\'' +
                ", title='" + title + '\'' +
                ", thumbnail=" + thumbnail +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", type='" + type + '\'' +
                ", typeKeywords='" + typeKeywords + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", snippet='" + snippet + '\'' +
                ", licenseInfo='" + licenseInfo + '\'' +
                ", culture='" + culture + '\'' +
                ", properties='" + properties + '\'' +
                ", extent='" + extent + '\'' +
                ", callbackHtml='" + callbackHtml + '\'' +
                ", originItemId='" + originItemId + '\'' +
                ", destinationItemId='" + destinationItemId + '\'' +
                ", relationshipType='" + relationshipType + '\'' +
                ", serviceUsername='" + serviceUsername + '\'' +
                ", servicePassword='" + servicePassword + '\'' +
                ", serviceCredentialsType='" + serviceCredentialsType + '\'' +
                ", serviceProxyParams='" + serviceProxyParams + '\'' +
                ", appCategories='" + appCategories + '\'' +
                ", industries='" + industries + '\'' +
                ", languages='" + languages + '\'' +
                ", largeThumbnail=" + largeThumbnail +
                ", banner=" + banner +
                ", screenshot1=" + screenshot1 +
                ", screenshot2=" + screenshot2 +
                ", screenshot3=" + screenshot3 +
                ", screenshot4=" + screenshot4 +
                ", listingProperties='" + listingProperties + '\'' +
                ", f='" + f + '\'' +
                '}';
    }
}
