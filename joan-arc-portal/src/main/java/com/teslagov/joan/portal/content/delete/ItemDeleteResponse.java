package com.teslagov.joan.portal.content.delete;

/**
 * Created by joncrain on 9/21/16.
 */
public class ItemDeleteResponse
{
    public Boolean success;
    public String itemId;

    @Override
    public String toString()
    {
        return "ItemShareResponse{" +
                "success=" + success +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
