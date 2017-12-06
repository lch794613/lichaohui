package snake;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class bg extends object{

	
	bg(){
		x=0;
		y=0;
		width=world.WIDTH;
		height=world.HEIGHT;
	}
	
	static BufferedImage bg;
	static {
		bg=loadImage("99.jpg");
	}
	
	
	@Override
	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, width, height, null);
	}
	@Override
	public BufferedImage getImage() {
		
		return bg;
	}

}
