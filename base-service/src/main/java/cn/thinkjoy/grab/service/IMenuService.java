/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: grab
 * $Id:  MenuService.java 2016-02-29 16:38:19 $
 */

package cn.thinkjoy.grab.service;

import cn.thinkjoy.grab.dao.IBaseDAO;
import cn.thinkjoy.grab.domain.BaseDomain;

public interface IMenuService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{

}
