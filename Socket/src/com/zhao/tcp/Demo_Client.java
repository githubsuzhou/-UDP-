package com.zhao.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo_Client {
	/***
	 * 1.客户端
	 * 创建Socket连接服务器（指定ip地址，端口号）通过ip地址找对应的服务器
	 * 调用Socket的getInputStream()和getoutputStream()方法获取服务连接的IO流
	 * 输入流可以读取服务器输出流写出的数据
	 * 输出流可以写出数据写到服务器的输入流
	 */

	public Demo_Client() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) throws Exception, IOException {
		Socket socket=new Socket("127.0.0.1", 9876);
		InputStream is= socket.getInputStream();  //获取客户端输入流
		OutputStream os=socket.getOutputStream(); //获取客户端输出流
		
		byte [] arr=new byte[1024];
		int len=is.read(arr);
		System.out.println(new String(arr,0,len)); //将字符转换为字符串并打印 
		os.write("好的，开始通信。。。。。".getBytes());
		socket.close();
		
	}

}
