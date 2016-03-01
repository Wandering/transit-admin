/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-30 13:27:41 $
 */

package cn.thinkjoy.grab.controller;


import cn.thinkjoy.grab.common.BaseServiceMaps;
import cn.thinkjoy.grab.common.MenuUtils;
import cn.thinkjoy.grab.domain.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
@Controller
@RequestMapping(value="/common/grab")
public class CommonController<T>{

    @Autowired
    BaseServiceMaps baseServiceMaps;

    /**
     * 菜单类
     */
    @RequestMapping(value="/getMenu")
    @ResponseBody
    public List getMenu() {
        List<Menu> menuList = (List) baseServiceMaps.get("menuservice").findAll();
        return MenuUtils.getTreeMenu(menuList);
    }
}
