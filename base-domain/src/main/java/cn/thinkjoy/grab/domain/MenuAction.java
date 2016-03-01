/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  MenuAction.java 2016-03-01 11:11:38 $
 */





package cn.thinkjoy.grab.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

public class MenuAction extends BaseDomain<Integer>{
    /** 资源名称 */
    private String name;
    /** 描述 */
    private String description;
    /**  */
    private Integer menuId;
    /** 权限类型Id */
    private Integer actionId;

	public MenuAction(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }
    public void setMenuId(Integer value) {
        this.menuId = value;
    }

    public Integer getMenuId() {
        return this.menuId;
    }
    public void setActionId(Integer value) {
        this.actionId = value;
    }

    public Integer getActionId() {
        return this.actionId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("MenuId",getMenuId())
			.append("ActionId",getActionId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MenuAction == false) return false;
		if(this == obj) return true;
		MenuAction other = (MenuAction)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

