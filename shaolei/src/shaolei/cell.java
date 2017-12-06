package shaolei;

import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class cell {
	
	
	static peizhiqi pzq = new peizhiqi();

	static int width = 50;
	static int weight = 50;
	static int x_width = pzq.getWidth();//
	static int y_weight = pzq.getHeight();//

	boolean[][] boo2 = new boolean[x_width][y_weight];
	boolean[][] boo = new boolean[x_width][y_weight];
	char arr[][] = new char[x_width][y_weight];

	void Paint(Graphics g) {
		for (int a = 0; a < x_width; a++) {
			for (int b = 0; b < y_weight; b++) {
				g.drawRect(a * width, b * weight, weight, width);// 画格子
			}
		}
	}

	char[][] creatArry(int sum) {

		for (int i = 0; i < sum; i++) {
			int x = (int) (Math.random() * x_width);
			int y = (int) (Math.random() * y_weight);
			arr[x][y] = '*';
		}
		for (int a = 0; a < x_width; a++) {
			for (int b = 0; b < y_weight; b++) {
				int sum_surround = 0;
				if (arr[a][b] != '*') {
					if (a - 1 > 0 && b - 1 > 0) {
						if (arr[a - 1][b - 1] == '*') {
							sum_surround += 1;
						}
					}
					if (b - 1 > 0) {
						if (arr[a][b - 1] == '*') {
							sum_surround += 1;
						}
					}
					if (a + 1 < x_width && b - 1 > 0) {
						if (arr[a + 1][b - 1] == '*') {
							sum_surround += 1;
						}
					}
					if (a - 1 > 0) {
						if (arr[a - 1][b] == '*') {
							sum_surround += 1;
						}
					}
					if (a + 1 < x_width) {
						if (arr[a + 1][b] == '*') {
							sum_surround += 1;
						}
					}
					if (a - 1 > 0 && b + 1 < y_weight) {
						if (arr[a - 1][b + 1] == '*') {
							sum_surround += 1;
						}
					}
					if (b + 1 < y_weight) {
						if (arr[a][b + 1] == '*') {
							sum_surround += 1;
						}
					}
					if (a + 1 < x_width && b + 1 < y_weight) {
						if (arr[a + 1][b + 1] == '*') {
							sum_surround += 1;
						}
					}
					String str = sum_surround + "";
					char c = str.charAt(0);
					int b1 = c;
					arr[a][b] = (char) b1;

				}
				if (arr[a][b] == '0') {
					arr[a][b] = ' ';
				}

			}
		}
		return arr;

	}

	public void hit(int x, int y) {
		for (int a = 0; a < x_width; a++) {
			for (int b = 0; b < y_weight; b++) {
				if (x > a * width && x < (a + 1) * width && y > b * weight && y < (b + 1) * weight) {
					boo[a][b] = true;
				}
			}
		}
	}

	public void check(int x, int y) {
		for (int a = 0; a < x_width; a++) {
			for (int b = 0; b < y_weight; b++) {
				if (x > a * width && x < (a + 1) * width && y > b * weight && y < (b + 1) * weight) {
					if (boo2[a][b] == false) {
						boo2[a][b] = true;
					} else
						boo2[a][b] = false;
				}
			}
		}
	}

}
