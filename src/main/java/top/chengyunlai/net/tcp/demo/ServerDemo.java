package top.chengyunlai.net.tcp.demo;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ServerDemo {
    public static void main(String[] args) throws Exception{
        // 创建服务器端的Socket对象(ServerSocket)
        ServerSocket serverSocket = new ServerSocket(10086);

        // Socket accept() 侦听要连接到此套接字并接受它
        Socket socket = serverSocket.accept();

        //获取输入流，读数据，并把数据显示在控制台
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        String data = new String(bytes,0,len);
        System.out.println("数据是：" + data);
        // 释放资源
        serverSocket.close();

    }
}
