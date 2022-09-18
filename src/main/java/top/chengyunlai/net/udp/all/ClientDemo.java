package top.chengyunlai.net.udp.all;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        byte[] b = "你好，广播".getBytes();
        // 在组播当中,这里是发给组播地址
        DatagramPacket p = new DatagramPacket(b,b.length, InetAddress.getByName("255.255.255.255"),10086);
        datagramSocket.send(p);
        datagramSocket.close();
    }
}
