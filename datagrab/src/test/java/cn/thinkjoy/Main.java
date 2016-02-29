package cn.thinkjoy;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by admin on 2016/2/29.
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getByName("www.baidu.com").getHostAddress());
            System.out.println(InetAddress.getByName("mc.zk.thinkjoy.cn").getHostAddress());
            System.out.println(Inet4Address.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
