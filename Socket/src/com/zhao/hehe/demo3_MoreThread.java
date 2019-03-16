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
		
			new Receive().start(); //���������߳�
			new Send().start();  //���������߳�
	}

}
class Send extends Thread{
	public void run(){
		try {
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
		} catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}	
}
class Receive extends Thread {
	
		public void run() {
			
			try {
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
			} catch (SocketException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			
		}	
}