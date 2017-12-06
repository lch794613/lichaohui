package snake;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class apple extends object {
	apple() {
		width = 30;
		height = 30;
		x = ((int) (Math.random()*configuration.getHeight()/2)+10)*30 ;
		y = ((int) (Math.random()*configuration.getWidth()/2)+10)*30 ;
	}

	static BufferedImage image;
	static {

		image = loadImage("O.png");

	}

	@Override
	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	@Override
	public BufferedImage getImage() {

		return image;
	}

}
