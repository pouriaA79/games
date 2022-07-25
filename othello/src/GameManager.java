/**
 * this class is manager of game and game playes in this class
 * @author seyed pouria ahmadi 9723002
 */



import java.util.Scanner;

public class GameManager {
    //players
    private Player player1;
    private Player player2;
    //create a board game
    private GameBoard gameBoard = new GameBoard();
//count of moves
    private int count = 0;
    //scanner
    Scanner scanner = new Scanner(System.in);

    /**
     * create a gmae manager
     * @param player1 is player1
     * @param player2 is player 2
     */
    public GameManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

    }

    /**
     * this ethos start and finish the game and control game
     */
    public boolean playGame() {
        //flags if 2 player cant make a move
        boolean flagPass1=false;
        boolean flagPass2=false;
        //if player can make a move
        boolean flagCheck = false;
        int row;
        int column;

        if (count % 2 == 0) {
            flagPass1=false;
            flagCheck = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (player1.checkMove(gameBoard, j, i + 1) == 1)
                        flagCheck = true;

                }

            }
            if (flagCheck) {
                System.out.println(player1.getName() + "  make your move,you are :" + player1.getColor());
                String str = scanner.nextLine();
                String[] strings = str.split(" ");
                row = Integer.parseInt(strings[0]);
                column = change(strings[1]);

                if (player1.makeMove(gameBoard, column, row) != 0)
                    count += 1;


            } else {
                flagPass1=true;
                System.out.println("pass");
                count += 1;
            }
        } else {
            flagPass2=false;
            flagCheck = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (player1.checkMove(gameBoard, j, i + 1) == 1)
                        flagCheck = true;

                }

            }
            if (flagCheck) {

                System.out.println(player2.getName() + "  make your move,you are :" + player2.getColor());
                String str = scanner.nextLine();
                String[] strings = str.split(" ");
                row = Integer.parseInt(strings[0]);
                column = change(strings[1]);


                if (player2.makeMove(gameBoard, column, row) != 0)
                    count += 1;
            } else {
                flagPass2=true;
                System.out.println("pass");
                count += 1;


            }
        }
        if (flagPass1 && flagPass2)
            return false;
        else
            return true;


    }

    /**
     * this method change character to number
     * @param ch is column of move
     * @return int of that
     */
    public int change(String ch) {
        switch (ch) {
            case "A":
            case "a":
                return 0;
            case "B":
            case "b":
                return 1;
            case "C":
            case "c":
                return 2;
            case "D":
            case "d":
                return 3;
            case "E":
            case "e":
                return 4;
            case "F":
            case "f":
                return 5;
            case "G":
            case "g":
                return 6;
            case "H":
            case "h":
                return 7;
            default:
                return 8;


        }
    }


    public static void main(String[] args) {
        Scanner pl1 = new Scanner(System.in);
        Scanner pl2 = new Scanner(System.in);
        String p1, p2;
        p1 = pl1.nextLine();
        p2 = pl2.nextLine();
//create a game with 2 player
        GameManager gameManager = new GameManager(new Player("w", p1), new Player("b", p2));
        GameBoard gameBoard = new GameBoard();

        gameBoard.printBoard();

        while ((gameBoard.getBlackCount() + gameBoard.getWhiteCount() != 64)) {

            if (!gameManager.playGame())
                break;


        }


    }
}






