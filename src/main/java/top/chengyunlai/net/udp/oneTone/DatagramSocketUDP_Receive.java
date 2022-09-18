package top.chengyunlai.net.udp.oneTone;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ClassName
 * @Description 接收方
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DatagramSocketUDP_Receive {
    public static void main(String[] args) throws IOException {
        // 创建接收端的Socket对象(DatagramSocket)
        DatagramSocket provide = new DatagramSocket(10086);
        // 构造一个数据包，用于接收数据
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys,bys.length);
        // 调用DatagramSocket对象的方法发送数据
        provide.receive(dp);

        //解析数据包，并把数据在控制台显示
        System.out.println("数据是：" + new String(dp.getData(), 0, dp.getLength()));

        //关闭发送端
        //void close() 关闭此数据报套接字
        provide.close();
    }
}
