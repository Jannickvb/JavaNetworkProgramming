package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.entity.MenuBarrel;
import control.ControlManager;
import control.GameStateManager.StateType;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class MenuState extends GameState {

	private String startString;
	private List<MenuBarrel> barrels;
	private BufferedImage bg;
	
	public MenuState(ControlManager cm) {
		super(cm);
		barrels = new ArrayList<>();
		bg = ImageHandler.getScaledImage(ImageHandler.getImage(ImageType.wijzeIndiaan));
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bg,0,0,null);
		drawBarrels(g2,0);
		g2.drawString("BARREL RUN!",
				cm.getGameStateManager().getWidth() / 2 - g2.getFontMetrics().stringWidth("BARREL RUN!") / 2,
				100);
		g2.drawString(startString,
				cm.getGameStateManager().getWidth() / 2 - g2.getFontMetrics().stringWidth(startString) / 2,
				cm.getGameStateManager().getHeight() / 2);
	}

	public void drawBarrels(Graphics2D g2, int i) {
		int index = i;
		if (i < barrels.size()) {
			barrels.get(index).draw(g2);
			index++;
			drawBarrels(g2, index);
		} else {
			return;
		}
	}

	@Override
	public void update() {
		Integer size = (int) (Math.random() * 300);
		MenuBarrel barrel;
		if(Math.floor(Math.random()*25) == 3)
		{
			barrel = new MenuBarrel(
					ImageHandler.getImage(ImageType.barrelSide),
					new Point2D.Double(0,
								cm.getGameStateManager().getHeight() - 400),
								   size);
			barrels.add(barrel);
		}
		for(MenuBarrel b: barrels){
			b.update();
		}
		checkIfBarrelIsBetweenBoundsOfTheCurrentScreenSizeOfTheJFramesContentPane(0);
	}

	public void checkIfBarrelIsBetweenBoundsOfTheCurrentScreenSizeOfTheJFramesContentPane(int i) {
		int index = i;
		if (index < barrels.size())
		{
			if(barrels.get(index).getX() > cm.getGameStateManager().getWidth())
			{
				barrels.remove(index);
			}
			index++;
			checkIfBarrelIsBetweenBoundsOfTheCurrentScreenSizeOfTheJFramesContentPane(index);
		} 
		else 
		{
			return;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			cm.getGameStateManager().setState(StateType.wait);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		startString = "Press enter to start";
		if (cm.getGameStateManager() != null) {
			cm.getGameStateManager().closeClient();
		}
	}

}
