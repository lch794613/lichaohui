package bird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class start extends JPanel {

	static final int width = 1000;
	static final int height = 600;
	static  int createspeed=configuration.getCreatespeed();

	private static final int begain = 0;
	private static final int run = 1;
	private static final int pasue = 2;
	private static final int gameover = 3;
	private static int state = begain;

	int sore = 0;

	private block[] b = {};
	private bird bird = new bird();
	private background bg = new background();

	static BufferedImage image;
	static BufferedImage startPicture;
	static BufferedImage pausePicture;

	static {
		image = object.loadimage("gameover1.png.png");
		startPicture = object.loadimage("start.jpg");
		pausePicture = object.loadimage("pause2.png");

	}

	private void step() {
		bg.step();
		bird.step();
		for (int i = 0; i < b.length; i++) {
			b[i].step();
		}

	}
	int blockIndex = 0;
	private void create() {//每100帧生成1组水管
		blockIndex++;
		if (blockIndex % createspeed == 0) {
			b = Arrays.copyOf(b, b.length + 1);
			b[b.length - 1] = new block();
		}
	}

	private void out() {
		if(bird.y>height) {
			state=gameover;
		}

	}
	
	

      
	
	private void birdAction() {
		KeyAdapter k = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch(key) {
				case KeyEvent.VK_UP:
					bird.keystep();
					break;
				}
				
			}

		};
		this.addKeyListener(k);
		this.setFocusable(true);
		this.requestFocus();
		
		
		MouseAdapter m = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case begain:
					sore=0;
					state = run;
					break;
				case run:
					state = pasue;
					break;
				case pasue:
					state = run;
					break;
				case gameover:
					 bird = new bird();
					  b = new block[0];
					state = begain;
				}
			}
		};
		addMouseListener(m);


		Timer timer = new Timer();

		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				if (state == run) {
					create();
					step();
					out();
					judg();
					sore=sore+1;
				}
				repaint();
			}
		};
		int timestop = 70;
		timer.schedule(tt, timestop, timestop);
	}

	@Override
	public void paint(Graphics g) {

		
		g.setFont(new Font("Tahoma", Font.BOLD, 30));
		bg.Paint(g);

		switch (state) {
		case begain:
			g.drawImage(startPicture, 0, -200, null);
			g.drawString("点击鼠标开始游戏 方向键*上*控制移动 再次点击暂停", 180, 460);
			break;
		case run:
			g.drawString("得分" + sore, 10, 50);
			bird.Paint(g);
			for (int i = 0; i < b.length; i++) {
				b[i].Paint(g);
			}
			break;
		case pasue:
			g.drawImage(pausePicture, 0, 0, null);
			g.drawString("得分" + sore, 10, 50);
			break;
		case gameover:
			g.drawImage(image, 0, 0, null);
			g.drawString("你的得分是" + sore, 380, 460);
			break;

		}
	}
	
	private void judg() {//撞击死亡
		for (int i = 0; i < b.length; i++) {
			if (b[i].x1 < bird.x2 && b[i].x2 > bird.x) {
				//if (b[i].y < bird.y && b[i].y1 > bird.y2 && b[i].y2 < bird.y && b[i].y2_2 > bird.y2) {
					state = gameover;}//}
			{	
			}
			}
	}

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		start s = new start();
		jf.add(s);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(width, height);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		s.birdAction();
	}
}
