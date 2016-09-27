package com.teslagov.joan.portal.community.group;

import com.teslagov.joan.core.SortOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Chen
 */
public class GroupBuilder {
	private String title;

	private String description;

	private String snippet;

	private List<String> tags;

	private String phone;

	private GroupAccess access;

	private GroupSortField sortField;

	private SortOrder sortOrder;

	private boolean isViewOnly;

	private boolean isInvitationOnly;

	private String thumbnail;

	public static GroupBuilder newGroup() {
		return new GroupBuilder();
	}

	public GroupBuilder title(String title) {
		this.title = title;
		return this;
	}

	public GroupBuilder description(String description) {
		this.description = description;
		return this;
	}

	public GroupBuilder snippet(String snippet) {
		this.snippet = snippet;
		return this;
	}

	public GroupBuilder tag(String tag) {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		tags.add(tag);
		return this;
	}

	public GroupBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}

	public GroupBuilder access(GroupAccess access) {
		this.access = access;
		return this;
	}

	public GroupBuilder sortField(GroupSortField sortField) {
		this.sortField = sortField;
		return this;
	}

	public GroupBuilder sortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public GroupBuilder isViewOnly(boolean isViewOnly) {
		this.isViewOnly = isViewOnly;
		return this;
	}

	public GroupBuilder isInvitationOnly(boolean isInvitationOnly) {
		this.isInvitationOnly = isInvitationOnly;
		return this;
	}

	public GroupBuilder thumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}

	public Group build() {
		Group group = new Group();

		group.title = title;
		group.description = description;
		group.snippet = snippet;
		group.tags = tags;
		group.phone = phone;
		group.access = access;
		group.sortField = sortField;
		group.sortOrder = sortOrder;
		group.isViewOnly = isViewOnly;
		group.isInvitationOnly = isInvitationOnly;
		group.thumbnail = thumbnail;

		return group;
	}
}
