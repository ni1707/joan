package com.teslagov.joan.portal.models;

/**
 * Created by joncrain on 9/20/16.
 */
public class PublishItemModel
{
    private String id;

    public PublishItemModel(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "PublishItemModel{" +
                "id='" + id + '\'' +
                '}';
    }
}
