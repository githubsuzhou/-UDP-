package com.zhao.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_Server {
	/**
	 * 2.�����
	 * ����ServerSocket(��Ҫָ���˿ں�)
	 * ����ServerSocket��accept()��������һ���ͻ������󣬵õ�һ��SOcket
	 * ����socketgetInputStream()��getoutputStream()������ȡ�ͻ������ӵ�IO��
	 * ���������Զ�ȡ�ͻ��������д��������
	 * ���������д������д���ͻ��˵�������
	 */
	public Demo_Server() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(9876);
		Socket socket=server.accept(); //���ܿͻ��˵�����
		InputStream is= socket.getInputStream();  //��ȡ�����������
		OutputStream os=socket.getOutputStream(); //��ȡ����������
		os.write("�յ�����".getBytes());    //�������ͻ���д������
		byte [] arr=new byte[1024];
		int len=is.read(arr);   //��ȡ�ͻ��˷�����������
		System.out.println(new String(arr,0,len)); //���ַ�ת��Ϊ�ַ�������ӡ 
		socket.close(); 
	}

}
