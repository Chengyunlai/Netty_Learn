package top.chengyunlai.net.udp.comprehensive;

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
public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        // 指定接收方的端口
        DatagramSocket datagramSocket = new DatagramSocket(10086);

        while (true){
            byte[] bytes = new byte[1024];
            DatagramPacket p = new DatagramPacket(bytes,bytes.length);
            datagramSocket.receive(p);
            System.out.println("接收的数据是: " + new String(p.getData(),0,p.getLength()));
        }
    }
}
