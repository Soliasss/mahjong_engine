package fr.univubs.inf1603.mahjong.engine.game;

/**
 * L'énumération de tout les combos possibles
 */
public enum Meld{
  PONG{
    @Override
    public String toString(){
      return "PONG";
    }
  },

  CHOW{
    @Override
    public String toString(){
      return "CHOW";
    }
  },

  KONG{
    @Override
    public String toString(){
      return "KONG";
    }
  },

  MAHJONG{
    @Override
    public String toString(){
      return "MAHJONG";
    }
  }
}
