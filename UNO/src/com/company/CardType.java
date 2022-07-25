/**
 * this class  is card type details
 * @author seyed pouria ahmadi 9723002
 */

package com.company;

import java.util.Arrays;
//card type in game
enum CardValueType {
    N0,N1,N2,N3,N4,N5,N6,N7,N8,N9,
    SKIP,REVERSE,DRAW2,
    WILDCOLOR,WILDDRAW4
}

// card type
enum CardsType {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    WILDCARD
}

public class CardType {

    private CardsType[] colorCardtypes;
    private CardsType[] wildCardtypes;
    private CardValueType[] ColorCardValues;
    private CardValueType[] WildCardValues;

    /**
     * this method  create cards wild cards are seprate
     */
    public CardType(){
        colorCardtypes=Arrays.copyOfRange(CardsType.values(),0,4);   //"red","green","blue","yellow"
        wildCardtypes=Arrays.copyOfRange(CardsType.values(),4,5);   //"black"
        ColorCardValues= Arrays.copyOfRange(CardValueType.values(),0,13);
        WildCardValues=Arrays.copyOfRange(CardValueType.values(),13,15);
    }

    /**
     * this method get color of card
     * @return color
     */
    public CardsType[] getColorCardTypes(){
        return colorCardtypes;
    }
    /**
     * this method get value of card
     * @return value
     */

    public CardsType[] getWildCardTypes(){
        return wildCardtypes;
    }
    /**
     * this method get value of card
     * @return value
     */

    public CardValueType[] getColorCardValues(){
        return ColorCardValues;
    }
    /**
     * this method get value of card
     * @return value of card
     */

    public CardValueType[] getWildCardValues(){
        return WildCardValues;
    }

}
