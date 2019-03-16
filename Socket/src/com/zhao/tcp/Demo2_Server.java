package com.zhao.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo2_Server {
	/**
	 * 2.服务端
	 * 创建ServerSocket(需要指定端口号)
	 * 调用ServerSocket的accept()方法接收一个客户端请求，得到一个SOcket
	 * 调用socketgetInputStream()和getoutputStream()方法获取客户端连接的IO流
	 * 输入流可以读取客户端输出流写出的数据
	 * 输出流可以写出数据写到客户端的输入流
	 */
	public Demo2_Server() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) throws IOException {
		//demo(); 
		
		ServerSocket server=new ServerSocket(9876);
		while(true){
			final Socket socket=server.accept(); //接受客户端的请求
			//每连接一个客户端就创建一个线程
			new Thread(){
				public void run() {
				
					try {
						BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //将字节流转换为字符流
						PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream 里面有写出换行的方法
						ps.println("欢迎访问服务器！");
						 System.out.println( br.readLine());
						 ps.println("ok");
						socket.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					
				};
			}.start();
		
		}
	}

	
	
	private static void demo() throws IOException {
		ServerSocket server=new ServerSocket(9876);
		Socket socket=server.accept(); //接受客户端的请求
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //将字节流转换为字符流
		PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream 里面有写出换行的方法
		ps.println("欢迎访问服务器！");
		 System.out.println( br.readLine());
		 ps.println("ok");
		socket.close();
	}

}
