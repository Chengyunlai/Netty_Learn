package top.chengyunlai.net.udp.all;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        byte[] b = new byte[1024];
        DatagramPacket p = new DatagramPacket(b,b.length);
        datagramSocket.receive(p);
        datagramSocket.close();
    }
}
