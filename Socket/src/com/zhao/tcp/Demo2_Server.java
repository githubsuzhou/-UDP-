package com.zhao.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo2_Server {
	/**
	 * 2.�����
	 * ����ServerSocket(��Ҫָ���˿ں�)
	 * ����ServerSocket��accept()��������һ���ͻ������󣬵õ�һ��SOcket
	 * ����socketgetInputStream()��getoutputStream()������ȡ�ͻ������ӵ�IO��
	 * ���������Զ�ȡ�ͻ��������д��������
	 * ���������д������д���ͻ��˵�������
	 */
	public Demo2_Server() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public static void main(String[] args) throws IOException {
		//demo(); 
		
		ServerSocket server=new ServerSocket(9876);
		while(true){
			final Socket socket=server.accept(); //���ܿͻ��˵�����
			//ÿ����һ���ͻ��˾ʹ���һ���߳�
			new Thread(){
				public void run() {
				
					try {
						BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //���ֽ���ת��Ϊ�ַ���
						PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream ������д�����еķ���
						ps.println("��ӭ���ʷ�������");
						 System.out.println( br.readLine());
						 ps.println("ok");
						socket.close();
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					
				};
			}.start();
		
		}
	}

	
	
	private static void demo() throws IOException {
		ServerSocket server=new ServerSocket(9876);
		Socket socket=server.accept(); //���ܿͻ��˵�����
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //���ֽ���ת��Ϊ�ַ���
		PrintStream ps=new PrintStream(socket.getOutputStream()); //PrintStream ������д�����еķ���
		ps.println("��ӭ���ʷ�������");
		 System.out.println( br.readLine());
		 ps.println("ok");
		socket.close();
	}

}
