package com.zhao.hehe;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class demo4_GUIChat extends Frame{

	private TextField tf;
	private Button send;
	private Button log;
	private Button clear;
	private Button shake;
	private TextArea viewText;
	private TextArea sedTexe;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private BufferedWriter bw;

	public demo4_GUIChat() {
		init();
		southPanel();
		CenterPanel();
		event();
	}

	public void event() {
		//����Ĺرհ�ť
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				socket.close();  //�˳������֮ǰ�ر���
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				System.exit(0); 
			}
		});
		//���Ͱ�ť�����¼�
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send();
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logFile();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}

		
		});
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewText.setText(""); //����
			}
		});
		
		shake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	shake();
				try {
					send(new byte[] {-1},tf.getText());
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}

			
		});
		//��������ļ��̼����¼�
		sedTexe.addKeyListener(new  KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){ //������µ���enter���ͷ�����Ϣ
					try {
						send();
					} catch (Exception e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	//�𶯹���
	private void shake() {
		int x=this.location().x;//��ȡ������λ��
		int y=this.location().y;//��ȡ������λ��
		this.setLocation(x+20, y+20);
		try {
			Thread.sleep(20);
			this.setLocation(x+20, y-20);
			Thread.sleep(20);
			this.setLocation(x-20, y+20);
			Thread.sleep(20);
			this.setLocation(x-20, y-20);
			Thread.sleep(20);
			this.setLocation(x, y);  //���������û�ԭλ
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	//��¼����
	private void logFile() throws IOException {
		bw.flush(); //ˢ�»�����
		FileInputStream fis=new FileInputStream("Config.txt");
		ByteArrayOutputStream baos=new ByteArrayOutputStream(); //���ڴ��д���������
		int len=0;
		byte [] arr=new byte[8192];
		//��ȡ
		while ((len=fis.read(arr))!=-1) {
			baos.write(arr,0,len);  
			
		}
		String str=baos.toString();//���ڴ��е�����ת��Ϊ�ַ���
		viewText.setText(str);
	}
	private void send(byte[] arr,String ip) throws Exception{
		
		packet = new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9000);
		socket.send(packet); //������Ϣ
	}
	private void send() throws Exception {
		String message=sedTexe.getText();//��ȡ�������������
		 String ip = tf.getText();
		 ip=ip.trim().length()==0 ? "255.255.255":ip ;//���ip���ڿվ�Ĭ��Ϊ�������˷���
		 send(message.getBytes(),ip);
//		socket = new DatagramSocket();
//		packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(ip), 9000);
//		socket.send(packet); //������Ϣ
//		viewText.setText(message);
		sedTexe.setText("");  //���Ϳ����
		String time=getCurrentTime();
		String strmsg = time+"�Ҷ�"+(ip.equals("255.255.255")?": ������":ip)+"˵\r\n"+message+"\r\n"; //��ȡ�ֲ����� alt+shift+l��
		viewText.append(strmsg); //����Ϣ׷��
		bw.write(strmsg); //����Ϣд���ļ���
//		socket.close();
	}
	//��ȡ��ǰʱ��
	private String getCurrentTime() {
		Date d=new Date(); //����ʱ�����
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		
		return sdf.format(d) ; //��ʱ���ʽ��
	}

	public void CenterPanel() {
		Panel center=new Panel();
		
		viewText = new TextArea();
		sedTexe = new TextArea(5,10);
		center.setLayout(new BorderLayout()); //Ĭ�ϵ�����ʽ���֣������Ĳ�������Ϊ�߽粼��
		center.add(sedTexe, BorderLayout.SOUTH);  //���͵��ı����������ϱ�
		center.add(viewText, BorderLayout.CENTER);  //��ʾ��¼���ı����������м�
		viewText.setEditable(false); //�����������Ա༭�������������д��������
		viewText.setBackground(Color.WHITE);  //�����ı���ı�����ɫ
		sedTexe.setFont(new Font("����", Font.PLAIN, 16)); //���÷��͵��������֣���ʽ����С
		viewText.setFont(new Font("��Բ", Font.PLAIN, 14)); //���÷��͵��������֣���ʽ����С
		this.add(center,BorderLayout.CENTER);  
	}

	public void southPanel() {
		Panel south=new Panel();  //�����ϱߵ�panelҲ��������������panel
		tf = new TextField(20);
		tf.setText("127.0.0.1");
		send = new Button("�� ��");
		log = new Button("�� ¼"); 
		clear = new Button("�� ��"); 
		shake = new Button("�� ��"); 
		south.add(tf);  //��tf��ӵ�panel��
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
		south.setBackground(Color.red);
		
		this.add(south,BorderLayout.SOUTH);//��panel��ӵ�fream���ϱ�
	}

	private void init() {
		this.setLocation(500,50);
		this.setSize(400, 560);
		new Receive().start();  //���������߳�
		try {
			socket = new DatagramSocket();
			bw=new BufferedWriter(new FileWriter("Config.txt",true)); //�������ڼ�¼���ļ�,trueҲ����˵֮ǰ�����ݱ���
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		this.setVisible(true);
	}
	private class Receive extends Thread{
		public void run(){
			
			try {
				DatagramSocket socket=new DatagramSocket(9000); //����socket�������ý��ܶ˿�Ϊ9000
				DatagramPacket packet=new DatagramPacket(new byte[8190], 8190);
				//��Ϊһֱ���ܾͲ�дsocket.close(); ��
				while(true){
				socket.receive(packet); //��������
				byte[] arr=packet.getData(); //��ȡȫ������
				int len=packet.getLength();  //��ȡ��Ч�ֽڳ���
				if(arr[0]==-1&&len==1){ //����������������һ���洢��ֵ��-1���������鳤����1���͵����𶯵ķ���
					shake();
					continue ; //��������ѭ���������´�ѭ��
				}
				String msg=new String(arr, 0, len);
				String time=getCurrentTime();
				String ip =packet.getAddress().getHostAddress();
				String string = time+" "+ip+"����˵\r\n"+msg+"\r\n";
				viewText.append(string); //����Ϣ׷��
//			    viewText.append(msg);
				bw.write(string);
				}
				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
			new demo4_GUIChat();  //����һ������ 
	}

}
