/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  NewsDAO.java 2016-03-04 11:58:15 $
 */
package cn.thinkjoy.grab.dao;

import java.util.Map;

public interface ILoginDAO{

    public Map<String,Object> login(String username);
    public int setLoginStatus(Map<String,Object> loginStatus);
}
