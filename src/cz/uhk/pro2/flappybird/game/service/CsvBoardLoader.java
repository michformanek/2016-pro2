package cz.uhk.pro2.flappybird.game.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.uhk.pro2.flappybird.game.GameBoard;
import cz.uhk.pro2.flappybird.game.Tile;
import cz.uhk.pro2.flappybird.game.tiles.WallTile;

public class CsvBoardLoader implements BoardLoader {
	InputStream is;// stream ze kterého se naèíta level

	public CsvBoardLoader(InputStream is) {
		this.is = is;
	}

	@Override
	public GameBoard getGameboard() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is));){
			//radek s poctem typu dlazdic
			String[] line = br.readLine().split(";");
			int numberOfTypes = Integer.parseInt(line[0]);
			System.out.println("Number of types: " + numberOfTypes);
			for(int i = 0; i< numberOfTypes; i++){
				line = br.readLine().split(";");
				String type = line[0];
				String clazz = line[1];
				int spriteX = Integer.parseInt(line[2]);
				int spriteY = Integer.parseInt(line[3]);
				int spriteWidth = Integer.parseInt(line[4]);
				int spriteHeight = Integer.parseInt(line[5]);
				String url = line[6]; //TODO
			}
			//radek s poctem radku a sloupcu v matici herni plochy
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			System.out.println("Reading gameboard with " + rows + " rows and " + columns + " columns");
			//vyrobime matici dlazdic
			Tile[][] tiles = new Tile[rows][columns];
			for(int i = 0; i < rows; i++){
				line = br.readLine().split(";");
				String t;
				for(int j = 0; j<columns; j++){
					if(j < line.length){  //Prazdne bunky na konci radku
						t = line[j];
					}else{ //Kdyz bunka chybi, povazujeme ji za prazdnou
						t = "";
					}
					if(!"".equals(t)){
						tiles[i][j] = new WallTile();
					}
				}

			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri nacteni souboru s levelem");
		}
	}

}
