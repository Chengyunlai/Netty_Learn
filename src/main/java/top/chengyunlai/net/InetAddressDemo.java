package top.chengyunlai.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName
 * @Description 学习InetAddress类
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class InetAddressDemo {
    public static void main(String[] args) {
        //InetAddress address = InetAddress.getByName("itheima");
        InetAddress address = null;
        try {
            address = InetAddress.getByName("172.18.45.50");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //public String getHostName()：获取此IP地址的主机名
        String name = address.getHostName();
        //public String getHostAddress()：返回文本显示中的IP地址字符串
        String ip = address.getHostAddress();
        System.out.println("主机名：" + name);
        System.out.println("IP地址：" + ip);
    }
}
