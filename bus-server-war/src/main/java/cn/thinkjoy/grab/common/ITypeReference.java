package cn.thinkjoy.grab.common;

import com.alibaba.fastjson.TypeReference;

/**
 * Created by admin on 2016/2/29.
 */
public interface ITypeReference {
    /**
     * 根据url获取requestT的泛型实例
     * @param url
     * @return
     */
    public TypeReference getTypeReference(String url);
}
