package top.chengyunlai.net.tcp.comprehensive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ClientDemo {
    public static void main(String[] args) throws Exception{
        // 创建客户端
        Socket socket = new Socket(InetAddress.getByName("192.168.0.111"),10086);

        // 往服务端发送信息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好".getBytes());

        // 仅仅关闭输出流
        socket.shutdownOutput();

        // 获取服务器发送的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine())!= null){
            System.out.println(line);
        }
        bufferedReader.close();
        outputStream.close();
        socket.close();
    }
}
