package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Derek Smith - DerekGSmith
    Jack Huber - jhuber112
    Justin Kang - Justin1065

 */

/**
 * This class, written for CPSC2150, will keep track of the individual cells for the board.
 * Each board position is identified by their column and row.
 *
 * @authors Derek Smith, Jack Huber, Justin Kang
 * @version 1.0
 *
 * @invariant row >= 0 && row < maxRow AND column >= 0 && column < maxCol
 *
 */

public class BoardPosition
{
    private int row, column;
    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;


    /**
     *  constructor for the board position object. Sets the instance variables to the values passed in
     *  through the parameters.
     *
     * @pre aRow >= 0 && aRow < MAX_ROW AND aColumn >= 0 && aColumn < MAX_COL
     *
     * @post row = aRow AND column = aColumn
     *
     * @param aRow the value to be set as the row
     * @param aColumn the value to be set as the column
     */

    public BoardPosition(int aRow, int aColumn)
    {
        row = aRow;
        column = aColumn;
    }

    /**
     * Returns the value of the board position stored in the row field
     *
     * @pre None
     *
     * @post getRow = row AND row = #row AND column = #column
     *
     * @return row for this instance of a board position
     */

    public int getRow()
    {
        return row;
        //returns the row
    }

    /**
     * Returns the value of board position stored in the column field
     *
     * @pre None
     *
     * @post getColumn = column AND row = #row AND column = #column
     *
     * @return column for this instance of a board position
     */

    public int getColumn()
    {
        return column;
        //returns the column
    }

    /**
     * Returns a boolean value determined if this board position's
     * row and column variables are equal to the parameters row and column position
     *
     * @pre None
     *
     * @post [check if the row and column of the object passed in as a parameter
     * match with the row and column of this class instance. If the row and column
     * of the object passed in as a parameter match, then return true. If not, return
     * false.]
     * row = #row AND column = #column
     *
     * @param obj the board position object to compare row and column values
     *
     * @return boolean value based on if the row and column values are equal to the
     * parameters row and column value
     */

    @Override
    public boolean equals(Object obj)
    {

        if (obj instanceof BoardPosition)
        {
            if (this.row == ((BoardPosition)obj).row && (this.column == ((BoardPosition)obj).column))
            {
                return true;
            }
        }
        return false;

        // returns boolean based on whether or not the row and column is
        // equal to the object parameters row and column value

    }

    /**
     * Returns a string in the format of "<row>,<column>."
     *
     * @pre None
     *
     * @post [convert row AND column to string values, put the string in "<row>,<column>." format
     *       row = #row AND column = #column]
     *
     * @return String with this board positions row and Column displayed in "<row>,<column>."
     */

    @Override
    public String toString()
    {
        String row = String.valueOf(getRow());
        String column = String.valueOf(getColumn());
        return row + "," + column;

        // returns a string in the format of "<row>,<column>"
    }
}