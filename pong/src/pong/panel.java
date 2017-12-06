package pong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class panel extends object {
panel(){
	 x=(int)(world.width/2)-55;
	 y=world.height-48;
	 height=10;
	 width=130;
}
   

	static BufferedImage image;
	static {
		image = loadImage("panel.jpg");
	}
	
	 void move(int x) {
		this.x=x-this.width/2;

	}

	void step() {
		y = configuration.getHeigth();
	}

	void Paint(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}
}
