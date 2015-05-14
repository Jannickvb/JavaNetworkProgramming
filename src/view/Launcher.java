package view;

import java.awt.EventQueue;

import control.ImageController;

public class Launcher {
//	ImageController imageControl = ImageController.instance;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					new GameFrame();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
