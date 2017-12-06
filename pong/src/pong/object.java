package pong;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class object {
	int x;
	int y;
	int width;
	int height;
	


	static BufferedImage image;

	void step() {

	}

	

	public static BufferedImage loadImage(String FileName) {
		try {
			image = ImageIO.read(object.class.getResource(FileName));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
