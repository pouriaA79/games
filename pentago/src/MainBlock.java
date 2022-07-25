/**
 * this class represents a main block or board of the game
 *
 * @author seyed pouria ahmadi 9723002
 */

import java.io.IOException;
import java.util.Random;

public class MainBlock {
    //the surface of the game
    private String[][] surface;
    //players of game
    private String[] player;
    //round of which player
    private Boolean round;

    /**
     * main block of game
     */
    public MainBlock() {
        //create cells of surface
        surface = new String[6][6];
        //players
        player = new String[2];
        player[0] = "B";
        player[1] = "R";
        // make a random boolean of round that which player starts
        round = (new Random()).nextBoolean();
        //make cells
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                surface[i][j] = ".";
    }

    /**
     * this method update surface and print it again with try catch
     */
    public void render() {
        try {
            //reference of  this line is https://www.codota.com/code/java/methods/java.lang.ProcessBuilder/inheritIO
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("you have" + ex);
        }
        //design
        for (int i = 0; i < 6; i++) {
            if (i % 3 == 0 && i != 0) {
                for (int j = 0; j < 18; j++)
                    System.out.print("-");
                System.out.print("\n");
            }
            for (int j = 0; j < 6; j++) {
                if (j % 3 == 0 && j != 0) System.out.print('|');
                System.out.print(" " + surface[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n******   Player " + (round ? 2 : 1) + "    *******\n");
    }

    /**
     * charkhesh yeki az 4 block dar jahat aghrabe saat
     * @param subBlock is number of block
     */
    public void rotateClockWise(int subBlock) {
        int x = (subBlock - 1) % 2;
        int y = (subBlock - 1) / 2;
        String[] temp = {
                surface[getY(subBlock, 0)][getX(subBlock, 0)],
                surface[getY(subBlock, 1)][getX(subBlock, 0)],
                surface[getY(subBlock, 2)][getX(subBlock, 0)]
        };
        surface[getY(subBlock, 0)][getX(subBlock, 0)] = surface[getY(subBlock, 2)][getX(subBlock, 0)];
        surface[getY(subBlock, 1)][getX(subBlock, 0)] = surface[getY(subBlock, 2)][getX(subBlock, 1)];
        surface[getY(subBlock, 2)][getX(subBlock, 0)] = surface[getY(subBlock, 2)][getX(subBlock, 2)];

        surface[getY(subBlock, 2)][getX(subBlock, 2)] = surface[getY(subBlock, 0)][getX(subBlock, 2)];
        surface[getY(subBlock, 2)][getX(subBlock, 1)] = surface[getY(subBlock, 1)][getX(subBlock, 2)];

        surface[getY(subBlock, 0)][getX(subBlock, 2)] = temp[0];
        surface[getY(subBlock, 1)][getX(subBlock, 2)] = surface[getY(subBlock, 0)][getX(subBlock, 1)];

        surface[getY(subBlock, 0)][getX(subBlock, 1)] = temp[1];
    }

    /**
     * charkhesh yeki az 4 block dar khalaf jahat aghrabe saat
     * @param subBlock is number of block
     */

    public void rotateCounterClockWise(int subBlock) {
        int x = (subBlock - 1) % 2;
        int y = (subBlock - 1) / 2;
        String[] temp = {
                surface[getY(subBlock, 0)][getX(subBlock, 0)],
                surface[getY(subBlock, 0)][getX(subBlock, 1)],
                surface[getY(subBlock, 0)][getX(subBlock, 2)],
        };

        surface[getY(subBlock, 0)][getX(subBlock, 0)] = surface[getY(subBlock, 0)][getX(subBlock, 2)];
        surface[getY(subBlock, 0)][getX(subBlock, 1)] = surface[getY(subBlock, 1)][getX(subBlock, 2)];
        surface[getY(subBlock, 0)][getX(subBlock, 2)] = surface[getY(subBlock, 2)][getX(subBlock, 2)];

        surface[getY(subBlock, 1)][getX(subBlock, 2)] = surface[getY(subBlock, 2)][getX(subBlock, 1)];
        surface[getY(subBlock, 2)][getX(subBlock, 2)] = surface[getY(subBlock, 2)][getX(subBlock, 0)];

        surface[getY(subBlock, 2)][getX(subBlock, 1)] = surface[getY(subBlock, 1)][getX(subBlock, 0)];
        surface[getY(subBlock, 2)][getX(subBlock, 0)] = temp[0];

        surface[getY(subBlock, 1)][getX(subBlock, 0)] = temp[1];

    }

    /**
     *this method check if row and column are true and in range and not belongs to a player
     * @param y is row
     * @param x is column
     * @return true or false
     */
    public boolean setSurface(int y, int x) {
        if (x < 1 || x > 6 || y < 1 || y > 6 || !surface[y - 1][x - 1].equals(".")) return false;
        surface[y - 1][x - 1] = player[round ? 1 : 0];
        return true;
    }

    /**
     * this method change player
     */
    public void changePlayer() {
        round = !round;
    }

    /**
     * this method check if game has winner
     * @return which player won
     */

    public int checkForWin() {
        boolean p1 = checkPlayersWin(0);
        boolean p2 = checkPlayersWin(1);
        if (p1 && p2) return 0;
        else if (p1) return 1;
        else if (p2) return 2;
        else return -1;
    }

    /**
     * this method check if player won
     * @param playerIndex is index of player
     * @return true or false
     */
    private boolean checkPlayersWin(int playerIndex) {
        for (int i = 0; i < 6; i++) {
            // Check Horizontally
            int cnt = 0;
            for (int j = 0; j < 6; j++) {
                if (surface[i][j].equals(player[playerIndex])) cnt++;
                else cnt = 0;
                if (cnt == 5) return true;
            }
            // Check Vertically
            cnt = 0;
            for (int j = 0; j < 6; j++) {
                if (surface[j][i].equals(player[playerIndex])) cnt++;
                else cnt = 0;
                if (cnt == 5) return true;
            }
        }
        // Check Diameters
        int cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0, cnt5 = 0, cnt6 = 0;
        for (int j = 0; j < 6; j++) {
            if (surface[j][j].equals(player[playerIndex])) cnt1++;
            else cnt1 = 0;
            if (surface[5 - j][j].equals(player[playerIndex])) cnt2++;
            else cnt2 = 0;
            if (j + 1 < 6 && surface[j + 1][j].equals(player[playerIndex])) cnt3++;
            else cnt3 = 0;
            if (j + 1 < 6 && surface[j][j + 1].equals(player[playerIndex])) cnt4++;
            else cnt4 = 0;
            if (j + 1 < 6 && surface[5 - j][j + 1].equals(player[playerIndex])) cnt5++;
            else cnt5 = 0;
            if (j + 1 < 6 && surface[j + 1][5 - j].equals(player[playerIndex])) cnt6++;
            else cnt6 = 0;
            if (cnt1 == 5 || cnt2 == 5 || cnt3 == 5 || cnt4 == 5 || cnt5 == 5 || cnt6 == 5) return true;
        }
        return false;
    }

    /**
     * this method checks if empty cell exist in surface
     * @return true or false
     */

    public boolean checkTwoWon() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                if (surface[i][j].equals(".")) return false;
        return true;
    }

    /**
     * this method gives row of main block
     * @param subIndex is 1 to 4 smaller block
     * @param subRow is number of row in smaller block
     * @return number of row in main one block
     */

    private int getY(int subIndex, int subRow) {
        int y = (subIndex - 1) / 2;
        return subRow + y * 3;
    }

    /**
     * this method gives column of main block
     * @param subIndex is 1 to 4 smaller block
     * @param subCol is number of column in smaller block
     * @return number of column in main one block
     */

    private int getX(int subIndex, int subCol) {
        int x = (subIndex - 1) % 2;
        return subCol + x * 3;
    }

}
