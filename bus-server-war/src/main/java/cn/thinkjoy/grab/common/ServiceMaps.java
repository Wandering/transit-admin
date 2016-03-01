package cn.thinkjoy.grab.common;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by admin on 2016/2/29.
 */
@Service("grabServiceMaps")
public class ServiceMaps  extends BaseServiceMaps{
    @PostConstruct
    public void init(){
        super.init();
    }
}
