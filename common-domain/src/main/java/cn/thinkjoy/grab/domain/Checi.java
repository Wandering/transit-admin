/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  Checi.java 2016-02-05 10:49:23 $
 */





package cn.thinkjoy.grab.domain;

public class Checi extends BaseDomain<Long>{
    /** 名称 */
    private String name;

	public Checi(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
}

