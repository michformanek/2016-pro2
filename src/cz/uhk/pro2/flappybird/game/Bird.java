package cz.uhk.pro2.flappybird.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bird implements TickAware{
	//fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 4;
	
	//sou�adnice st�edu pt�ka
	int viewportX;
	double viewportY;//aby se dala jemn� ladit rychlost pad�n�
	
	//rychlost pad�n� (pozitivn�), vzletu(negativn�)
	double velocityY = koefDown;
	//kolik ticku zb�v�, ne� za�ne padat
	int ticksToFall = 0;
	
	public Bird(int initialX, int initialY) {
		this.viewportX = initialX;
		this.viewportY = initialY;
	}
	
	public void kick(){
		velocityY = koefUp; //let� nahoru - m�n� stav
		ticksToFall = ticksFlyingUp; //jak dlouho let� nahoru
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE-2, Tile.SIZE,	Tile.SIZE);
		g.setColor(Color.BLACK);
		g.drawString(viewportX +", "+(int)viewportY, viewportX,(int)viewportY);
	}
	
	public boolean collidesWithRectangle(final int x, final int y, final int w, final int h){
		Ellipse2D.Float birdBoundary = new Ellipse2D.Float(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE-2, Tile.SIZE,Tile.SIZE); // TODO Vytvaret birdBoundary pri ticku
		return birdBoundary.intersects(x, y, w, h);
	}
	
	@Override
	public void tick(long ticksSinceStart) {
		viewportY +=velocityY;
		if(ticksToFall >0){//ptak letel nahoru
			ticksToFall --;
		}else{
			velocityY=koefDown;//ptak zacne padat
		}
	}
	
	public void reset(){
		viewportX = 100;
		viewportY = 100;
		velocityY = Bird.koefDown;
	}

}
