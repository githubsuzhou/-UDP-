package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class demo1_send {
	public demo1_send() {
		// TODO �Զ����ɵĹ��캯�����
	}
	/**1.send
	 * DatagramSocket   ����˿ںţ�Ҳ�����ڴ�����ʱ����ӵ�ַ���˿ڵ�
	 * 
	  *DatagramPacket  ָ������ �����ȣ���ַ���˿�
	  *ʹ��DatagramSocket����DatagramPacket
	  *�ر�DatagramSocket
	 */
	public static void main(String[] args) throws Exception {
		  	String str="Hi,I'm socket ";  //Ҫ�����ֽ�
			DatagramSocket socket=new  DatagramSocket();  //����socket �൱����ͷ
			//����packet ���൱�ڼ�װ��
			DatagramPacket p=new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 8008);
			socket.send(p); //������������Ϣ��
			socket.close(); //�ر�socket ����Ϊ���ĵײ���io������Ҫ�ر�
	}

}
