package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class demo2_send {
	public demo2_send() {
		
	}
	/**1.send
	 * DatagramSocket   ����˿ںţ�Ҳ�����ڴ�����ʱ����ӵ�ַ���˿ڵ�
	 * 
	  *DatagramPacket  ָ������ �����ȣ���ַ���˿�
	  *ʹ��DatagramSocket����DatagramPacket
	  *�ر�DatagramSocket
	 */
	public static void main(String[] args) throws Exception {
		
			DatagramSocket socket=new  DatagramSocket();  //����socket �൱����ͷ
			Scanner sc=new Scanner(System.in);// ��������¼�����		
		  	//String str="Hi,I'm socket ";  //Ҫ�����ֽ�
			
			while (true) {
				String line=sc.nextLine(); //��ȡ����¼����ַ���
				if("quit".equals(line)){
					break;
				}
				//����packet ���൱�ڼ�װ��
				DatagramPacket p=new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName("127.0.0.1"), 8008);
				socket.send(p); //������������Ϣ��
			}
			socket.close();
			System.out.println("----------------");
	}

}
