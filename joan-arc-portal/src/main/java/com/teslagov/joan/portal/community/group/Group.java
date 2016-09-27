package com.teslagov.joan.portal.community.group;

import com.teslagov.joan.core.SortOrder;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class Group {
	// ONLY SET BY RESPONSE
	public String id;

	public String owner;

	public boolean isFav;

	public long created;

	public long modified;

	public String provider;

	public String providerGroupName;

	public boolean isReadOnly;

	public List<String> capabilities;

	/**
	 * The group title must be unique for the username, and the character limit is 250.
	 */
	public String title;

	/**
	 * A description of the group that can be any length.
	 */
	public String description;

	/**
	 * Snippet or summary of the group that has a character limit of 250 characters.
	 */
	public String snippet;

	/**
	 * Tags are words or short phrases that describe the group. Separate terms with commas.
	 */
	public List<String> tags;

	/**
	 * Phone is the group contact information. It can be a combination of letters and numbers. The character limit is 250.
	 */
	public String phone;

	/**
	 * Sets the access level for the group. private is the default. Setting to org restricts group access to members of your organization. If public,
	 * all users can access the group.
	 */
	public GroupAccess access;

	/**
	 * Sets sort field for group items.
	 */
	public GroupSortField sortField;

	/**
	 * Sets sort order for group items.
	 */
	public SortOrder sortOrder;

	/**
	 * Allows the group owner or admin to create view-only groups where members are not able to share items. If members try to share, view-only groups
	 * are returned in the notshared response property. false is the default.
	 */
	public boolean isViewOnly;

	/**
	 * If true, this group will not accept join requests. If false, this group does not require an invitation to join. Only group owners and admins
	 * can invite users to the group. false is the default.
	 */
	public boolean isInvitationOnly;

	/**
	 * Enter the pathname to the thumbnail image to be used for the group. The recommended image size is 200 pixels wide by 133 pixels high.
	 * Acceptable image formats are PNG, GIF, and JPEG. The maximum file size for an image is 1 MB. This is not a reference to the file but the file
	 * itself, which will be stored in the Portal. Example: thumbnail=subfolder/thumbnail.jpg
	 */
	public String thumbnail;
}
