package com.teslagov.joan.rampart;

import com.teslagov.joan.portal.sharing.community.group.Group;
import com.teslagov.rampart.hook.RampartHookBase;

import java.util.UUID;

import static com.teslagov.joan.portal.sharing.community.group.GroupBuilder.newGroup;

/**
 * @author Kevin Chen
 */
public class JoanRampartHook extends RampartHookBase
{
	// TODO generate ArcConfiguration and ArcApi for all these methods
	// TODO Noah will map a person's resource ID to their Arc username
	// TODO Noah will map the role's resource ID to its Arc group ID
	// TODO how do we handle failures? ideally, a durable message queue

	@Override
	public void roleCreated( UUID resourceId, String name )
	{
		Group group = newGroup()
			.title( name )
			.build();

		// arcApi.createGroup( group )
	}

	@Override
	public void roleUpdated( UUID resourceId, String name )
	{
		// TODO we need a findGroupByName operation
		// or, if 2 groups can have the same name, a findGroupById operation
	}

	@Override
	public void roleDeleted( UUID resourceId )
	{
		super.roleDeleted( resourceId );
	}

	@Override
	public void membershipCreated( UUID resourceId, UUID personResourceId, UUID roleResourceId )
	{
		super.membershipCreated( resourceId, personResourceId, roleResourceId );
	}

	@Override
	public void membershipDeleted( UUID resourceId )
	{
		super.membershipDeleted( resourceId );
	}
}
