package control;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageController {
	
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public ImageController() {
		
	}

	public BufferedImage getImage(int imageID){
		return images.get(imageID);
	}
}
