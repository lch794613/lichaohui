package snake;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public abstract class object {

	public int x;
	public int y;
	public int height;
	public int width;

	public BufferedImage image;

	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage image = ImageIO.read(object.class.getResource(fileName));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	 public boolean hit(object o) {
		 if(o.x==this.x&&o.y==this.y) {
			 return true;
		 }
		 return false;
		
	}
	public abstract BufferedImage getImage();

	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);

	}
}
