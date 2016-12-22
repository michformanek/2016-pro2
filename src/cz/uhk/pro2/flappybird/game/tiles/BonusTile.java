package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Tile;

public class BonusTile extends AbstractTile{
	private boolean active;
	private Tile emptyTile;

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public BonusTile(Image image, Tile emptyTile) {
		super(image);
		this.active = true;
		this.emptyTile = emptyTile;
	}

	@Override
	public void draw(Graphics g, int x, int y){
		if(active == false && emptyTile != null){
			emptyTile.draw(g, x, y);
		}else{
			super.draw(g, x, y);
		}
	}

	@Override
	public BonusTile clone(){
		return new BonusTile(image,emptyTile);
	}

}
