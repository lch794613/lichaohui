package pong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class block extends object {
	block() {
		x = 0;
		y = 0;
		width = 80;
		height = 25;
     
	}

	int i = (int) (Math.random() * 10);
	static BufferedImage black;
	static BufferedImage bule;
	static BufferedImage danqing;
	static BufferedImage green;
	static BufferedImage qingse;
	static BufferedImage red;
	static BufferedImage shit;
	static BufferedImage white;
	static BufferedImage yellow;
	static BufferedImage zise;

	static {
		black = loadImage("black.jpg");
		bule = loadImage("black.jpg");
		danqing = loadImage("danqing.jpg");
		green = loadImage("green.jpg");
		qingse = loadImage("qingse.jpg");
		red = loadImage("red.jpg");
		shit = loadImage("shit.jpg");
		white = loadImage("white.jpg");
		yellow = loadImage("yellow.jpg");
		zise = loadImage("zise.jpg");

	}

	BufferedImage getImage() {
		BufferedImage[] bi = { black, bule, danqing, green, qingse, red, shit, white, yellow, zise };

		return bi[i];
	}

	void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, this.width, this.height, null);
	}

}
