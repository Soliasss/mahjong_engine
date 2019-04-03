package fr.univubs.inf1603.mahjong.engine.game;

/**
 * Cette classe va être appelée dans les classes ou sous classes de Zone afin de lever une exception
 * @author COGOLUEGNES Charles
 */
public class ZoneException extends Exception{
	public ZoneException(String msg){
		super(msg);
	}
}

