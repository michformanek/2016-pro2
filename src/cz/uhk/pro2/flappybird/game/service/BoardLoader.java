package cz.uhk.pro2.flappybird.game.service;

import cz.uhk.pro2.flappybird.game.GameBoard;

/**
 * rozhran� pro v�echny t��dy, kter� um� nac�st hern� plochu
 */
public interface BoardLoader {
	/*na�te a vr�t� hern� plochu*/
	public GameBoard getGameboard();
}
