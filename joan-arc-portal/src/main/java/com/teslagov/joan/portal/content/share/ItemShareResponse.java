package com.teslagov.joan.portal.content.share;

import java.util.List;

/**
 * Created by joncrain on 9/21/16.
 */
public class ItemShareResponse
{
    public String itemId;
    public Boolean success;
    public List<String> notSharedWith;
    public Error error;

    private class Error
    {
        public String code;
        public String message;
    }
}
