package top.chengyunlai.net.tcp.file;

import java.io.*;
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
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("192.168.0.111"),10086);
        // 本地流，读取本地文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\xxx\\Desktop\\Netty\\src\\main\\resources\\开发.jpg"));
        // 写到服务器
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);

        int b;
        while((b = bis.read())!=-1){
            bos.write(b);//通过网络写到服务器中
        }
        bos.flush();
        //给服务器一个结束标记,告诉服务器文件已经传输完毕
        socket.shutdownOutput();

        BufferedReader br = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
        String line;
        while((line = br.readLine()) !=null){
            System.out.println(line);
        }
        bis.close();
        socket.close();
    }
}
