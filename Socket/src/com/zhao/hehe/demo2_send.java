package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class demo2_send {
	public demo2_send() {
		
	}
	/**1.send
	 * DatagramSocket   随机端口号，也可以在创建的时候添加地址，端口等
	 * 
	  *DatagramPacket  指定数据 ，长度，地址，端口
	  *使用DatagramSocket发送DatagramPacket
	  *关闭DatagramSocket
	 */
	public static void main(String[] args) throws Exception {
		
			DatagramSocket socket=new  DatagramSocket();  //创建socket 相当于码头
			Scanner sc=new Scanner(System.in);// 创建键盘录入对象		
		  	//String str="Hi,I'm socket ";  //要发的字节
			
			while (true) {
				String line=sc.nextLine(); //获取键盘录入的字符串
				if("quit".equals(line)){
					break;
				}
				//创建packet ，相当于集装箱
				DatagramPacket p=new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName("127.0.0.1"), 8008);
				socket.send(p); //发货（发送消息）
			}
			socket.close();
			System.out.println("----------------");
	}

}
