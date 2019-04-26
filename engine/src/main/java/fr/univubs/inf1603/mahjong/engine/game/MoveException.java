package fr.univubs.inf1603.mahjong.engine.game;

/**
 * Cette classe va être appellée dans Move afin de lever une exception
 */
public class MoveException extends GameException{
	public MoveException(String msg){
		super(msg);
	}
}
