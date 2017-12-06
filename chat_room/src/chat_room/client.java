package chat_room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class client {

	
	private static final long serialVersionUID = 1L;
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter pw = null;
	
	JFrame jf;
	JPanel jp,jp2;
	JTextArea jta,jta2;
	JScrollPane jsp;
	JButton button;
	String contents;
	
	public client() throws UnknownHostException, IOException {

		jf=new JFrame("Chat_room-1.0");
		jf.setResizable(false);
		jf.setAlwaysOnTop(true);
		jf.setLocation(500, 300);
		jf.setSize(500, 500);
		jp=new JPanel();
		
		jp2=new JPanel();
		//jp2.setBackground(Color.LIGHT_GRAY);
		jf.add(jp2);
		jf.setLocationRelativeTo(jp2);
		
		jp.setLayout(new BorderLayout()); 
		jf.setLocationRelativeTo(jp);
		jf.add(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jta =new JTextArea(15, 60);
		jsp=new JScrollPane(jta);
		jsp.setViewportView(jta);
		
		jsp.setHorizontalScrollBarPolicy(  
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
		jsp.setVerticalScrollBarPolicy(  
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		jp.add(jsp);
		jta.setText("这是聊天框");
		jta.setLineWrap(true);
		jta.setBackground(Color.WHITE);
		jta.setBorder(new LineBorder(Color.BLACK));
		jta.setEditable(false);
		jta2 =new JTextArea(10, 60);
		jta2.setBackground(Color.WHITE);
		jta2.setBorder(new LineBorder(Color.BLACK));
		jta2.setText("从这里输入");
		jp.add(jta2,BorderLayout.SOUTH);
		//jp.setVisible(false);
		jta2.setLineWrap(true);
		//jf.pack();
		
		
		
		
		
		System.out.println("正在链接");
		socket = new Socket("localhost", 8088);
		System.out.println("已连接");
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 形成流
		pw = new PrintWriter(socket.getOutputStream());// 同上
		new Thread(new read(br,jta)).start();
	}

	private void start() throws IOException {

		
		jta2.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER ) {
               contents= jta2.getText();
               System.out.println(contents);
               	jta2.setText("");
               	pw.println(contents);
               	pw.flush();
					
			}
				}

			@Override
			public void keyReleased(KeyEvent e) {
			
			}
			
			
		});

	}


	public static void main(String[] args) throws UnknownHostException, IOException {
		
		new client().start();
		
	}
}
class read implements Runnable {// 接受socket里面的内容 然后打印
	private BufferedReader br ;
	private JTextArea jta;
	public read(BufferedReader br ,JTextArea jta) {
		this.br = br;
		this.jta=jta;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String s = br.readLine();
				if(s!=null) {
					System.out.println(s);
					jta.append("\r\n");
					jta.append(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

