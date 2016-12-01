package cz.uhk.pro2.flappybird.game;

import java.awt.Color;
import java.awt.Graphics;

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
	
	@Override
	public void tick(long ticksSinceStart) {
		viewportY +=velocityY;
		if(ticksToFall >0){//ptak letel nahoru
			ticksToFall --;
		}else{
			velocityY=koefDown;//ptak zacne padat
		}
	}

}
