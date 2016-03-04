/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  NewsService.java 2016-03-04 11:58:15 $
 */

package cn.thinkjoy.grab.service;
import cn.thinkjoy.grab.dao.IBaseDAO;
import cn.thinkjoy.grab.domain.BaseDomain;

public interface INewsService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{

}
