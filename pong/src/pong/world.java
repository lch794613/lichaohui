package pong;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class world extends JPanel {
	public static final int width = configuration.getWidth()+5;
	public static final int height = configuration.getHeigth()+30;
	public final static int start = 0;
	final static int run = 1;
	final static int pause = 2;
	final static int gameover = 3;
	static int state = start;
	int sore;

	
	block b[][] = new block[10][8];
	bg bg = new bg();
	ball ball2 = new ball();
	panel p = new panel();

	static BufferedImage startPicture;
	static BufferedImage pausePicature;
	static BufferedImage gameOverPicture;

	static {
		startPicture = object.loadImage("bg.jpg");
		pausePicature = object.loadImage("pause2.png");
		gameOverPicture = object.loadImage("gameover.png");
	}

	void createWorld() {
		for(int i=0;i<10;i++) {
			for(int i2=0;i2<8;i2++) {
				b[i][i2]=new block();
				b[i][i2].x=80*i;
				b[i][i2].y=25*i2;
				
			}
		}
		

	}

	void ballMove() {
		if (ball2.x < 0 || ball2.x > width-30) {
			ball2.Xspeed=-ball2.Xspeed;
			ball2.x=ball2.x+ball2.Xspeed;
			
			
		}
		if (ball2.y < 0) {
			ball2.Yspeed=-ball2.Yspeed;
			ball2.y=ball2.y+ball2.Yspeed;
		}
		if (ball2.y > height) {
			state = gameover;
		}
		if((ball2.x+12)>p.x&&ball2.x+12<(p.x+130)&&ball2.y>p.y) {
			ball2.Yspeed=-ball2.Yspeed;
			ball2.y=ball2.y+ball2.Yspeed;
		}

	}
	
	
	
	void is_hit() {
		for (int i = 0; i < 10; i++) {
			for (int i2 = 0; i2 < 8; i2++) {
				block z = b[i][i2];
//				if (z.x < ball2.x1 && ball2.Xspeed > 0 && z.y < ball2.y1 && (z.y+25) > ball2.y1) {// 向右撞
//					z.x = -100;
//					z.y = -100;
//					ball2.Xspeed = -ball2.Xspeed;
//					ball2.x = ball2.x + ball2.Xspeed;
//				}
//				if ((z.x+80) > ball2.x && ball2.Xspeed < 0 && z.y < ball2.y1 && (z.y+25) > ball2.y1) {// 向左撞
//					z.x = -100;
//					z.y = -100;
//					ball2.Xspeed = -ball2.Xspeed;
//					ball2.x = ball2.x + ball2.Xspeed;
//				}
//				if (z.y > ball2.y1 && ball2.Yspeed > 0 && z.x < ball2.x1 && (z.x+80) > ball2.y1) {// 向下撞
//					z.x = -100;
//					z.y = -100;
//					ball2.Yspeed = -ball2.Yspeed;
//					ball2.y = ball2.y + ball2.Yspeed;
//				}
//				if ((z.y+25) < ball2.y && ball2.Yspeed < 0&& z.x < ball2.x1 && (z.x+80) > ball2.x1) {// 向上撞
//					z.x = -100;
//					z.y = -100;
//					ball2.Yspeed = -ball2.Yspeed;
//					ball2.y = ball2.y + ball2.Yspeed;
				}

			}
		}
	}

	private void action() {
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==run) {
				int x = e.getX() ;
			     p.move(x);
			}
				}

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case start:
					createWorld();
					p=new panel();
					state = run;
					break;
				case run:
					System.out.println("pause");
					state = pause;
					break;

				case pause:
					state = run;
					System.out.println("run!");
					break;
				case gameover:
					ball2=new ball();
					state = start;
					break;

				}
			}
		};
		addMouseListener(ma);
		addMouseMotionListener(ma);

		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				if (state == run) {
					ball2.step();
					is_hit();
					ballMove();
					
					System.out.println("ball.y"+ball2.y);
					System.out.println("ball.x"+(ball2.x+12));
					System.out.println("PX"+p.x);
					System.out.println("PY"+p.y);
					System.out.println("ballx2"+ball2.x1);
					System.out.println("bally2"+ball2.y1);

					
				}
				repaint();
			}
		};
		t.schedule(tt, 5, configuration.getSpeed());
		
	}

	@Override
	public void paint(Graphics g) {
		
		
		switch (state) {
		case start:
			g.drawImage(startPicture, 0, 0, null);
			break;
		case run:
			g.drawImage(startPicture, 0, 0, null);
			
			ball2.Paint(g);
			p.Paint(g);
			for (int i = 0; i < 10; i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					b[i][i2].Paint(g);
				}
			}
			break;
		case pause:
			g.drawImage(pausePicature, 0, 0, width, height, null);
			break;
		case gameover:
			g.drawImage(gameOverPicture, 0, 0, width, height, null);
			break;
		}
	}
	

	
	
	public static void main(String[] args) {

		JFrame j = new JFrame();
		world w = new world();
		j.add(w);
		j.setVisible(true);
		j.setSize(width, height);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
		
		
		
		
		
		w.action();

	}
}
