/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-30 13:27:41 $
 */

package cn.thinkjoy.grab.controller;


import cn.thinkjoy.grab.common.BaseServiceMaps;
import cn.thinkjoy.grab.common.MenuUtils;
import cn.thinkjoy.grab.common.QueryUtil;
import cn.thinkjoy.grab.common.StringUtil;
import cn.thinkjoy.grab.domain.BizData4Page;
import cn.thinkjoy.grab.domain.Menu;
import cn.thinkjoy.grab.service.IBaseService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
@Controller
@RequestMapping(value="/common")
public class CommonController extends BaseCommonController{

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

    /**
     * 菜单类
     */
    @RequestMapping(value="/{mainObj}/getDataByPage")
    @ResponseBody
    public BizData4Page getDataByPage(@PathVariable String mainObj,
                                      @RequestParam(defaultValue = "1",required = false) Integer page,
                                      @RequestParam(defaultValue = "10",required = false) Integer rows) {

        Map<String,Object> map = getRequestParam(null);
        BizData4Page bizData4Page = this.doPage(map,baseServiceMaps.get(mainObj), page, rows);
        return bizData4Page;
    }


    public Map<String, Object> getRequestParam(Map<String, Object> dataMap){
        //TODO   支持多对象保存
        if(dataMap==null) {
            dataMap = Maps.newHashMap();
        }
        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            String param=request.getParameter(prop);
            if(!StringUtil.isNulOrBlank(param)) {
                QueryUtil.setMapOp(dataMap, prop, "=", param);
            }
        }
        paramCheck(dataMap);
        return dataMap;
    }
}
