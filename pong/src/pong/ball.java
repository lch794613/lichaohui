package pong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ball extends object {
	
		int x = configuration.getWidth() / 2;
		int y = configuration.getHeigth() /2+207;
		int width = 25;
		int height = 25;
		
	  int	x1;
	  int	y1;
		 
	
	
	 
	
	int Xspeed=(int) (Math.random()*10)-5;
	int Yspeed=-((int) (Math.random()*3)+7);
	
	
	
	

	static BufferedImage image;
	static {
		image = loadImage("ball.png");
	}

	@Override
	void step() {
		x+=Xspeed;
		y+=Yspeed;
		x1=x+25;
		y1=y+25;
		
	}
	
		
	 

	void Paint(Graphics g) {
		g.drawImage(image, this.x, this.y, 25, 25, null);
	}
}
