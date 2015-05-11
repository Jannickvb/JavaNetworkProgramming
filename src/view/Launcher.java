package view;

import java.awt.EventQueue;

public class Launcher {
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
