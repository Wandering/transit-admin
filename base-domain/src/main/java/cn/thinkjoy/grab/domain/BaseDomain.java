package cn.thinkjoy.grab.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * 基类 IDEntity
 * <p/>
 * 创建时间: 2014年4月17日 下午2:06:32 <br/>
 *
 * @author ktian
 * @version
 * @since v0.0.1
 */
public class BaseDomain implements Serializable {

    protected static final long serialVersionUID = 6667600871541308622L;
    // ID
    private Integer     id;

    public Integer getId() {
            return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}