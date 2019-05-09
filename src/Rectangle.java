
public class Rectangle implements Comparable<Rectangle> {
	int width;
	int height;
	
	public Rectangle(int w, int h){
		width=  w;
		height = h; 
	}

	@Override
	public int compareTo(Rectangle rect) {
		if(width*height > rect.width * rect.height) {
			return 1;
		}else if(width*height < rect.width * rect.height) {
			return -1;
		}
		return 0;
	}
	
}
