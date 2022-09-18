## 概念
**计算机网络**
是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来，在网络操作系统，网络管理软件及网络通信协议的管理和协调下，实现**资源共享**和**信息传递**的计算机系统。

**网络编程**
在网络通信协议下，不同计算机上运行的程序，可以进行数据传输。

**网络编程三要素**  

- IP地址

要想让网络中的计算机能够互相通信，必须**为每台计算机指定一个标识号**，通过这个标识号来指定 要接收数据的计算机和识别发送的计算机，而IP地址就是这个标识号。也就是设备的标识  

- 端口

 **设备上应用程序的唯一标识**。网络的通信，**本质上是两个应用程序的通信**。每台计算机都有很多的应用程序，那么在网络通信时，如何**区分这些应用程序**呢？如果说IP地址可以唯一标识网络中的设备，那么端口号就可以唯一 标识设备中的应用程序了。也就是**应用程序的标识**。
端口号的取值范围：0~65535；其中0~1023之间的端口用于一些知名的网络服务和应用，普通的应用程序需要使用1024以上的端口号。

- 协议

通过计算机网络可以使多台计算机实现连接，位于**同一个网络中的计算机在进行连接和通信时需要遵守一定的规则**，这就好比在道路中行驶的汽车一定要遵守交通规则一样。在计算机网络中，这些连接和通信的规则被称为网络通信协议，它对数据的传输格式、传输速率、传输步骤等做了统一规定，**通信双方必须同时遵守才能完成数据交换**。常见的协议有**UDP协议**和**TCP协议**。
**UDP：** 用户数据报协议(User Datagram Protocol)  
UDP是无连接通信协议，即在数据传输时，数据的发送端和接收端不建立逻辑连接。
简单来 说，当一台计算机向另外一台计算机发送数据时，发送端不会确认接收端是否存在，就会发出数据，同样接收端在收到数据时，也不会向发送端反馈是否收到数据。
由于使用UDP协议消耗系统资源小，通信效率高，所以通常都**会用于音频**、**视频**和**普通数据的传输**，例如视频会议通常采用UDP协议，因为这种情况即使偶尔丢失一两个数据包，也不会对接收 结果产生太大影响。但是在使用UDP协议传送数据时，由于UDP的面向无连接性，不能保证数据的完整性，因此在传输重要数据时不建议使用UDP协议。

**TCP：** 传输控制协议 (Transmission Control Protocol) 
TCP协议是面向连接的通信协议，即传输数据之前，①在发送端和接收端建立逻辑连接，②然后再传输数据，它提供了两台计算机之间可靠无差错的数据传输。
在TCP连接中必须要**明确**客户端与服务器端，由客户端向服务端发出连接请求，每次连接的创建都需要经过“三次握手”。
三次握手： TCP协议中，在发送数据的准备阶段，客户端与服务器之间的三次交互，以保证连接的可靠。

   1. 第一次握手，客户端向服务器端发出连接请求，等待服务器确认。
   1. 第二次握手，服务器端向客户端回送一个响应，通知客户端收到了连接请求。
   1. 第三次握手，客户端再次向服务器端发送确认信息，确认连接  

完成三次握手，连接建立后，客户端和服务器就可以开始进行数据传输了。由于这种面向连接的特性，TCP协议**可以保证传输数据的安全**，所以**应用十分广泛。例如上传文件、下载文 件、浏览网页等 **。
## 应用
### InetAddress类
| 方法名 | 说明 | 示例 |
| --- | --- | --- |
| static InetAddress **getByName**(String host)   | 通过host获得一个InetAddress对象，用来确定主机名称的IP地址。主机名称可以是机器名称，也 可以是IP地址   | InetAddress address = InetAddress.getByName("172.18.45.50"); |
| String **getHostName**()   | 获取此IP地址的主机名   | address.getHostName() |
|  String **getHostAddress**() | 返回文本显示中的IP地址字符串   | address.getHostAddress() |

```java
package top.chengyunlai.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName
 * @Description 学习InetAddress类
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class InetAddressDemo {
    public static void main(String[] args) {
        // 创建类的对象
        InetAddress address = null;
        try {
            // 调用getByName方法
            address = InetAddress.getByName("172.18.45.50");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //public String getHostName()：获取此IP地址的主机名
        String name = address.getHostName();
        //public String getHostAddress()：返回文本显示中的IP地址字符串
        String ip = address.getHostAddress();
        System.out.println("主机名：" + name);
        System.out.println("IP地址：" + ip);
    }
}

```
### UDP通信 - DatagramSocket类
> UDP协议是一种不可靠的网络协议，它**在通信的两端各建立一个Socket对象**，但是这**两个 Socket只是发送，接收数据的对象**，因此对于基于UDP协议的通信双方而言，**没有所谓的客户 端和服务器的概念。**
> Java提供了**DatagramSocket类**作为基于UDP协议的Socket  

#### 发送
构造方法：

| 方法名 | 说明 |
| --- | --- |
| DatagramSocket() | 创建数据报套接字并将其绑定到本机地址上的任何可用端口 |
| DatagramPacket(byte[] buf,int len,InetAddress add,int port)   | 创建数据包,发送长度为len的数据包到指定主 机的指定端口 |

相关方法

| 方法名 | 说明 | 示例 |
| --- | --- | --- |
|  void send(DatagramPacket p)  | 发送数据报包 |  |
|  void close() | 关闭数据报套接字 |  |
|  void receive(DatagramPacket p)   | 从此套接字接受数据报包 |  |

发送数据的步骤：

1. 创建发送端的Socket对象(DatagramSocket) 
1. 创建数据，并把数据打包
1. 调用DatagramSocket对象的方法发送数据
1. 关闭发送端
```java
package top.chengyunlai.net;

import java.io.IOException;
import java.net.*;

/**
 * @ClassName
 * @Description 提供了DatagramSocket类作为基于UDP协议的Socket;发送方
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DatagramSocketUDP_Provide {
    public static void main(String[] args) throws IOException {
        // 获取xx主机的地址
        InetAddress address = InetAddress.getByName("172.18.45.50");
        // 创建发送端的Socket对象(DatagramSocket)
        DatagramSocket provide = new DatagramSocket();
        // 构造一个数据包，发送长度为 length的数据包到指定主机上的指定端口号。
        byte[] bys = "hello,我来了".getBytes();
        DatagramPacket dp = new DatagramPacket(bys,bys.length,address,10086);
        // 调用DatagramSocket对象的方法发送数据
        provide.send(dp);
        //关闭发送端
        //void close() 关闭此数据报套接字
        provide.close();
    }

}
```
#### 接收
构造方法：

|  方法名 | 说明 |
| --- | --- |
| DatagramSocket() | 创建数据报套接字并将其绑定到本机地址上的任何可用端口 |
| DatagramPacket(byte[] buf, int len)   | 创建一个DatagramPacket用于接收长度为len的数据包   |

相关方法：

| 方法名 | 说明 | 示例 |
| --- | --- | --- |
|  byte[] getData()   | 返回数据缓冲区   |  |
|  int getLength()   | 返回要发送的数据的长度或接收的数据的长度   |  |

```java
package top.chengyunlai.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName
 * @Description 接收方
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DatagramSocketUDP_Receive {
    public static void main(String[] args) throws IOException {
        // 创建接收端的Socket对象(DatagramSocket),表明自己的端口号
        DatagramSocket provide = new DatagramSocket(10086);
        
        // 构造一个数据包，用于接收数据
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys,bys.length);
        
        // 调用DatagramSocket对象的方法发送数据
        provide.receive(dp);

        //解析数据包，并把数据在控制台显示
        System.out.println("数据是：" + new String(dp.getData(), 0, dp.getLength()));

        //关闭发送端
        //void close() 关闭此数据报套接字
        provide.close();
    }
}
```
#### 综合应用
案例需求

- UDP发送数据：数据来自于键盘录入，直到输入的数据是886，发送数据结束
- UDP接收数据：因为接收端不知道发送端什么时候停止发送，故采用死循环接收

代码实现：
【发送方】
```java
package top.chengyunlai.net.comprehensive;

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
```
【接收方】
```java
package top.chengyunlai.net.comprehensive;

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
```
#### UDP的三种通讯方式

- 单播

 单播用于**两个主机之间**的端对端通信 （一对一）

- 组播

 组播用于对**一组特定的主机**进行通信  （一对多）

- 广播

 广播用于一**个主机对整个局域网上所有主机**上的数据通信 

以上已经实现了单播的方式。接下去了解一下**UDP组播**的实现方式：
实现步骤
发送端

   1. 创建发送端的Socket对象(DatagramSocket)
   1. 创建数据，并把数据打包(DatagramPacket)
   1. 调用DatagramSocket对象的方法发送数据(在单播中,这里是发给指定IP的电脑但是在组
播当中,这里是发给组播地址)
   1. 释放资源
```java
package top.chengyunlai.net.manyTm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName
 * @Description 组播的发送方
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ClinetDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        byte[] b = "你好，组播".getBytes();
        // 在组播当中,这里是发给组播地址
        DatagramPacket p = new DatagramPacket(b,b.length, InetAddress.getByName("224.0.1.0"),10086);
        datagramSocket.send(p);
        datagramSocket.close();
    }
}
```
接收端

   5. 创建接收端Socket对象(MulticastSocket)
   5. 创建一个箱子,用于接收数据
   5. 把当前计算机绑定一个组播地址
   5. 将数据接收到箱子中
   5. 解析数据包,并打印数据
   5. 释放资源
```java
package top.chengyunlai.net.manyTm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

```

了解一下**UDP广播**的实现方式：

实现步骤
发送端

   1. 创建发送端Socket对象(DatagramSocket)
   1. 创建存储数据的箱子,将广播地址封装进去
   1. 发送数据
   1. 释放资源
```java
package top.chengyunlai.net.all;

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
```
接收端

   5. 创建接收端的Socket对象(DatagramSocket)
   5. 创建一个数据包，用于接收数据
   5. 调用DatagramSocket对象的方法接收数据
   5. 解析数据包，并把数据在控制台显示
   5. 关闭接收端
```java
package top.chengyunlai.net.all;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
```
### TCP通信 - Socket类
> Java为客户端提供了Socket类，为服务器端提供了ServerSocket类

Java对基于TCP协议的的网络提供了良好的封装，使用Socket对象来代表两端的通信端口，并通过Socket产生IO流来进行网络通信。  

构造方法

| **方法名** | **说明** |
| --- | --- |
| Socket(InetAddress address,int port)   | 创建流套接字并将其连接到指定IP指定端口号   |
| Socket(String host, int port)   | 创建流套接字并将其连接到指定主机上的指定端口号   |


相关方法

| **方法名** | **说明** |
| --- | --- |
|  InputStream getInputStream()   |  返回此套接字的输入流 |
|  OutputStream getOutputStream()  |  返回此套接字的输出流 |

#### 客户端示例代码
```java
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
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),10086);
        // 返回此套接字的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello TCP".getBytes());
        // 释放资源
        socket.close();
    }
}
```
#### 服务器示例代码
构造方法

| **方法名** | **说明** |
| --- | --- |
|  ServletSocket(int port)   |  创建绑定到指定端口的服务器套接字   |

相关方法

| **方法名** | **说明** |
| --- | --- |
|  Socket accept()  |  监听要连接到此的套接字并接受 它 |

注意事项

1. accept方法是阻塞的,作用就是等待客户端连接
1. 客户端创建对象并连接服务器,此时是通过三次握手协议,保证跟服务器之间的连接
1. 针对客户端来讲,是往外写的,所以是输出流 针对服务器来讲,是往里读的,所以是输入流
1. read方法也是阻塞的
1. 客户端在关流的时候,还多了一个往服务器写结束标记的动作
1. 最后一步断开连接,通过四次挥手协议保证连接终止
```java
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
```
#### 案例-接收和反馈
案例需求

- 客户端：发送数据，接受服务器反馈
- 服务器：收到消息后给出反馈

案例分析

- 客户端创建对象，使用输出流输出数据 服务端创建对象，使用输入流接受数据。
- 服务端使用输出流给出反馈数据 客户端使用输入流接受反馈数据  

客户端
```java
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
```

服务器
```java
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
```
#### 案例-TCP文件上传
 案例需求

- 客户端：传输文件给服务器，上传完毕之后服务器会给与反馈，客户端接收服务器反馈。
- 服务器：接收到的数据写入本地文件，并给出反馈。

案例分析

- 创建客户端对象，创建输入流对象指向文件，每读一次数据就给服务器输出一次数据，输出 结束后使用shutdownOutput()方法告知服务端传输结束
- 创建服务器对象，创建输出流对象指向文件，每接受一次数据就使用输出流输出到文件中， 传输结束后。使用输出流给客户端反馈信息。
- 客户端接受服务端的回馈信息。

相关方法

| **方法名** | **说明** |
| --- | --- |
|  void shutdownInput() | 将此套接字的输入流放置在“流的末尾” |
|  void shutdownOutput() | 告诉服务器文件已经传输完毕 |

客户端
```java
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
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("开发.jpg"));
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
```
服务端
```java
package top.chengyunlai.net.tcp.file;

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
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(10086);
        Socket accept = ss.accept();
        //网络中的流,从客户端读取数据的
        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        //本地的IO流,把数据写到本地中,实现永久化存储
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.jpg"));
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        BufferedWriter bw = new BufferedWriter(new
                OutputStreamWriter(accept.getOutputStream()));
        bw.write("上传成功");
        bw.newLine();
        bw.flush();
        bos.close();
        accept.close();
        ss.close();
    }
}

```
