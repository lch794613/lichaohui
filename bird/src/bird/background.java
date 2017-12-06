package bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class background extends object {

	int x = 0;
	int y = 0;
	int x1 = start.width;
	int width = start.width;
	int weight = start.height;
	int speed = configuration.getSpeed();

	private static BufferedImage image;
	static {
		image = loadimage("bg.jpg");
	}

	@Override
	protected void step() {
		this.x = this.x - 5;
		if (x == -start.width) {
			x = start.width;
		}
		this.x1 = this.x1 - 5;
		if (x1 == -start.width) {
			x1 = start.width;
		}
	}

	@Override
	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);
		g.drawImage(getImage(), x1, y, null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
