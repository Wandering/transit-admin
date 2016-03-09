/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  Menu.java 2016-03-01 11:11:36 $
 */





package cn.thinkjoy.grab.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

public class Menu extends BaseDomain{
    /** 0前端1admin */
    private Boolean type;
    /** 页面url */
    private String url;
    /** 顺序 */
    private Integer orderNum;
    /** 父页面,null为顶层页面 */
    private Integer parentId;
    /** 编码 */
    private String number;
    /** 长编码 */
    private String longNumber;
    /** 资源名称 */
    private String name;
    /** 描述 */
    private String description;

	public Menu(){
	}
    public void setType(Boolean value) {
        this.type = value;
    }

    public Boolean getType() {
        return this.type;
    }
    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return this.url;
    }
    public void setOrderNum(Integer value) {
        this.orderNum = value;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    public void setNumber(String value) {
        this.number = value;
    }

    public String getNumber() {
        return this.number;
    }
    public void setLongNumber(String value) {
        this.longNumber = value;
    }

    public String getLongNumber() {
        return this.longNumber;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Type",getType())
			.append("Url",getUrl())
			.append("OrderNum",getOrderNum())
			.append("ParentId",getParentId())
			.append("Number",getNumber())
			.append("LongNumber",getLongNumber())
			.append("Name",getName())
			.append("Description",getDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

