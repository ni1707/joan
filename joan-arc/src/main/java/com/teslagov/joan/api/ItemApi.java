package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.community.group.create.GroupCreator;
import com.teslagov.joan.portal.community.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.community.group.delete.GroupDeleter;
import com.teslagov.joan.portal.community.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.community.group.update.GroupUpdater;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAdder;
import com.teslagov.joan.portal.community.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.portal.community.group.userremove.GroupUserRemover;
import com.teslagov.joan.portal.content.analyze.ItemAnalyzeResponse;
import com.teslagov.joan.portal.content.analyze.ItemAnalyzer;
import com.teslagov.joan.portal.content.delete.ItemDeleteResponse;
import com.teslagov.joan.portal.content.delete.ItemDeleter;
import com.teslagov.joan.portal.content.fetch.ItemFetchResponse;
import com.teslagov.joan.portal.content.fetch.ItemFetcher;
import com.teslagov.joan.portal.content.publish.ItemPublishResponse;
import com.teslagov.joan.portal.content.publish.ItemPublisher;
import com.teslagov.joan.portal.content.share.ItemShareResponse;
import com.teslagov.joan.portal.content.share.ItemSharer;
import com.teslagov.joan.portal.content.upload.ItemUploadResponse;
import com.teslagov.joan.portal.content.upload.ItemUploader;
import com.teslagov.joan.portal.models.ItemPublishModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.List;

/**
 *
 * Api for dealing with Items
 * Created by joncrain on 9/22/16.
 */
public class ItemApi extends AbstractArcRestApi
{
    private static final Logger logger = LoggerFactory.getLogger( ItemApi.class );

    private final ItemUploader itemUploader = new ItemUploader();

    private final ItemPublisher itemPublisher = new ItemPublisher();

    private final ItemDeleter itemDeleter = new ItemDeleter();

    private final ItemSharer itemSharer = new ItemSharer();

    private final ItemAnalyzer itemAnalyzer = new ItemAnalyzer();

    private final ItemFetcher itemFetcher = new ItemFetcher();

    public ItemApi(
            HttpClient httpClient,
            ArcConfiguration arcConfiguration,
            ZoneOffset zoneOffset,
            TokenManager tokenManager
    )
    {
        super (httpClient, arcConfiguration, zoneOffset, tokenManager, "Item Api");
    }

    public ItemUploadResponse uploadItem(ItemUploadModel itemUploadModel, String username )
    {
        refreshTokenIfNecessary();
        return itemUploader.uploadItem( httpClient, arcConfiguration, tokenManager.getTokenResponse(), itemUploadModel, username);
    }

    public ItemPublishResponse publishItem(ItemPublishModel itemPublishModel, String username )
    {
        refreshTokenIfNecessary();
        return itemPublisher.publishItem( httpClient, arcConfiguration, tokenManager.getTokenResponse(), itemPublishModel, username);
    }

    public ItemDeleteResponse deleteItem(String id, String username )
    {
        refreshTokenIfNecessary();
        return itemDeleter.deleteItem( httpClient, arcConfiguration, tokenManager.getTokenResponse(), id, username);
    }

    public ItemShareResponse shareItem(String id, String username, String groups)
    {
        refreshTokenIfNecessary();
        return itemSharer.shareItem( httpClient, arcConfiguration, tokenManager.getTokenResponse(), id, username, groups);
    }

    public ItemAnalyzeResponse analyzeResponse(String id)
    {
        refreshTokenIfNecessary();
        return itemAnalyzer.analyzeItem( httpClient, arcConfiguration, tokenManager.getTokenResponse(), id );
    }

    public ItemFetchResponse fetchItems(String username)
    {
        refreshTokenIfNecessary();
        return itemFetcher.fetchItems( httpClient, arcConfiguration, tokenManager.getTokenResponse(), username);
    }
}
