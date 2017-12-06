package pong;

import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;

public class bg extends object {
	
		int x=0;
		int y=0;
		int width=800;
		int heigth=500;
		
	
	static BufferedImage image;
	static {
		image=loadImage("bg.jpg");
	}
	
	void Paint(Graphics g){
		g.drawImage(image, x, y, this.width, this.height, null);
	}
}
