package cn.thinkjoy.grab.controller.admin;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.grab.common.ERRORCODE;
import cn.thinkjoy.grab.controller.BaseCommonController;
import cn.thinkjoy.grab.service.ILoginService;
import cn.thinkjoy.grab.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by admin on 2016/3/7.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController{
    @Autowired
    private ILoginService loginService;
    /**
     * 页面主请求
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/login")
    @ResponseBody
    public Object login(@RequestParam String username,@RequestParam String password){
        Map<String,Object> loginDomain=loginService.login(username);
        if(!loginDomain.containsKey("username")){
            throw new BizException(ERRORCODE.USERNAMEERROR.getCode(),ERRORCODE.USERNAMEERROR.getMessage());
        }
        if(loginDomain.containsKey("password")){
            if(password.equals(loginDomain.get("password"))){
                Map<String,Object> resultmap=new HashMap<>();
                resultmap.put("token",loginDomain.get("token"));
                resultmap.put("name",loginDomain.get("name"));
                resultmap.put("loginStatus",true);
                loginService.setLoginStatus(resultmap);
                return resultmap;
            }else {
                throw new BizException(ERRORCODE.PASSWORDERROR.getCode(),ERRORCODE.PASSWORDERROR.getMessage());
            }
        }

        return "false";
    }

    @RequestMapping(value="/logout")
    @ResponseBody
    public String loginout(@RequestParam String token){
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("loginStatus",false);
        loginService.setLoginStatus(map);
        return "true";
    }
}
