package com.zhao.hehe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class demo3_MoreThread {

	public demo3_MoreThread() {
		
	}

	public static void main(String[] args) {
		
			new Receive().start(); //启动接收线程
			new Send().start();  //启动发送线程
	}

}
class Send extends Thread{
	public void run(){
		try {
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
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}	
}
class Receive extends Thread {
	
		public void run() {
			
			try {
				DatagramSocket socket=new DatagramSocket(8008); //创建socket ，相当于接受的码头
				DatagramPacket p=new DatagramPacket(new byte[1024], 1024); //创建pack ，相当于集装箱，里面参数 相当于一个1024byte大小的箱子，里面有1024个格子
				while(true){
					socket.receive(p);//接受数据，接货
					byte[] arr=p.getData(); //获取p中的全部数据，包括空数据
					int len=p.getLength(); //获取p中接受到的有效的字节个数
					String ip=p.getAddress().getHostAddress();//获取ip地址
					int port=p.getPort(); //获取端口号
					//System.out.println(ip+"---------");
					System.out.println(ip+":"+port+":"+(new String(arr,0,len)));//输出字符串  arr，从0开始len结束
				}
			} catch (SocketException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
		}	
}