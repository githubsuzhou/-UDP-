package com.zhao.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo2_Client {
	/***
	 * 1.客户端
	 * 创建Socket连接服务器（指定ip地址，端口号）通过ip地址找对应的服务器
	 * 调用Socket的getInputStream()和getoutputStream()方法获取服务连接的IO流
	 * 输入流可以读取服务器输出流写出的数据
	 * 输出流可以写出数据写到服务器的输入流
	 */

	public Demo2_Client() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) throws Exception, IOException {
		Socket socket=new Socket("127.0.0.1", 9876);
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //将字节流转换为字符流
		PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream 里面有写出换行的方法
		 System.out.println( br.readLine());  //readLine()是以\r\n为结束标志的
		 ps.println("连接！");
		 System.out.println( br.readLine());
		socket.close();
		
	}

	

}
