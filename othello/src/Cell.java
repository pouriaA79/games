/**
 * this class represents a cell in the game
 *
 * @author pouria ahmadi 9723002
 */

public class Cell {
    //color of the cell
    private String color;
    //if cell is empty
    private boolean isEmpty = true;

    /**
     * this method check if cell is empty
     *
     * @return if cell is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * this method return color of cell
     *
     * @return color of the cell
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color is color of cell
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param empty is if cell is empty
     */

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
