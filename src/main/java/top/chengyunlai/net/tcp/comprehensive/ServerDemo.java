package top.chengyunlai.net.tcp.comprehensive;

import java.io.*;
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
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10086);

        // 获取客户端发来的数据
        // 1.先接收数据
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        String data = new String(bytes,0,len);
        System.out.println("数据是：" + data);
        System.out.println("服务器准备回复中...");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bw.write("你好客户端");
        bw.newLine();
        bw.flush();

        bw.close();
        inputStream.close();
        socket.close();
        serverSocket.close();



    }
}
