package bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class block extends object {

	int width = 100;
	int height = 433;
	int speed = configuration.getSpeed();
	int blanket = configuration.getBlanket();
	
	int y = (-(int) (Math.random() * 250)) - 33;
	int x = start.width;
	int x1 = x;
	int y1 = y + this.height;

	int y2 = y + this.height + this.blanket;
	int x2 = x+this.width;
	int y2_2 = y2 +this.height;
	int x2_2 = x2;

	static BufferedImage image2;
	static BufferedImage image;

	static {
		image = loadimage("shuiguan.jpeg");
		image2 = loadimage("shuiguan2.jpeg");
	}

	@Override
	public void step() {
		this.x = this.x - speed;
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void Paint(Graphics g) {
		g.drawImage(image2, x, y, null);
		g.drawImage(image2, x, y2, null);
	}

}
