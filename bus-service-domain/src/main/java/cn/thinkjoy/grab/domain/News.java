/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  News.java 2016-03-04 11:58:15 $
 */





package cn.thinkjoy.grab.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

public class News extends BaseDomain<Long>{
    /** 热点标题 */
    private String title;
    /** 热点摘要 */
    private String subContent;
    /** 热点信息 */
    private String content;
    /** 热点时间 */
    private String hotdate;
    /** 类型（0/null是热点，1是头条） */
    private Integer type;

	public News(){
	}
    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }
    public void setSubContent(String value) {
        this.subContent = value;
    }

    public String getSubContent() {
        return this.subContent;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setHotdate(String value) {
        this.hotdate = value;
    }

    public String getHotdate() {
        return this.hotdate;
    }
    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("SubContent",getSubContent())
			.append("Content",getContent())
			.append("Hotdate",getHotdate())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof News == false) return false;
		if(this == obj) return true;
		News other = (News)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

