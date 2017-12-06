package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
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

public class world extends JPanel {

	private final static int width = configuration.getWidth() * 30 + 5;
	private final static int height = configuration.getHeight() * 30 + 40;
	private final static int x = 0;
	private final static int y = 0;
	private final static int time = configuration.getTime();

	private final static int start = 0;
	private final static int run = 1;
	private final static int pause = 2;
	private final static int gameover = 3;
	static int state = start;

	private final static int up = 4;
	private final static int down = 5;
	private final static int left = 6;
	private final static int right = 7;
	static int moveState = right;

	int aa;// 用于储存移动的时候的值
	int ab;// 同上
	int[] s2;// 存x
	int[] s3;// 存y

	int sore = 0;

	private apple a = new apple();
	private snake[] s = {};
	private bg bg=new bg();

	static BufferedImage background;
	static BufferedImage image;
	static BufferedImage image2;
	static BufferedImage image3;
	static {
		image = object.loadImage("start.jpg");
		image2 = object.loadImage("pause2.png");
		image3 = object.loadImage("gameover.png");
		background=object.loadImage("99.jpg");

	}

	private void snakeInit() {// 初始化
		for (int i = 0; i < 5; i++) {
			s = Arrays.copyOf(s, 5);
			s[i] = new snake();
		}
		s[1].x = s[0].x - 30;
		s[2].x = s[1].x - 30;
		s[1].y = s[0].y;
		s[2].y = s[1].y;
		s[3].x = s[2].x - 30;
		s[4].x = s[3].x - 30;
		s[3].y = s[2].y;
		s[4].y = s[3].y;

	}

	private void coypArry() {

		s2 = new int[s.length];
		s3 = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			s2[i] = s[i].x;
			s3[i] = s[i].y;
		}

	}

	private void snakeFollow() {
		for (int i = 1; i < s.length; i++) {
			s[i].x = s2[i - 1];
			s[i].y = s3[i - 1];
		}

	}

	private void snakeMove() {
		switch (moveState) {
		case up:
			s[0].y = s[0].y - s[0].speed;
			break;
		case down:
			s[0].y = s[0].y + s[0].speed;
			break;
		case left:
			s[0].x = s[0].x - s[0].speed;
			break;
		case right:
			s[0].x = s[0].x + s[0].speed;
			break;
		}

	}

	private void isHit() {
		if (a.x == s[0].x && a.y == s[0].y) {
			sore += 100;
			s = Arrays.copyOf(s, s.length + 1);
			s[s.length - 1] = new snake();

			boolean b = true;
			while (b) {
				aa = ((int) (Math.random() * configuration.getHeight() + 10) - 10) * 30;
				ab = ((int) (Math.random() * configuration.getHeight() + 10) - 10) * 30;
				for (int i = 0; i < s.length; i++) {
					if (s[i].x != aa && s[i].y != ab) {
						a.x = aa;
						a.y = ab;
						b = false;
					}
					;

				}
			}
		}
	}

	private void out() {
		if (s[0].x > width || s[0].x < 0 || s[0].y > height || s[0].y < 0) {
			state = gameover;
		}

	}

	private void suicide() {
		for (int i = 1; i < s.length; i++) {
			if (s[i].x == s[0].x && s[i].y == s[0].y) {
				state = gameover;
			}

		}

	}
	private void hard() {
		if(sore==1000) {
			configuration.setTime(50);
		}if(sore==2000) {
			configuration.setTime(30);
		}if(sore==3000) {
			configuration.setTime(10);
		}if(sore==4000) {
			configuration.setTime(5);
		}

	}

	private void Action() {

		KeyAdapter ka = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_UP:
					if(moveState!=down) {
					moveState = up;
					break;}
					
					break;
				case KeyEvent.VK_DOWN:
					if(moveState!=up) {
					moveState = down;break;}
					
					break;
				case KeyEvent.VK_LEFT:
					if(moveState!=right) {
					moveState = left;break;}
					break;
				case KeyEvent.VK_RIGHT:
					if(moveState!=left) {
					moveState = right;break;}
					break;

				}
			}

		};
		MouseAdapter ma = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case start:
					snakeInit();
					state = run;
					break;
				case run:
					state = pause;
					break;
				case pause:
					state = run;
					break;
				case gameover:
					
					state = start;
					moveState=right;
					sore=0;
               break;

				}

			}

		};
		this.addMouseListener(ma);
		this.addKeyListener(ka);
		this.setFocusable(true);
		this.requestFocus();

		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				if (state == run) {

					coypArry();
					snakeMove();// 头部移动
					snakeFollow();// 身体跟随
					isHit();// 碰撞创建新苹果
					out();
					suicide();
					hard();
				}
				repaint();
			}

			

		};

		t.schedule(tt, time, time);
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		bg.Paint(g);
		
		//g.fillRect(0, 0, width, height);

		switch (state) {
		case start:
			g.drawImage(image, 0, 0, width + 100, height + 100, null);
			break;
		case run:
			g.drawImage(background, 0, 0, width, height, null);
			bg.Paint(g);
			g.setColor(Color.BLACK);
			a.Paint(g);
			g.drawString(""+sore, 5, 20);
          g.drawRect(0, 0, width, height);
			for (int i = 0; i < s.length; i++) {
				s[i].Paint(g);
			}
			break;
		case pause:
			g.drawImage(image2, 0, 0, width, height, null);
			break;
		case gameover:
			g.drawImage(image3, 0, 0, width, height, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 30));
			g.drawString("yourSore=" + sore, width/2-80, height / 2+140);
			g.setColor(Color.WHITE);
			break;

		}
	}

	public static void main(String[] args) {

		JFrame j = new JFrame();
		world w = new world();
		j.add(w);

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(width, height);
		j.setLocationRelativeTo(null);
		j.setVisible(true);

		

		w.Action();
		
	}
}
