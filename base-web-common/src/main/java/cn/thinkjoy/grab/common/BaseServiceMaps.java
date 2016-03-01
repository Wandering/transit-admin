package cn.thinkjoy.grab.common;

import cn.thinkjoy.grab.service.IBaseService;
import cn.thinkjoy.grab.service.IMenuService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by admin on 2016/2/29.
 */
public class BaseServiceMaps {
    @Autowired
    private IMenuService menuService;
    protected final Map<String, IBaseService> serviceMap = Maps.newHashMap();

    protected void init(){
        //key 全部为小写
        serviceMap.put("menuservice",menuService);
    }

    public IBaseService get(String mainObj){
        return serviceMap.get(mainObj);
    }
}
