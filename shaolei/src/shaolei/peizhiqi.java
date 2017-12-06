package shaolei;

public class peizhiqi {
         private  static int width=10;//横格数
         private  static int height=10;//竖格数
         private  static int sum=((int) (Math.random()*15))+10;//雷的数量
         private  static int picture=3;//图片的张数（x.jpg）
         
		public int getPicture() {
			return picture;
		}
		public void setPicture(int picture) {
			this.picture = picture;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getSum() {
			return sum;
		}
		public void setSum(int sum) {
			this.sum = sum;
		}
}
