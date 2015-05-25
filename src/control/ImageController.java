package control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import view.Launcher;

public class ImageController {
	
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public ImageController() {
		
	}
	
	static{
		try{
			images.add(ImageIO.read(Launcher.class.getResource("/images/menu_background.jpg")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		menubg
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}
	
	
	
	
}

