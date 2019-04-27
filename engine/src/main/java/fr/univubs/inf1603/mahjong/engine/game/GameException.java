package fr.univubs.inf1603.mahjong.engine.game;

/**
 * Cette classe va être appelée dans la classe Game afin de lever une exception
 */
public class GameException extends Exception{
	public GameException(String msg){
		super(msg);
	}
}
