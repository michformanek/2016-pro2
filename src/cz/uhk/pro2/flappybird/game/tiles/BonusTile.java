package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Image;

public class BonusTile extends AbstractTile{
	
	private boolean active;
	
	public boolean isActive(){
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public BonusTile(Image image) {
		super(image);
		active = true;
	}


}
