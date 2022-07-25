/**
 * this class is playing the game
 *
 * @author seyed pouria ahmadi 9723002
 */

import java.util.Scanner;

public class Main {

    private static MainBlock mainBlock;
    private static Scanner sc;

    public static void main(String[] args) {
        mainBlock = new MainBlock();
        sc = new Scanner(System.in);
        while (true) {
            mainBlock.render();
            chooseCell();
            mainBlock.render();
            if (checkWin()) break;
            if (mainBlock.checkTwoWon()) {
                System.out.println("Player1 Equal Player2");
                break;
            }
            chooseRotate();
            mainBlock.changePlayer();
            if (checkWin()) break;
        }
    }

    /**
     * this method wants to choose a cell from surface
     */
    private static void chooseCell() {
        int row, col;
        do {
            System.out.print("Please enter clean row and col (from 1~6) to fill it : ");
            try {
                row = Integer.parseInt(sc.next());
            } catch (Exception e) {
                row = 0;
            }
            try {
                col = Integer.parseInt(sc.next());
            } catch (Exception e) {
                col = 0;
            }
            //check if location of cell is true

        } while (!mainBlock.setSurface(row, col));

    }

    /**
     * this method wants to rotate smaller blocks
     */
    private static void chooseRotate() {
        int bl;
        String direction;
        do {
            System.out.print("Please select a sub-block (from 1~4) to rotate :");
            try {
                bl = sc.nextInt();
            } catch (Exception e) {
                bl = 0;
            }
        } while (bl < 1 || bl > 4);
        do {
            System.out.print("Please select (cw or ccw) for clockwise or counterclockwise :");
            direction = sc.next();
        } while (!(direction.equals("cw") || direction.equals("ccw")));
        if (direction.equals("cw"))
            mainBlock.rotateClockWise(bl);
        else
            mainBlock.rotateCounterClockWise(bl);
    }

    /**
     * this method check if game has winner
     *
     * @return true or false
     */
    private static boolean checkWin() {
        int result = mainBlock.checkForWin();
        if (result > 0) {
            System.out.println("Player" + result + " is Win.");
            return true;
        } else if (result == 0) {
            System.out.println("Player1 Equal Player2");
            return true;
        }
        return false;
    }
}
