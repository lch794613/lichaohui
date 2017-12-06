package bird;

import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public abstract class object {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int speed;
	protected int x2;
	protected int y2;

	public static BufferedImage loadimage(String flieName) {
		try {
			BufferedImage img = ImageIO.read(object.class.getResource(flieName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public abstract BufferedImage getImage();

	protected void step() {

	}

	boolean isdead() {

		return false;
	}

	public void Paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	public boolean hit(object o) {//判定鸟与水管的接触
		
			
		
			
		
		return false;
  
	}
}
