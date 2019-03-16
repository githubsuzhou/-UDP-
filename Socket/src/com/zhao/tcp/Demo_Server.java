package com.zhao.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_Server {
	/**
	 * 2.服务端
	 * 创建ServerSocket(需要指定端口号)
	 * 调用ServerSocket的accept()方法接收一个客户端请求，得到一个SOcket
	 * 调用socketgetInputStream()和getoutputStream()方法获取客户端连接的IO流
	 * 输入流可以读取客户端输出流写出的数据
	 * 输出流可以写出数据写到客户端的输入流
	 */
	public Demo_Server() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(9876);
		Socket socket=server.accept(); //接受客户端的请求
		InputStream is= socket.getInputStream();  //获取服务端输入流
		OutputStream os=socket.getOutputStream(); //获取服务端输出流
		os.write("收到请求".getBytes());    //服务端向客户端写出数据
		byte [] arr=new byte[1024];
		int len=is.read(arr);   //读取客户端发过来的数据
		System.out.println(new String(arr,0,len)); //将字符转换为字符串并打印 
		socket.close(); 
	}

}
