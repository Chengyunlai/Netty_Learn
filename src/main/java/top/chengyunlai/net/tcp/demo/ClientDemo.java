package top.chengyunlai.net.tcp.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        // 创建客户端Socket对象
        Socket socket = new Socket(InetAddress.getByName("172.18.45.50"),10086);
        // 返回此套接字的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello TCP".getBytes());
        // 释放资源
        socket.close();
    }
}
