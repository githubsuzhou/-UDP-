package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class demo1_send {
	public demo1_send() {
		// TODO 自动生成的构造函数存根
	}
	/**1.send
	 * DatagramSocket   随机端口号，也可以在创建的时候添加地址，端口等
	 * 
	  *DatagramPacket  指定数据 ，长度，地址，端口
	  *使用DatagramSocket发送DatagramPacket
	  *关闭DatagramSocket
	 */
	public static void main(String[] args) throws Exception {
		  	String str="Hi,I'm socket ";  //要发的字节
			DatagramSocket socket=new  DatagramSocket();  //创建socket 相当于码头
			//创建packet ，相当于集装箱
			DatagramPacket p=new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 8008);
			socket.send(p); //发货（发送消息）
			socket.close(); //关闭socket ，因为他的底层是io流所以要关闭
	}

}
