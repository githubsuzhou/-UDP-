/**
 * 
 */
package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author 光头强
 *
 */
public class demo1_Receive {

	/**
	 2.Receive
	 * DatagramSocket   制定他要接受的端口号
	 * 
	  *DatagramPacket  指定数据 ，长度，地址，端口
	  *使用DatagramSocket使用DatagramPacket接受
	  *关闭DatagramSocket
	 */
	public demo1_Receive() {
		
	}

	
	public static void main(String[] args) throws Exception {
		DatagramSocket socket=new DatagramSocket(8008); //创建socket ，相当于接受的码头
		DatagramPacket p=new DatagramPacket(new byte[1024], 1024); //创建pack ，相当于集装箱，里面参数 相当于一个1024byte大小的箱子，里面有1024个格子
		socket.receive(p);//接受数据，接货
		byte[] arr=p.getData(); //获取p中的全部数据，包括空数据
		int len=p.getLength(); //获取p中接受到的有效的字节个数
		System.out.println(new String(arr,0,len));//输出字符串  arr，从0开始len结束
		socket.close();
		

	}

}
