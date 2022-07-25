/**
 * this class represents card details
 * @ author  seyed pouria ahmadi 9723002
 */

package com.company;

public class Card {

    //card type
    private CardsType cardType;
    //value of card
    private CardValueType value;

    /**
     * creating a card
     * @param type is type of card
     * @param val is value of card
     */
    public Card(CardsType type, CardValueType val) {
        this.cardType=type;
        this.value=val;
    }

    /**
     * this method returns card type
     * @return type of card
     */
    public CardsType getCardType(){
        return cardType;
    }

    /**
     * this method  returns value of cards
     * @return value of cards
     */
    public CardValueType getCardValue(){
        return value;
    }

    /**
     * this method change cards
     * @param toIt is details that card must be
     */

    public void change(Card toIt){
        cardType=toIt.cardType;
        value=toIt.value;
    }

    /**
     * this method change color of card
     * @param cardType is new card
     */
    public void changeColor(CardsType cardType){
        this.cardType=cardType;
    }

    /**
     * this method print on console
     * @param x is a detail on console
     * @param y is a detail on console
     */
    public void render(int x,int y){
        String color=new String();
        switch (cardType){
            case RED:color=Colors.RED;break;
            case GREEN:color=Colors.GREEN;break;
            case BLUE:color=Colors.BLUE;break;
            case YELLOW:color=Colors.YELLOW;break;
            case WILDCARD:color=Colors.WHITE;break;
        }
        System.out.print(color);
        gotoXY(x,y);
        System.out.print("|$$$$$$$$$$|");
        // go one line down
        gotoXY(x,y+1);
        System.out.print("|          |");
        gotoXY(x,y+2);
        System.out.print("|          |");
        gotoXY(x,y+3);
        System.out.print("|          |");
        gotoXY(x,y+4);
        System.out.print("|$$$$$$$$$$|");
        //print value of card
        gotoXY(x+6-value.toString().length()/2,y+2);
        System.out.print(value);
        gotoXY(1,y+6);
        System.out.print(Colors.RESET);
    }

    /**
     * change pointer on console
     * @param x is detail of console
     * @param y is detail of console
     */
    private void gotoXY(int x,int y){
        System.out.print(String.format("\033[%d;%dH",y,x));
    }
}
