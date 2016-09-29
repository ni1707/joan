package com.teslagov.joan.portal.community.group.fetchUsers;

import com.teslagov.joan.core.Response;

import java.util.List;

/**
 * Created by joncrain on 9/29/16.
 */
public class GroupUserFetchResponse extends Response
{
    public String owner;
    public List<String> admins;
    public List<String> users;
}
