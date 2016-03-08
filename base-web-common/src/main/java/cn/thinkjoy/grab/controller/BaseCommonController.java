/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-30 13:27:41 $
 */

package cn.thinkjoy.grab.controller;


import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.grab.common.BaseServiceMaps;
import cn.thinkjoy.grab.common.ERRORCODE;
import cn.thinkjoy.grab.common.MenuUtils;
import cn.thinkjoy.grab.common.SqlOrderEnum;
import cn.thinkjoy.grab.domain.BizData4Page;
import cn.thinkjoy.grab.domain.Menu;
import cn.thinkjoy.grab.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseCommonController<T extends IBaseService>{

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private Map<String,Object> checkMap;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,
                             HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public BizData4Page createBizData4Page(T service, Map<String,Object> selector,Map<String, Object> conditions, int curPage, int offset, int rows){

        String orderBy = null;
        String sortBy = null;
        conditions.put("groupOp","and");
        if (conditions.containsKey("orderBy")) {
            orderBy = conditions.get("orderBy").toString();
        }else {
            orderBy="id";
        }
        if (conditions.containsKey("sortBy")) {
            sortBy = conditions.get("sortBy").toString();
        }else {
            sortBy="desc";
        }
        enhanceSearchFilter(conditions);
        List mainData = service.queryPage(conditions, offset, rows, orderBy, SqlOrderEnum.valueOf(sortBy.toUpperCase()));
        mainData=(List)enhanceStateTransition(mainData);
        int records = service.count(conditions);

        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        int pagesize=0;
        if(mainData!=null) {
            pagesize = mainData.size();
        }
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setPagesize(pagesize);
        bizData4Page.setTotal(total);

        return bizData4Page;
    }

    protected BizData4Page doPage(Map<String, Object> conditions,T service,Integer page,Integer rows){
        return doPage(null,conditions,service,page, rows);
    }

    protected BizData4Page doPage(Map<String,Object> selector,Map<String, Object> conditions,T service,Integer page,Integer rows){
        if(page==null){
            page = 1;
        }
        if(rows==null){
            rows = 10;
        }
        enhanceSortBy(conditions);
        enhanceSelectorBy(selector);
        return createBizData4Page(service,selector,conditions, page, (page - 1) * rows, rows);
    }

    /**
     * 增强 或 改变 过滤条件内容
     * @param conditions
     */
    protected void enhanceSelectorBy(Map<String, Object> selector) {
    }
    /**
     * 增强 或 改变 过滤条件内容
     * @param conditions
     */
    protected void enhanceSearchFilter(Map<String, Object> conditions) {
    }

    /**
     * 增强查询条件
     * @param conditions
     */
    protected void enhanceSortBy(Map<String, Object> conditions) {
    }

    /**
     * domain转换
     * @param conditions
     */
    protected Object enhanceStateTransition(List conditions) {
        return conditions;
    }


    protected T isNull(T o){
        if(o==null){
            throw new BizException(ERRORCODE.RESOURCEISNULL.getCode(),ERRORCODE.RESOURCEISNULL.getMessage());
        }
        return o;
    }
    protected void idIsNull(String o){
        if(o==null || "".equals(o)){
            throw new BizException(ERRORCODE.IDISNOTNULL.getCode(),ERRORCODE.IDISNOTNULL.getMessage());
        }
    }


    /**
     * 数据校验
     * @param dataMap 页面提交字段
     */
    public void paramCheck(Map<String,Object> dataMap){
        if(this.getCheckMap()==null){
            return;
        }
        Iterator<String> iterator=this.getCheckMap().keySet().iterator();

        while (iterator.hasNext()){
            String key=iterator.next();
            if(!dataMap.containsKey(key)) {
                throw new BizException("error","缺少"+this.getCheckMap().get(key)+","+key+"参数");
            }
        }
    }


    public Map<String, Object> getCheckMap() {
        return checkMap;
    }

    public void setCheckMap(Map<String, Object> checkMap) {
        this.checkMap = checkMap;
    }
}
