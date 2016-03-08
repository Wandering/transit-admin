package cn.thinkjoy.grab.common;

import cn.thinkjoy.grab.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by admin on 2016/2/29.
 */
@Service("grabServiceMaps")
public class ServiceMaps  extends BaseServiceMaps{
    @Autowired
    private INewsService newsService;
    @PostConstruct
    public void init(){
        super.init();
        serviceMap.put("news",newsService);
    }
}
