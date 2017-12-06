package snake;

public class configuration {
	private static int x;
	private static int y;
	private static int width=50;//格数x
	private static int height=30;//格数y
	private static int speed=1;
	private static int time=100;

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		configuration.time = time;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		configuration.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		configuration.y = y;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		configuration.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		configuration.height = height;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		configuration.speed = speed;
	}

}
