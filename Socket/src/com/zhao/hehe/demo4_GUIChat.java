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
		//窗体的关闭按钮
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				socket.close();  //退出虚拟机之前关闭它
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				System.exit(0); 
			}
		});
		//发送按钮监听事件
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
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
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}

		
		});
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewText.setText(""); //清屏
			}
		});
		
		shake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	shake();
				try {
					send(new byte[] {-1},tf.getText());
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}

			
		});
		//发送区域的键盘监听事件
		sedTexe.addKeyListener(new  KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){ //如果按下的是enter键就发送消息
					try {
						send();
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	//震动功能
	private void shake() {
		int x=this.location().x;//获取横坐标位置
		int y=this.location().y;//获取纵坐标位置
		this.setLocation(x+20, y+20);
		try {
			Thread.sleep(20);
			this.setLocation(x+20, y-20);
			Thread.sleep(20);
			this.setLocation(x-20, y+20);
			Thread.sleep(20);
			this.setLocation(x-20, y-20);
			Thread.sleep(20);
			this.setLocation(x, y);  //将窗体设置回原位
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	//记录函数
	private void logFile() throws IOException {
		bw.flush(); //刷新缓冲区
		FileInputStream fis=new FileInputStream("Config.txt");
		ByteArrayOutputStream baos=new ByteArrayOutputStream(); //在内存中创建缓冲区
		int len=0;
		byte [] arr=new byte[8192];
		//读取
		while ((len=fis.read(arr))!=-1) {
			baos.write(arr,0,len);  
			
		}
		String str=baos.toString();//将内存中的内容转换为字符串
		viewText.setText(str);
	}
	private void send(byte[] arr,String ip) throws Exception{
		
		packet = new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9000);
		socket.send(packet); //发送消息
	}
	private void send() throws Exception {
		String message=sedTexe.getText();//获取发送区域的内容
		 String ip = tf.getText();
		 ip=ip.trim().length()==0 ? "255.255.255":ip ;//如果ip等于空就默认为都所有人发送
		 send(message.getBytes(),ip);
//		socket = new DatagramSocket();
//		packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(ip), 9000);
//		socket.send(packet); //发送消息
//		viewText.setText(message);
		sedTexe.setText("");  //发送框清空
		String time=getCurrentTime();
		String strmsg = time+"我对"+(ip.equals("255.255.255")?": 所有人":ip)+"说\r\n"+message+"\r\n"; //抽取局部变量 alt+shift+l；
		viewText.append(strmsg); //将信息追加
		bw.write(strmsg); //讲消息写入文件中
//		socket.close();
	}
	//获取当前时间
	private String getCurrentTime() {
		Date d=new Date(); //创建时间对象
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		
		return sdf.format(d) ; //将时间格式化
	}

	public void CenterPanel() {
		Panel center=new Panel();
		
		viewText = new TextArea();
		sedTexe = new TextArea(5,10);
		center.setLayout(new BorderLayout()); //默认的是流式布局，将他的布局设置为边界布局
		center.add(sedTexe, BorderLayout.SOUTH);  //发送的文本框设置在南边
		center.add(viewText, BorderLayout.CENTER);  //显示记录的文本框设置在中间
		viewText.setEditable(false); //设置他不可以编辑，所以他上面就写不了字了
		viewText.setBackground(Color.WHITE);  //设置文本域的背景颜色
		sedTexe.setFont(new Font("楷体", Font.PLAIN, 16)); //设置发送的字体名字，样式，大小
		viewText.setFont(new Font("幼圆", Font.PLAIN, 14)); //设置发送的字体名字，样式，大小
		this.add(center,BorderLayout.CENTER);  
	}

	public void southPanel() {
		Panel south=new Panel();  //创建南边的panel也就是聊天框下面的panel
		tf = new TextField(20);
		tf.setText("127.0.0.1");
		send = new Button("发 送");
		log = new Button("记 录"); 
		clear = new Button("清 屏"); 
		shake = new Button("震 动"); 
		south.add(tf);  //将tf添加到panel中
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
		south.setBackground(Color.red);
		
		this.add(south,BorderLayout.SOUTH);//将panel添加到fream的南边
	}

	private void init() {
		this.setLocation(500,50);
		this.setSize(400, 560);
		new Receive().start();  //启动接收线程
		try {
			socket = new DatagramSocket();
			bw=new BufferedWriter(new FileWriter("Config.txt",true)); //创建用于记录的文件,true也就是说之前的内容保留
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.setVisible(true);
	}
	private class Receive extends Thread{
		public void run(){
			
			try {
				DatagramSocket socket=new DatagramSocket(9000); //创建socket对象并设置接受端口为9000
				DatagramPacket packet=new DatagramPacket(new byte[8190], 8190);
				//因为一直接受就不写socket.close(); 了
				while(true){
				socket.receive(packet); //接受数据
				byte[] arr=packet.getData(); //获取全部数据
				int len=packet.getLength();  //获取有效字节长度
				if(arr[0]==-1&&len==1){ //如果发过来的数组第一个存储的值是-1，并且数组长度是1，就调用震动的方法
					shake();
					continue ; //结束本次循环，继续下次循环
				}
				String msg=new String(arr, 0, len);
				String time=getCurrentTime();
				String ip =packet.getAddress().getHostAddress();
				String string = time+" "+ip+"对我说\r\n"+msg+"\r\n";
				viewText.append(string); //将信息追加
//			    viewText.append(msg);
				bw.write(string);
				}
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
			new demo4_GUIChat();  //创建一个窗体 
	}

}
