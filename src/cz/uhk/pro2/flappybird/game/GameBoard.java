package cz.uhk.pro2.flappybird.game;

import java.awt.*;

import cz.uhk.pro2.flappybird.game.tiles.WallTile;

public class GameBoard implements TickAware{
	Tile[][] tiles;//matice dlazdic na herni plose
	int shiftX; // o kolik pixelu se svet posunul
	int widthPix;//sirka hraci plochy
	Bird bird; //hern� pt�k
	
	public GameBoard(){
		//TODO jen testovaci data, nutno udelat nacitani dat ze souboru
		tiles = new Tile[20][20];
		//tiles[2][1] =new WallTile();
		
		bird = new Bird(100, 100); //TODO um�stit do st�edu okna?
	}
	
	public GameBoard(final Tile[][] tiles){
		this.tiles = tiles;
		bird = new Bird(100, 100); //TODO um�stit do st�edu okna?
	}
	public void setWidthPix(int widthPix) {
		this.widthPix = widthPix;
	}

	public void kickTheBird(){
		bird.kick();
	}
	
	/**
	 * vykresli celou herni plochu (sloupy, bonusy, ptaka) na herni platno g
	 * 
	 * @param g
	 */
	public void draw(Graphics g){
		//j souradnice prvni drazdice vlevo, kterou je potreba kreslit
		int minJ = shiftX/Tile.SIZE;
		//pocet dlazdic na sirku, kolik je nutno kreslit do viewportu
		//+2 muze chybet cast bunky vlevo a pravo kvuli celocislenu deleni
		int countJ = widthPix/Tile.SIZE+2;
		//i je cislo radku
		for (int i=0;i<tiles.length;i++){
			for(int j=minJ;j<countJ+minJ;j++){
				 //aby level b�z�l po��d dokola, j se na konci pole vrac� na 0; tiles0 je pocet sloupc�
				int modJ = j% tiles[0].length;
				Tile t =  tiles[i][modJ];
				if(t!=null){
					//v bunce je nejaka dlazdice - ne pozadi
					//vykresli
					int viewportX=j*Tile.SIZE-shiftX;
					int viewportY=i*Tile.SIZE;
					t.draw(g, viewportX,viewportY);
					
				}
			}
		}
		// vykreslit ptaka
		bird.draw(g);
		
	}

	@Override
	public void tick(long ticksSinceStart) {
		//s ka�d�m tikem ve h�e posuneme hru o jeden pixel
		//tj. po�et ticku a pixelu se rovnaj�
		shiftX=(int)ticksSinceStart;
		
		//TODO d�me v�d�t pt�kovi, �e hodiny tickly
		bird.tick(ticksSinceStart);
	}
	
	
}
