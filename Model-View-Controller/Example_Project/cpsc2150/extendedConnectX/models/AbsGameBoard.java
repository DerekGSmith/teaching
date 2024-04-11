package cpsc2150.extendedConnectX.models;

/*
 * @invariant 0 < cols <= maxRow
 * @invariant 0 < rows <= maxColumn
 *
 */
public abstract class AbsGameBoard implements IGameBoard{

    /**
     *
     * @pre None
     *
     * @post [Function shows the object as a string] AND self = #self
     *
     * @return String of values of BoardSize
     */
    @Override
    public String toString() {

        // initialize string builder to create the board
        StringBuilder board = new StringBuilder("|");
        // column counter to display each column number above the board
        int colCounter = 0;
        char line = '|';

        // loop through each column position in the board
        // and place it's column number above
        // (this creates the header)
        for (int c = 0; c < getNumColumns(); c++)
        {

            // loop to add a space to single digit values before
            // displaying the value
            if (colCounter < 10)
            {
                board.append(" ");
            }

            board.append(String.valueOf(colCounter));

            board.append(line);
            colCounter++;
        }

        // start the next line of the string
        // for building the board
        board.append('\n');
        board.append(line);

        // loop through each row and column to place it's
        // marker and construct the columns
        for(int r = (getNumRows() - 1); r >= 0; r--)
        {
            for (int c = 0; c < getNumColumns(); c++)
            {
                BoardPosition currentPos = new BoardPosition(r, c);
                board.append(whatsAtPos(currentPos));

                board.append(" ");
                board.append(line);
            }
            board.append('\n');
            if (r != 0)
            {
                board.append(line);
            }


        }

        return board.toString();
    }
}