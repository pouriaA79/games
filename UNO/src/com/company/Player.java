/**
 * this class represents a player details
 * @author seyed pouria ahmadi 9723002
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.company.Main.CPU;
//implementing comparable
public class Player  implements Comparable<Player> {
    private String name;
    private List<Card> cards;
    private Scanner sc;
    Random rnd;

    /**
     * create player and give them  random cards
     * @param name is name
     * @param allCards are all cards of game
     */

    public Player(String name, List<Card> allCards) {
        rnd = new Random();
        sc=new Scanner(System.in);
        this.name = name;
        cards = new ArrayList<Card>();
        //give random cards
        for (int i = 0; i < 7; i++) {
            int a = rnd.nextInt(allCards.size());
            cards.add(allCards.get(a));
            allCards.remove(a);
        }
    }

    /**
     * print all cards of player
     * har card 12 character arz va 6 character ertefa darad va yek character beyn keteba fasele darad
     */

    public void renderCards(int y) {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).render(1 + (i % 8) * 14, 1+y + (i / 8)*6);
        }
    }

    /**
     *
     * @param allCards  are all cards of game
     * @param groundCard is card on the ground
     * @return true or false
     */

    public boolean turnCpu(List<Card> allCards, Card groundCard) {
        //this for check if cpu doesnt have wild card or suitable card
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardType() != CardsType.WILDCARD &&
                    (cards.get(i).getCardType() == groundCard.getCardType() || cards.get(i).getCardValue() == groundCard.getCardValue())) {
                allCards.add(groundCard);
                groundCard.change(cards.get(i));
                cards.remove(i);
                return true;
            }
        }
        //checking for wild card

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardType() == CardsType.WILDCARD ) {
                allCards.add(groundCard);
                groundCard.change(cards.get(i));
                cards.remove(i);
                changeCardColor(groundCard);
                return true;
            }
        }
        return getOneCardFromAllCards(allCards,groundCard);
    }

    /**
     * this method is about player
     */
    public boolean turnPlayer(List<Card> allCards, Card groundCard) {
        int c;
        //mitone cart bendaze ya na
        boolean can=false,canWildcard=true;
        for (Card card : cards) {
            //sharayet ra check mikond
            if (card.getCardType() == groundCard.getCardType() || card.getCardValue() == groundCard.getCardValue() || card.getCardType() == CardsType.WILDCARD) {
                can = true;
                if(card.getCardType() != CardsType.WILDCARD)
                    canWildcard=false;
            }
        }
        if(can){
            do{
                System.out.print("Choose one card from 1~"+cards.size()+" that can match with ground card :");
                try {
                    c=sc.nextInt();
                    if(!canWildcard && cards.get(c-1).getCardType()==CardsType.WILDCARD) c=0;
                }catch (Exception e){
                    c=0;
                }
            }while (c<1 || c>cards.size() ||
                    (cards.get(c-1).getCardType()!=CardsType.WILDCARD && cards.get(c-1).getCardValue()!=groundCard.getCardValue()
                            && cards.get(c-1).getCardType()!=groundCard.getCardType()));

            allCards.add(groundCard);
            groundCard.change(cards.get(c-1));
            if(cards.get(c-1).getCardType()==CardsType.WILDCARD) changeCardColor(groundCard);
            cards.remove(c-1);
            return true;
        }else{
            return getOneCardFromAllCards(allCards,groundCard);
        }
    }

    /**
     * this method change color of card
     * @param groundCard is back ground card
     */

    private void changeCardColor(Card groundCard){
        CardType cardType=new CardType();
        if(getName().substring(0, 3).equals(CPU)){
            Random rnd=new Random();
            int r=rnd.nextInt(4);
            groundCard.changeColor(cardType.getColorCardTypes()[r]);
        }else{
            int c=0;
            do{
                System.out.print("Enter new card color from 1~4 (RED,GREEN,BLUE,YELLOW) :");
                try {
                    c=sc.nextInt();
                }catch (Exception e){
                    c=0;
                }
            }while(c<1 || c>4);
            groundCard.changeColor(cardType.getColorCardTypes()[c-1]);
        }
    }

    /**
     * this method  get one card from cards
     * @param allCards are all cards
     * @param groundCard is back ground card
     * @return true or false
     */
    private boolean getOneCardFromAllCards(List<Card> allCards, Card groundCard){
        int r = rnd.nextInt(allCards.size());
        if(allCards.get(r).getCardType() == groundCard.getCardType() || allCards.get(r).getCardValue() == groundCard.getCardValue()){
            allCards.add(groundCard);
            groundCard.change(allCards.get(r));
            return true;
        }else{
            cards.add(allCards.get(r));
        }
        allCards.remove(r);
        return false;
    }

    /**
     * ba in method card ezafe mikonim
     * @param count tedad card hayi k byd ezafe konim
     */
    public void addRandomCards(List<Card> allCards, int count) {
        Random rnd = new Random();
        for (int i = 0; i < count && allCards.size() > 0; i++) {
            int r = rnd.nextInt(allCards.size());
            cards.add(allCards.get(r));
            allCards.remove(r);
        }
    }

    /**
     * this method return name
     */
    public String getName() {
        return name;
    }

    /**
     *  this method return cards counr
     * @return
     */

    public int getCardCount() {
        return cards.size();
    }

    /**
     * this methid return player score
     */

    public int getScore() {
        int score=0;
        for(Card c:cards){
            int s=0;
            switch (c.getCardValue()){
                case N1:s=1;break;
                case N2:s=2;break;
                case N3:s=3;break;
                case N4:s=4;break;
                case N5:s=5;break;
                case N6:s=6;break;
                case N7:s=7;break;
                case N8:s=8;break;
                case N9:s=9;break;
                case DRAW2:
                case REVERSE:
                case SKIP:s=20;break;
                case WILDCOLOR:
                case WILDDRAW4:s=50;break;
            }
            score+=s;
        }
        return score;
    }

    /**
     * ba in method mikhahim player ha ra sort konim
     * @param o yeki az player ha
     */
    @Override
    public int compareTo(Player o) {
        return this.getScore()-o.getScore();
    }
}
