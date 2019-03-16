package fr.univubs.inf1603.mahjong.engine;

/**
 * This class will be called if Move encounters an exception
 * @author COGOLUEGNES Charles
 */
public class MoveException extends Exception{
	public MoveException(String msg){
		super(msg);
	}
}
