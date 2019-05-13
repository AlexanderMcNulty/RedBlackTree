package cs146S19.McNulty.project4;

public class Rectangle implements Comparable<Rectangle> {
	int width;
	int height;
	
	public Rectangle(int w, int h){
		width=  w;
		height = h; 
	}
	
	@Override
	public String toString() {
		return String.valueOf(width*height);
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
