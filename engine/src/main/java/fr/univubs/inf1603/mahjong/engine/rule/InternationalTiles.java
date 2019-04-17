package fr.univubs.inf1603.mahjong.engine.rule;

public enum InternationalTiles implements AbstractTile {
    BAMBOO_1(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.ONE)){
        @Override
        public AbstractTile getNext() {
            return BAMBOO_2;
        }
    },
    BAMBOO_2(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.TWO)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_1;
        }
        
        @Override
        public AbstractTile getNext() {
            return BAMBOO_3;
        }
    },
    BAMBOO_3(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.THREE)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_2;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_4;
        }
    },
    BAMBOO_4(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.FOUR)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_3;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_5;
        }
    },
    BAMBOO_5(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.FIVE)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_4;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_6;
        }
    },
    BAMBOO_6(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.SIX)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_5;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_7;
        }
    },
    BAMBOO_7(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.SEVEN)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_6;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_8;
        }
    },
    BAMBOO_8(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.EIGHT)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_7;
        }

        @Override
        public AbstractTile getNext() {
            return BAMBOO_9;
        }
    },    
    BAMBOO_9(new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE)){
        @Override
        public AbstractTile getPrevious(){
            return BAMBOO_8;
        }
    },
    DOT_1(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.ONE)){
        @Override
        public AbstractTile getNext() {
            return DOT_2;
        }
    },
    DOT_2(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.TWO)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_1;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_3;
        }
    },
    DOT_3(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.THREE)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_2;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_4;
        }
    },
    DOT_4(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.FOUR)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_3;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_5;
        }
    },
    DOT_5(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.FIVE)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_4;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_6;
        }
    },
    DOT_6(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.SIX)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_5;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_7;
        }
    },
    DOT_7(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.SEVEN)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_6;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_8;
        }
    },
    DOT_8(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.EIGHT)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_7;
        }

        @Override
        public AbstractTile getNext() {
            return DOT_9;
        }
    },
    DOT_9(new CommonTile(CommonTile.Family.DOT, CommonTile.Number.NINE)){
        @Override
        public AbstractTile getPrevious(){
            return DOT_8;
        }
    },
    CHARACTER_1(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.ONE)){
        @Override
        public AbstractTile getNext() {
            return CHARACTER_2;
        }
    },
    CHARACTER_2(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.TWO)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_1;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_3;
        }
    },
    CHARACTER_3(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.THREE)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_2;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_4;
        }
    },
    CHARACTER_4(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.FOUR)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_3;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_5;
        }
    },
    CHARACTER_5(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.FIVE)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_4;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_6;
        }
    },
    CHARACTER_6(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.SIX)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_5;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_7;
        }
    },
    CHARACTER_7(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.SEVEN)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_6;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_8;
        }
    },
    CHARACTER_8(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.EIGHT)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_7;
        }

        @Override
        public AbstractTile getNext() {
            return CHARACTER_9;
        }
    },
    CHARACTER_9(new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.NINE)){
        @Override
        public AbstractTile getPrevious(){
            return CHARACTER_8;
        }
    },
    WIND_EAST(new SimpleHonor(Wind.EAST)),
    WIND_NORTH(new SimpleHonor(Wind.NORTH)),
    WIND_WEST(new SimpleHonor(Wind.WEST)),
    WIND_SOUTH(new SimpleHonor(Wind.SOUTH)),

    DRAGON_RED(new SuperiorHonor(SuperiorHonor.Dragon.RED)),
    DRAGON_GREEN(new SuperiorHonor(SuperiorHonor.Dragon.GREEN)),
    DRAGON_WHITE(new SuperiorHonor(SuperiorHonor.Dragon.WHITE)),

    SEASON_WINTER(new SeasonTile(SeasonTile.Season.WINTER)),
    SEASON_SPRING(new SeasonTile(SeasonTile.Season.SPRING)),
    SEASON_SUMMER(new SeasonTile(SeasonTile.Season.SUMMER)),
    SEASON_AUTUMN(new SeasonTile(SeasonTile.Season.AUTUMN)),

    FLOWER_PLUM(new FlowerTile(FlowerTile.Flower.PLUM)),
    FLOWER_BAMBOO(new FlowerTile(FlowerTile.Flower.BAMBOO)),
    FLOWER_CHRYSANTHEMUM(new FlowerTile(FlowerTile.Flower.CHRYSANTHEMUM)),
    FLOWER_ORCHID(new FlowerTile(FlowerTile.Flower.ORCHID));
    
    private AbstractTile tile;
    
    private InternationalTiles(AbstractTile abstractTile) {
        this.tile = abstractTile;
    }

    @Override
    public String toNormalizedName() {
        return this.tile.toNormalizedName();
    }
}
