package top.chengyunlai.net.udp.oneTone;

import java.io.IOException;
import java.net.*;

/**
 * @ClassName
 * @Description 提供了DatagramSocket类作为基于UDP协议的Socket;发送方
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DatagramSocketUDP_Provide {
    public static void main(String[] args) throws IOException {
        // 获取xx主机的地址
        InetAddress address = InetAddress.getByName("172.18.45.50");
        // 创建发送端的Socket对象(DatagramSocket)
        DatagramSocket provide = new DatagramSocket();
        // 构造一个数据包，发送长度为 length的数据包到指定主机上的指定端口号。
        byte[] bys = "hello,我来了".getBytes();
        DatagramPacket dp = new DatagramPacket(bys,bys.length,address,10086);
        // 调用DatagramSocket对象的方法发送数据
        provide.send(dp);
        //关闭发送端
        //void close() 关闭此数据报套接字
        provide.close();
    }

}
