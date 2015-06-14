package control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import view.GameFrame;

public class ImageHandler {
	
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private static Scalr.Mode mode;
	public ImageHandler() {
		
	}
	
	static{
		try {
			addImage("player","player1.png");
			addImage("player","player2.png");
			addImage("objects", "barrel.png");
			addImage("objects", "barrelClient.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
		player1,
		player2,
		barrel,
		clientIcon,
		
	}
	
	public static void addImage(String folderName, String fileName) throws IOException{
		if(folderName.equals(""))
			images.add(ImageIO.read(GameFrame.class.getResource("/images/" + fileName)));
		else
			images.add(ImageIO.read(GameFrame.class.getResource("/images/"+ folderName + "/" + fileName)));
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}

	public static BufferedImage getScaledImage(BufferedImage image){
		getMode(image);
		int targetSize;
		if(mode == Scalr.Mode.FIT_TO_HEIGHT)
			targetSize = ControlManager.screenHeight;
		else
			targetSize = ControlManager.screenWidth;
		image = Scalr.resize(image, mode, targetSize,Scalr.OP_ANTIALIAS);
		return image;
	}
	
	private static Scalr.Mode getMode(BufferedImage image){
		if (image.getHeight() < image.getWidth()) {
			mode = Scalr.Mode.FIT_TO_WIDTH;
		} else {
			mode = Scalr.Mode.FIT_TO_HEIGHT;
		}
		return mode;
	}
}

