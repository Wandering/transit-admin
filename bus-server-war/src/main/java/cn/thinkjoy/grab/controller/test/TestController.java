package cn.thinkjoy.grab.controller.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/2/29.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/test")
public class TestController {
    /**
     * 页面主请求
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/datagroupdata")
    @ResponseBody
    public String renderMainView(){
        return "test";
    }
}
