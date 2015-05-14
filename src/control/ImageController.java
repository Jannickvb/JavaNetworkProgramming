package control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageController {
	
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	public static final ImageController instance = new ImageController();
	
	public ImageController() {
		images.add(loadImage("/menu_background.jpg"));
	}

	public BufferedImage getImage(int imageID){
		return images.get(imageID);
	}
	
	public BufferedImage loadImage(String imagePath){
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResource(imagePath));
			return image;
		} catch (IOException e) {
			System.out.println("Wrong Path");
		}
		return null;
	}
}
