package snake;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class snake extends object {
	snake() {
		x = (int) ((Math.random()*configuration.getHeight()/2)+10)*30 ;
		y = (int) ((Math.random()*configuration.getWidth()/2)+10)*30 ;
		width = 30;
		height = 30;

	}
	
	int speed = 30 * configuration.getSpeed();

	static BufferedImage image;
	static {
		image = loadImage("I.png");
	}
	

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

}
