/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  RolePower.java 2016-03-01 11:11:38 $
 */





package cn.thinkjoy.grab.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

public class RolePower extends BaseDomain<Integer>{
    /** 角色名称 */
    private String name;
    /** 描述 */
    private String description;
    /**  */
    private Integer roleId;
    /**  */
    private Integer powerId;

	public RolePower(){
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
    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    public Integer getRoleId() {
        return this.roleId;
    }
    public void setPowerId(Integer value) {
        this.powerId = value;
    }

    public Integer getPowerId() {
        return this.powerId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("RoleId",getRoleId())
			.append("PowerId",getPowerId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RolePower == false) return false;
		if(this == obj) return true;
		RolePower other = (RolePower)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

