package cn.thinkjoy.grab.service;

import java.util.Map;

/**
 * Created by admin on 2016/3/31.
 */
public interface ILoginService {
    public Map<String,Object> login(String username);
    public int setLoginStatus(Map<String,Object> loginStatus);
}
