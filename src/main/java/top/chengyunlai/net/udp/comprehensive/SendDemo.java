package top.chengyunlai.net.udp.comprehensive;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @ClassName
 * @Description UDP发送数据：数据来自于键盘录入，直到输入的数据是886，发送数据结束
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class SendDemo {
    public static void main(String[] args) throws IOException {
        // 创建发送发送端的Socket对象
        DatagramSocket ds = new DatagramSocket();
        // 创建键盘输入对象
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            // 判断如果输入的数据是886，表示发送数据结束
            if ("886".equals(s)){
                break;
            }
            byte[] b = s.getBytes();
            DatagramPacket p = new DatagramPacket(b,0,b.length, InetAddress.getByName("172.18.45.50"),10086);
            ds.send(p);
        }
    }
}
