package chat_room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class server {
	private List<clients> client = new ArrayList<>();// 集合来接受所有的连接对象
	private String contents = null;
	int sum_client;

	private void startup() throws IOException {
		System.out.println("开始");
		ServerSocket ss = new ServerSocket(8088);// 服务器端口 176.26.10.101
		System.out.println("创建端口");
		new Thread(new listen()).start();// 开启线程2
		new Thread(new offline()).start();//监控下线
		
		while (true) {
			Socket s = ss.accept();// 不断循环遍历接受socket
			System.out.println("接受到一位连接");
			Runnable t = new clients(s); // 将所有的连接对象放入集合中间
			new Thread(t).start();// 开启线程
			

		}

	}

	class clients implements Runnable {

		private Socket s = null;
		private BufferedReader br = null;
		private PrintWriter pw = null;
		private String name = null;

		public clients(Socket socket) throws IOException {// 构造器初始化socket以及io流 主要的作用就是读取输入的内容
			this.s = socket;
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(), true);
			name = "[" + s.getInetAddress().getHostAddress() + ":" + s.getPort() + "]"; // client的name
			client.add(this);// 将对象放入list
			for (clients c : client) {
				c.send("我上线了");
			}
			sum_client+=1;	
		}

		@Override
		public void run() { // 读取内容
			while (true) {
				try {
					 contents = br.readLine();
					 System.out.println(contents);
					if(contents==null) {
						client.remove(this);
						System.out.println("某人下线了");
						return;
					}
					
				} catch (IOException  e) {
					//System.out.println("br.read miss");
					client.remove(this);
				}
			}
		}
		

		private void send(String s) {
			try {
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String time = dateFormat.format(now);// 时间模块
				pw.println(name + ":" + time + ":" );
				pw.flush();
				pw.println(s);
				pw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	class listen implements Runnable {// 发送内容
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (contents != null) {
					for (clients c : client) {
						c.send(contents);// 增强for循环 将所有的内容发送给所有的连接者
					}
					contents = null;// 将内容消除
				}
			}

		}

	}
	class offline implements Runnable{//下线通知
		int sum=0;
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sum=client.size();
				if(sum<sum_client) {
					sum_client=sum;
					for (clients c : client) {
						c.send("我下线了");
						
					}
				}
			}
		}
		}
		
	

	public static void main(String[] args) throws IOException {
			new server().startup();
	}
}
