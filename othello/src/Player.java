/**
 * this class represents one of the player
 *
 * @author seyed pouria ahmadi 9723002
 */
public class Player {

    // is name of player
    private String name;
    //is color of player
    private String color;

    /**
     * thos method create a player
     * @param color is color of player
     * @param name is  name of player
     */
    public Player(String color, String name) {
        this.color = color;
        this.name = name;
    }

    /**
     *
     * @return color of player
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * this method wants to make a move for player
     * @param gameBoard is board  of the game
     * @param column is column of move
     * @param row is row of the move
     * @return situation of method
     */
    public int makeMove(GameBoard gameBoard, int column, int row) {
        int situation = gameBoard.validMove(color, row, column);


        if (situation == 1)
            gameBoard.putCell(column, row, color);
        if (situation == 0)
            System.out.println("invalid move");

        return situation;

    }

    /**
     *this method wants to make a move for player
     * @param gameBoard is board  of the game
     * @param column is column of move
     * @param row is row of the move
     * @return situation of method
     */
    public int checkMove(GameBoard gameBoard, int column, int row) {
        return gameBoard.validMove(color, row, column);

    }


}


