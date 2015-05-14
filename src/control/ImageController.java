package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageController {
	
//	public static final ImageController instance = new ImageController();
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public ImageController() {
		images.add(loadImage("/images/menu_background.jpg"));
	}

	public BufferedImage getImage(int imageID){
		return images.get(imageID);
	}
	
	public BufferedImage loadImage(String imagePath){		
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			System.out.println("Wrong Path");
		}
		return image;
	}
	
}
