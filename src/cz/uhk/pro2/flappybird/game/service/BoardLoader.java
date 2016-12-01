package cz.uhk.pro2.flappybird.game.service;

import cz.uhk.pro2.flappybird.game.GameBoard;

/**
 * rozhraní pro všechny tøídy, které umí nacíst herní plochu
 */
public interface BoardLoader {
	/*naète a vrátí herní plochu*/
	public GameBoard getGameboard();
}
