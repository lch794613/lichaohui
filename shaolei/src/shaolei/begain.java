package shaolei;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class begain extends JPanel {

	static peizhiqi pzq = new peizhiqi();
	cell c = new cell();

	static String str = pzq.getPicture() + ".jpg";
	static int WIDTH = pzq.getWidth() * 50 ;
	static int HEIGHT = pzq.getHeight() * 50 + 36;
	static int sum = pzq.getSum();
	static int x_mouse;
	static int y_mouse;

	static final int start = 0;
	static final int running = 1;
	static final int gameOver = 2;
	static final int show = 3;
	private static int state = start;
	
	static BufferedImage imge;
	static BufferedImage imge2;
	static BufferedImage gameover;

	char[][] arr = c.creatArry(this.sum);

	public static BufferedImage loadImage(String fileName) throws Exception {

		BufferedImage img = ImageIO.read(begain.class.getResource(fileName));
		return img;
	}

	static {
		try {
			imge = begain.loadImage(str);
			imge2 = begain.loadImage("2.jpg");
			gameover = begain.loadImage("gameover.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void SLaction() {

		int sum = this.sum;
		MouseAdapter ma = new MouseAdapter() {

			int sum2 = sum;

			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) {
					if (state == running) {
						x_mouse = e.getX();
						y_mouse = e.getY();
						c.check(x_mouse, y_mouse);
					}
					 System.out.println("右击");
				}
				if (e.getButton() == MouseEvent.BUTTON1) {
					switch (state) {
					case start:
						c = new cell();
						arr = c.creatArry(this.sum2);
						
						state = running;
						break;
					case running:
					   x_mouse = e.getX();
						y_mouse = e.getY();
						c.hit(x_mouse, y_mouse);// 获得x y值 然后hit掉点击的位置
						if (over(x_mouse, y_mouse) == true) {//如果点击到雷就结束游戏
							 state=gameOver;
						};
							 System.out.println("左击");
					 break;
					case gameOver:
						state = show;
						break;
					case show:
						state = start;
						break;
					}
				}
			}
		};

		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);

		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {

				repaint();
			}
		};
		int timestop = 10;
		timer.schedule(tt, timestop, timestop);
	}

	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.BLACK);

		switch (state) {
		case gameOver:
			g.drawImage(gameover, 0, 0, null);
			break;
		case running:
			c.Paint(g);// 画格子
			for (int a = 0; a < c.x_width; a++) {
				for (int b = 0; b < c.y_weight; b++) {
					char ca = arr[a][b];
					String s = String.valueOf(ca);
					g.setFont(new Font("Tahoma", Font.BOLD, 25));
					g.drawString(s, a * c.width + c.width / 2 - 8, b * c.weight + c.weight / 2 + 10);// 画内容

					if (c.boo[a][b] != true) {
						g.drawImage(imge, a * c.width, b * c.weight, null);// 画覆盖的格子

					}
					if (c.boo2[a][b] == true) {
						g.drawImage(imge2, a * c.width, b * c.weight, null);// 右擊畫格子
					}
				}
			}
			break;
		case show:
			c.Paint(g);
			for (int a = 0; a < c.x_width; a++) {
				for (int b = 0; b < c.y_weight; b++) {
					char ca = arr[a][b];
					String s = String.valueOf(ca);
					g.setFont(new Font("Tahoma", Font.BOLD, 25));
					g.drawString(s, a * c.width + c.width / 2 - 8, b * c.weight + c.weight / 2 + 10);
				}
			}

			break;
		case start:
			g.setFont(new Font("Tahoma", Font.BOLD, 25));
			g.drawString("点击鼠标请开始游戏", WIDTH / 2 - 110, HEIGHT / 2);

		}
	}

	public boolean over(int x, int y) {
		for (int a = 0; a < c.x_width; a++) {
			for (int b = 0; b < c.y_weight; b++) {
				if (x > a * c.width && x < (a + 1) * c.width && y > b * c.weight && y < (b + 1) * c.weight) {
					if (arr[a][b] == '*') {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		begain be = new begain();
		frame.add(be);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		be.SLaction();

	}
}
