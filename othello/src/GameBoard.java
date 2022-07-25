/**
 * this class represent a board
 */

public class GameBoard {
    //count of black and white
    private int blackCount = 0;
    private int whiteCount = 0;
    //cells
    private Cell cells[] = new Cell[64];

    /**
     * create a board for game
     */
    public GameBoard() {
        for (int i = 0; i < 64; i++) {
            cells[i] = new Cell();
            cells[i].setColor(" ");
        }
        this.cells[27].setEmpty(false);
        this.cells[28].setEmpty(false);
        this.cells[35].setEmpty(false);
        this.cells[36].setEmpty(false);
        this.cells[27].setColor("w");
        this.cells[28].setColor("b");
        this.cells[35].setColor("b");
        this.cells[36].setColor("w");

    }

    /**
     * @return number of black cells
     */
    public int getBlackCount() {
        return blackCount;
    }

    /**
     * @return number of white cells
     */

    public int getWhiteCount() {
        return whiteCount;
    }

    private Player player;

    /**
     * this method check if players move is valid
     *
     * @param color  is color of player
     * @param row    is row of move
     * @param column is column of move
     * @return return situation
     */
    public int validMove(String color, int row, int column) {
        //in method dar har 8 jahat momken valid bodan ra check milkonad

        boolean flagDifferentColor = false;
        int differentColor = 0;
        int check = 0;
        int number = (row - 1) * 8 + (column);
        if (row >= 1 && row <= 8 && column >= 0 && column <= 7) {

            if (cells[number].isEmpty()) {

                //aghab
                check = number - 1;
                while (check >= (row - 1) * 8) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;
                    }
                    check -= 1;
                    if (flagDifferentColor && differentColor - 1 >= (row - 1) * 8 && cells[differentColor - 1].getColor().equals(color))
                        return 1;

                }
                //jelo
                flagDifferentColor = false;
                differentColor = 0;
                check = number + 1;
                while (check < (row) * 8) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;

                        differentColor = check;

                    }
                    check += 1;
                    if (flagDifferentColor && differentColor + 1 < row * 8 && cells[differentColor + 1].getColor().equals(color))
                        return 1;
                }
                //bala
                flagDifferentColor = false;
                differentColor = 0;
                check = number - 8;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;


                    }
                    check -= 8;
                    if (flagDifferentColor && differentColor - 8 >= 0 && cells[differentColor - 8].getColor().equals(color)) {

                        return 1;
                    }
                }
                //paein
                flagDifferentColor = false;

                differentColor = 0;
                check = number + 8;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;


                    }
                    check += 8;
                    if (flagDifferentColor && differentColor + 8 <= 63 && cells[differentColor + 8].getColor().equals(color)) {

                        return 1;


                    }

                }
                //orib bala
                flagDifferentColor = false;
                differentColor = 0;
                check = number - 7;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;

                    }
                    check -= 7;
                    if (flagDifferentColor && differentColor - 7 >= 0 && cells[differentColor - 7].getColor().equals(color))
                        return 1;
                }
                //orib paeen
                flagDifferentColor = false;
                differentColor = 0;
                check = number + 7;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;

                    }
                    check += 7;
                    if (flagDifferentColor && differentColor + 7 <= 63 && cells[differentColor + 7].getColor().equals(color))
                        return 1;

                }
                //orib paeen
                flagDifferentColor = false;
                differentColor = 0;
                check = number + 9;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;

                    }
                    check += 9;
                    if (flagDifferentColor && differentColor + 9 <= 63 && cells[differentColor + 9].getColor().equals(color))
                        return 1;

                }
                //orib bala

                flagDifferentColor = false;
                differentColor = 0;
                check = number - 9;
                while (check >= 0 && check <= 63) {
                    if (cells[check].getColor().equals(" "))
                        break;


                    if (!cells[check].getColor().equals(color)) {
                        flagDifferentColor = true;
                        differentColor = check;

                    }
                    check -= 9;
                    if (flagDifferentColor && cells[differentColor - 9].getColor().equals(color))
                        return 1;

                }


                return 0;

            }

        }
        return 0;
    }

    /**
     * this method change color of some cells if it is possible
     *
     * @param color  is color of player
     * @param row    is row of move
     * @param column is column of move
     */
    public void putCell(int column, int row, String color) {
        //in method da har 8 jahat momken agar mojaz bashad mohre ha ra bar migardanad
        boolean flag;
        if (color.equals("w"))
            flag = true;
        else
            flag = false;
        cells[(row - 1) * 8 + column].setColor(color);
        cells[(row - 1) * 8 + column].setEmpty(false);
        int number = (row - 1) * 8 + (column);
        int check = 0;
        boolean flagDifferentColor = false;
        int differentColor = 0;
        //aghab
        check = number - 2;
        while (check >= (row - 1) * 8) {

            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;

                break;
            }
            check -= 1;
        }
        while (flagDifferentColor && !cells[differentColor + 1].getColor().equals(color)) {

            if (flag)
                cells[differentColor + 1].setColor("w");
            else
                cells[differentColor + 1].setColor("b");
            differentColor += 1;
        }
        //jelo
        flagDifferentColor = false;
        check = number + 2;
        differentColor = 0;
        while (check < (row) * 8) {


            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check += 1;
        }

        while (flagDifferentColor && !cells[differentColor - 1].getColor().equals(color)) {

            if (flag)
                cells[differentColor - 1].setColor("w");
            else
                cells[differentColor - 1].setColor("b");
            differentColor -= 1;


        }

        //bala
        flagDifferentColor = false;
        differentColor = 0;
        check = number - 8;
        while (check >= 0) {


            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check -= 8;
        }
        while (flagDifferentColor && !cells[differentColor + 8].getColor().equals(color)) {
            if (flag)
                cells[differentColor + 8].setColor("w");
            else
                cells[differentColor + 8].setColor("b");
            differentColor += 8;
        }


        //paein
        flagDifferentColor = false;
        differentColor = 0;
        check = number + 8;
        while (check <= 63) {


            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;

                break;
            }
            check += 8;
        }
        while (flagDifferentColor && !cells[differentColor - 8].getColor().equals(color)) {
            if (flag)
                cells[differentColor - 8].setColor("w");
            else
                cells[differentColor - 8].setColor("b");
            differentColor -= 8;
        }
        //orib1
        flagDifferentColor = false;

        differentColor = 0;
        check = number + 7;
        while (check <= 63 && check >= 0) {


            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check += 7;
        }
        while (flagDifferentColor && !cells[differentColor - 7].getColor().equals(color)) {
            if (flag)
                cells[differentColor - 7].setColor("w");
            else
                cells[differentColor - 7].setColor("b");
            differentColor -= 7;
        }
        //orib2
        flagDifferentColor = false;

        differentColor = 0;
        check = number - 7;
        while (check >= 0 && check <= 63) {

            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check -= 7;
        }
        while (flagDifferentColor && !cells[differentColor + 7].getColor().equals(color)) {
            if (flag)
                cells[differentColor + 7].setColor("w");
            else
                cells[differentColor + 7].setColor("b");
            differentColor += 7;
        }


        flagDifferentColor = false;
        differentColor = 0;
        check = number - 9;
        while (check >= 0 && check <= 63) {
            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check -= 9;
        }
        while (flagDifferentColor && !cells[differentColor + 9].getColor().equals(color)) {
            if (flag)
                cells[differentColor + 9].setColor("w");
            else
                cells[differentColor + 9].setColor("b");
            differentColor += 9;
        }


        flagDifferentColor = false;
        differentColor = 0;
        check = number + 9;
        while (check >= 0 && check <= 63) {
            if (cells[check].getColor().equals(color)) {
                flagDifferentColor = true;
                differentColor = check;
                break;
            }
            check += 9;
        }
        while (flagDifferentColor && !cells[differentColor - 9].getColor().equals(color)) {
            if (flag)
                cells[differentColor - 9].setColor("w");
            else
                cells[differentColor - 9].setColor("b");
            differentColor -= 9;
        }


        printUpdate(cells);

    }

    /**
     * this method print only first stepof board
     */
    public void printBoard() {
        int k = 0;
        String s = "ABCDEFGH";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i == 0) {
                    if (k == 0)
                        System.out.print("  ");
                    k++;
                    System.out.print(s.charAt(j) + " ");
                }


            }
            if (i == 4) {
                System.out.print(i);
                System.out.print("| | | |w|b| | | |");
            } else if (i == 5) {
                System.out.print(i);
                System.out.print("| | | |b|w| | | |");
            } else if (i != 0) {
                System.out.print(i);
                System.out.print("| | | | | | | | |");
            }
            System.out.println();

        }
        for (int i = 0; i < 64; i++) {
            if (cells[i].getColor().equals("b")) {
                blackCount += 1;
            } else if (cells[i].getColor().equals("w")) {
                whiteCount += 1;
            }
        }
        System.out.print("black: " + blackCount + "  white :" + whiteCount);
        System.out.println();


    }

    /**
     * this method print board
     * @param cellss is cells of the game
     */
    public void printUpdate(Cell[] cellss) {
        int k = 0;
        String s = "ABCDEFGH";
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i == 0) {
                    if (k == 0)
                        System.out.print("  ");
                    k++;
                    System.out.print(s.charAt(j) + " ");
                }
            }

            if (i != 0) {
                System.out.print(i);
                System.out.print("|" + cellss[(i - 1) * 8].getColor() + "|" + cellss[(i - 1) * 8 + 1].getColor() + "|" + cellss[(i - 1) * 8 + 2].getColor() +
                        "|" + cellss[(i - 1) * 8 + 3].getColor() + "|" + cellss[(i - 1) * 8 + 4].getColor() + "|" + cellss[(i - 1) * 8 + 5].getColor() + "|"
                        + cellss[(i - 1) * 8 + 6].getColor() + "|" + cellss[(i - 1) * 8 + 7].getColor() + "|");
            }


            System.out.println();


        }
        blackCount = whiteCount = 0;
        for (int i = 0; i < 64; i++) {
            if (cells[i].getColor().equals("b")) {
                blackCount += 1;
            } else if (cells[i].getColor().equals("w")) {
                whiteCount += 1;
            }
        }
        System.out.print("black: " + blackCount + "  white :" + whiteCount);
        System.out.println();
    }


}