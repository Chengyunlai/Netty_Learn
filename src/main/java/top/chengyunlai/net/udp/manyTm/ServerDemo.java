package top.chengyunlai.net.udp.manyTm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @ClassName
 * @Description 使用的是：MulticastSocket
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(10086);
        byte[] b = new byte[1024];
        DatagramPacket p = new DatagramPacket(b,b.length);
        // 把当前计算机绑定一个组播地址,表示添加到这一组中
        multicastSocket.joinGroup(InetAddress.getByName("224.0.1.0"));
        multicastSocket.receive(p);
        multicastSocket.close();
    }
}
