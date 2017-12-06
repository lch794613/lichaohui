package bird;

public class configuration {
	private static int speed = 12;//背景运动速度
	private static int blanket= 150;//空间
	private static int birdspeed=5;//鸟的下降速度
	private static int keyspeed=2;//键盘输入之后的上升速度
	private static int createspeed=40;//水管生成的速度

	public static int getCreatespeed() {
		return createspeed;
	}

	public static void setCreatespeed(int createspeed) {
		configuration.createspeed = createspeed;
	}

	public static int getKeyspeed() {
		return keyspeed;
	}

	public static void setKeyspeed(int keyspeed) {
		configuration.keyspeed = keyspeed;
	}

	public static int getBirdspeed() {
		return birdspeed;
	}

	public static void setBirdspeed(int birdspeed) {
		configuration.birdspeed = birdspeed;
	}

	public static int getBlanket() {
		return blanket;
	}

	public static void setBlanket(int blanket) {
		configuration.blanket = blanket;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		configuration.speed = speed;
	}
}
