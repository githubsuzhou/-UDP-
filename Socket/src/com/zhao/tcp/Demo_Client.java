package com.zhao.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo_Client {
	/***
	 * 1.�ͻ���
	 * ����Socket���ӷ�������ָ��ip��ַ���˿ںţ�ͨ��ip��ַ�Ҷ�Ӧ�ķ�����
	 * ����Socket��getInputStream()��getoutputStream()������ȡ�������ӵ�IO��
	 * ���������Զ�ȡ�����������д��������
	 * ���������д������д����������������
	 */

	public Demo_Client() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public static void main(String[] args) throws Exception, IOException {
		Socket socket=new Socket("127.0.0.1", 9876);
		InputStream is= socket.getInputStream();  //��ȡ�ͻ���������
		OutputStream os=socket.getOutputStream(); //��ȡ�ͻ��������
		
		byte [] arr=new byte[1024];
		int len=is.read(arr);
		System.out.println(new String(arr,0,len)); //���ַ�ת��Ϊ�ַ�������ӡ 
		os.write("�õģ���ʼͨ�š���������".getBytes());
		socket.close();
		
	}

}
