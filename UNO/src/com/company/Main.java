/**
 * this class runs game
 * @authoe seyed pouria ahmadi 9723002
 */
package com.company;

import java.io.IOException;
import java.util.*;
import java.util.*;

import static com.company.CardValueType.*;

enum Direction{
    clockwise,anticlockwise
}

public class Main {
    // az mavared lazem  obj misazim
    private static List<Card> cards;
    private static List<Player> players;
    private static CardType cardType;
    private static Direction direction;
    private static Card groundCard;
    private static int playerIndex;
    public static final String CPU = "CPU";
    private static Scanner sc;

    public static void main(String[] args) {
        cards=new ArrayList<Card>() ;
        players=new ArrayList<Player>() ;
        cardType=new CardType();
        //create card
        init_cards();
        direction=Direction.clockwise;
        sc=new Scanner(System.in);
        int cnt;
        //anjam bazi
        do{
            System.out.print("Please enter number of players (3~5):");
            cnt=sc.nextInt();
        }while(cnt<3 || cnt>5);
        init_players_cards(cnt);
        init_ground_card();
        Random rnd=new Random();
        playerIndex=rnd.nextInt(cnt);
        boolean groundCardChange=true;
        while (true){
            if(groundCardChange) checkRules();
            int dir=direction==Direction.clockwise?1:-1;
            render();
            if(players.get(playerIndex).getName().substring(0, 3).equals(CPU))
                groundCardChange=players.get(playerIndex).turnCpu(cards,groundCard);
            else
                groundCardChange=players.get(playerIndex).turnPlayer(cards,groundCard);
            render();
            if(players.get(playerIndex).getCardCount()==0) break;
            System.out.println("press enter key to next");
            sc.nextLine();
            playerIndex=(playerIndex+dir+players.size())%players.size();
        }
        Collections.sort(players);
        System.out.println("Players scores : ");
        System.out.println();
        for (Player player : players) {
            System.out.println("   "+player.getName() + " : " + player.getScore());
        }
    }

    /**
     * this method checks rules of game
     */
    private static void checkRules() {
        int dir=direction==Direction.clockwise?1:-1;
        switch (groundCard.getCardValue()){
            case SKIP:
                playerIndex=(playerIndex+2*dir+players.size())%players.size();
                break;
            case DRAW2:
                players.get(playerIndex).addRandomCards(cards,2);
                playerIndex=(playerIndex+dir+players.size())%players.size();
                break;
            case REVERSE:
                direction=(direction==Direction.clockwise?Direction.anticlockwise:Direction.clockwise);
                break;
            case WILDDRAW4:
                players.get(playerIndex).addRandomCards(cards,4);
                playerIndex=(playerIndex+dir+players.size())%players.size();
                break;
        }
    }

    /**
     * this method create all cards
     */
    private static void init_cards(){
        for(CardsType type : cardType.getColorCardTypes()){
            for(CardValueType val: cardType.getColorCardValues()){
                cards.add(new Card(type,val));
                //N0 ha nesf bagie sakhte mishavand
                if(val!= N0) cards.add(new Card(type,val));
            }
        }
        for(int i=0;i<4;i++){
            for(CardsType type : cardType.getWildCardTypes()){
                for(CardValueType val: cardType.getWildCardValues())
                    cards.add(new Card(type,val));
            }
        }
    }

    /**
     * player hara misazim
     * @param cnt tedad player ha
     */
    private static void init_players_cards(int cnt){
        players.add(new Player("Player 1",cards));
        for(int i=1;i<cnt;i++)
            players.add(new Player(CPU+i,cards));
    }

    /**
     * card roye zamin ra meghdar dehi mikinad
     */
    private static void init_ground_card(){
        int i;
        Random rnd=new Random();
        do{
            i=rnd.nextInt(cards.size());
        }while(cards.get(i).getCardType()==CardsType.WILDCARD);
        groundCard=cards.get(i);
        cards.remove(i);
    }

    /**
     * chap etelat bazi
     */
    private static void render(){
        clearScreen();
        System.out.println("###    "+direction.toString()+"     ###");
        for(Player p:players){
            System.out.print(p.getName()+":"+p.getCardCount()+" cards --- ");
        }
        System.out.println();
        System.out.println("---  "+players.get(playerIndex).getName());
        groundCard.render(10,4);
        if(!players.get(playerIndex).getName().substring(0, 3).equals(CPU)){
            players.get(playerIndex).renderCards(10);
        }
    }

    /**
     * console ra pak mikonim
     */

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
