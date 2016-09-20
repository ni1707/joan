package com.teslagov.joan.portal.content.publish;

import com.teslagov.joan.core.Response;

/**
 * Created by joncrain on 9/20/16.
 */
public class PublishItemResponse extends Response
{
    public String id;

    @Override
    public String toString()
    {
        return "PublishItemResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
