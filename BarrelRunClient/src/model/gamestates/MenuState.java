package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.MyGeneric;
import model.entity.MenuBarrel;
import control.ControlManager;
import control.ImageHandler;
import control.GameStateManager.StateType;
import control.ImageHandler.ImageType;

public class MenuState extends GameState {

	private String startString;
	private List<MenuBarrel> barrels;

	public MenuState(ControlManager cm) {
		super(cm);
		barrels = new ArrayList<>();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(startString,
					cm.getGameStateManager().getWidth() / 2 - g2.getFontMetrics().stringWidth(startString) / 2,
					cm.getGameStateManager().getHeight() / 2);
		if(barrels.size()!=0)
			drawBarrels(g2,0);
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
		MyGeneric<Number> size = new MyGeneric<>();
		size.change(Math.random() * 300);
		Integer stuff = size.get().intValue();
		MenuBarrel barrel;
		if(Math.floor(Math.random()*25) == 3)
		{
			barrel = new MenuBarrel(
					new Point2D.Double(0,
								cm.getGameStateManager().getHeight() - 100),
								   stuff);
			barrels.add(barrel);
		}
		for(MenuBarrel b: barrels){
			b.update();
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
