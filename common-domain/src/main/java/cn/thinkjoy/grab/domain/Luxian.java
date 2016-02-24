/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  Luxian.java 2016-02-05 10:49:25 $
 */





package cn.thinkjoy.grab.domain;

public class Luxian extends BaseDomain<Integer>{
    /** 排序 */
    private String paixu;
    /** 站名 */
    private String zhanming;
    /**  */
    private Integer pid;

	public Luxian(){
	}
    public void setPaixu(String value) {
        this.paixu = value;
    }

    public String getPaixu() {
        return this.paixu;
    }
    public void setZhanming(String value) {
        this.zhanming = value;
    }

    public String getZhanming() {
        return this.zhanming;
    }
    public void setPid(Integer value) {
        this.pid = value;
    }

    public Integer getPid() {
        return this.pid;
    }

}

