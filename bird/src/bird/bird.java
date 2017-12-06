package bird;

import java.awt.image.BufferedImage;

public class bird extends object {

	public bird() {
		x = 20;
		y = start.height / 2 - 35;
		speed = configuration.getBirdspeed();
		width =56;
		height=48;
		x2=x+width;
		y2=y+height;

	}
	

	int keyspeed = configuration.getKeyspeed();

	private static BufferedImage[] images;
	static {
		images = new BufferedImage[7];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadimage(i + ".png");
		}
	}

	protected void keystep() {// 键盘速度
		this.y -= 30;
	}

	@Override
	protected void step() {

		this.y = this.y + speed;// 下降速度

		if (this.x < start.width / 2 - 50) {
			this.x = this.x + configuration.getBirdspeed();// 平移速度
		} else
			this.x = start.width / 2 - 50;// 到达中点之后 X不动

		if (this.x < 0) {
			this.x = 0;
		}
		
	}

	int index = 0;

	public BufferedImage getImage() {
		return images[index++ % 7];

	}

}
