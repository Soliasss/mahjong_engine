package fr.univubs.inf1603.mahjong.engine;

/**
 * Cette classe va être appellée dans Move afin de lever une exception
 * @author COGOLUEGNES Charles
 */
public class MoveException extends Exception{
	public MoveException(String msg){
		super(msg);
	}
}
