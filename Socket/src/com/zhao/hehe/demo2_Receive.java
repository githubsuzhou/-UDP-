
package com.zhao.hehe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ��ͷǿ
 *
 */
public class demo2_Receive {

	/**
	 2.Receive
	 * DatagramSocket   �ƶ���Ҫ���ܵĶ˿ں�
	 * 
	  *DatagramPacket  ָ������ �����ȣ���ַ���˿�
	  *ʹ��DatagramSocketʹ��DatagramPacket����
	  *�ر�DatagramSocket
	 */
	public demo2_Receive() {
		
	}

	
	public static void main(String[] args) throws Exception {
		DatagramSocket socket=new DatagramSocket(8008); //����socket ���൱�ڽ��ܵ���ͷ
		DatagramPacket p=new DatagramPacket(new byte[1024], 1024); //����pack ���൱�ڼ�װ�䣬������� �൱��һ��1024byte��С�����ӣ�������1024������
		while(true){
			socket.receive(p);//�������ݣ��ӻ�
			byte[] arr=p.getData(); //��ȡp�е�ȫ�����ݣ�����������
			int len=p.getLength(); //��ȡp�н��ܵ�����Ч���ֽڸ���
			String ip=p.getAddress().getHostAddress();//��ȡip��ַ
			int port=p.getPort(); //��ȡ�˿ں�
			//System.out.println(ip+"---------");
			System.out.println(ip+":"+port+":"+(new String(arr,0,len)));//����ַ���  arr����0��ʼlen����
		}
		
		//System.out.println("===================");
		//socket.close();
		

	}

}
