package com.zhao.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo2_Client {
	/***
	 * 1.�ͻ���
	 * ����Socket���ӷ�������ָ��ip��ַ���˿ںţ�ͨ��ip��ַ�Ҷ�Ӧ�ķ�����
	 * ����Socket��getInputStream()��getoutputStream()������ȡ�������ӵ�IO��
	 * ���������Զ�ȡ�����������д��������
	 * ���������д������д����������������
	 */

	public Demo2_Client() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public static void main(String[] args) throws Exception, IOException {
		Socket socket=new Socket("127.0.0.1", 9876);
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //���ֽ���ת��Ϊ�ַ���
		PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream ������д�����еķ���
		 System.out.println( br.readLine());  //readLine()����\r\nΪ������־��
		 ps.println("���ӣ�");
		 System.out.println( br.readLine());
		socket.close();
		
	}

	

}
