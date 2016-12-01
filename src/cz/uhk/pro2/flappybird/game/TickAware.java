package cz.uhk.pro2.flappybird.game;
/**
 * rozhran� pro objekty, kter� pot�ebuj� v�d�t, kolik �asu (tick) ub�hlo od za��tku hry
 * @author nemecon3
 *
 */
public interface TickAware {
	/**
	 * zm�n� stav hern� nentity s ohledem na zm�nu herniho �asu
	 * 
	 * @param ticksSinceStart �as - po�et tiku - od zah�jen� hry
	 */
	void tick(long ticksSinceStart);

}
