package cpsc2150.extendedConnectX.models;
/**
 * IGameBoard represents a board of characters
 * 
 * Initialization ensures:
 *      A gameboard is created 
 *      
 * 
 * Defines: None
 * 
 * Constraints: There can be no indices between player tokens that contain a ' '
 *          
 *  
 */


public interface IGameBoard {
    public static final int MAX_NUM_ROWS = 100;
    public static final int MAX_NUM_COLUMNS = 100;
    public static final int MAX_IN_A_ROW = 25;
    public static final int MIN_NUM_ROWS = 3;
    public static final int MIN_NUM_COLUMNS = 3;
    public static final int MIN_IN_A_ROW = 3;
    public static final int MIN_NUM_PLAYERS = 2;
    public static final int MAX_NUM_PLAYERS = 10;

    /**
     * Function that will check the row values at a column and return true if a row is empty and false if not.
     *
     * @param c number that represents a column
     *
     * @return true if topmost row in column c contains a blank space, else false
     *
     * @pre c >= 0 AND c < getNumColumns()
     *
     * @post [highest row in column c does not contain a character token AND highest row in column c must have ''
     *        return true, else return false] AND self = #self
     *
     */
    default public boolean checkIfFree(int c) {

        // create instance of board position at the highest row and at column c
        BoardPosition pos = new BoardPosition(getNumRows() - 1, c);
        //returns true if the column can accept another token; false otherwise.
        return whatsAtPos(pos) == ' ';
    }

    public void dropToken(char p, int c);

    /**
     * Function that will return a truth value representing whether or not the player token has resulted in the player winning the game.
     * @param c number that represents column
     *
     * @return [true if any of the checkDirectionWin() functions evaluate to true with the
     *          boardPosition of the last token placed]
     *
     * @pre [c is the column that a token was last placed in]
     * @pre c >= 0 AND c < getNumColumns()
     *
     * @post [Return true when the last token placed is the last same consecutive marker that
     *        makes the checkHorizWin, checkVertWin, checkDiagWin true, else return false] AND self = #self
     *
     */
    default public boolean checkForWin(int c) {

        // token placeholder
        char p = ' ';

        // loop down the row positions until a token is reached
        for (int r = (getNumRows() - 1); r >= 0; r--)
        {
            // create the board position
            BoardPosition pos = new BoardPosition(r, c);
            // if a token is present in the board position
            if (whatsAtPos(pos) != ' ')
            {
                // make the token placeholder equal to the token at the board position
                p = whatsAtPos(pos);
                // check if it passes any win conditions, if so return true
                if (checkHorizWin(pos, p))
                {
                    return true;
                }
                if (checkDiagWin(pos, p))
                {
                    return true;
                }
                if(checkVertWin(pos, p))
                {
                    return true;
                }

                // exit the loop
                r = -1;
            }
        }

        return false;

    }

    /**
     * This function will check if a play has resulted in a tie. It will do this by checking for any empty positions.
     * If there are no empty positions the game will return true and false if there are empty positions.
     *
     * @return If the board is completely full, return true, else return false
     *
     * @pre None
     *
     * @post [Loop through and check if there are any empty positions, if there are return true
     *        else return false] AND self = #self
     *
     */
    default public boolean checkTie() {
        // counter to iterate through columns
        int col = 0;

        // loop through each column position
        while (col < getNumColumns())
        {
            // create BoardPosition object with the highest row count and column position
            BoardPosition tie = new BoardPosition((getNumRows() - 1), col);

            // if that row and column position is empty, return false
            if (whatsAtPos(tie) == ' ')
            {
                return false;
            }
            col++;
        }

        return true;
    }

    /**
     * Function will check if the last token played has resulted in a number equal to MAX_IN_A_ROW of the same player's tokens being adjacent on the same row.
     * If so the function will return true indicating that the player that laid the token has won, and false if not.
     *
     * @param pos object indicating spot where last token was placed
     * @param p character indicating the last players token
     *
     * @return true if getNumToWin() of the same player tokens are lined up horizontally without any blank spaces
     *         or other tokens in between, else return false
     *
     * @pre p != ' '
     *
     * @post [Loop through row and check if getNumToWin() char values equal to p are placed in a row adjacent to each other,
     *        if they are, return true, else return false] && self = #self
     */
    default public boolean checkHorizWin(BoardPosition pos, char p) {

        // booleans to check if column can go further left or right without
        // leaving the dimensions of the board
        boolean left = true, right = true;

        int inARowCount = 1, currentRow = pos.getRow(),
                currentCol = pos.getColumn();

        // while the currentCol position isn't outside the board and the
        // number to win hasn't been reached, continue looping towards the left
        // of the board.
        while (currentCol >= 0 && left && inARowCount < getNumToWin())
        {
            // if moving further left doesn't cause you to leave the dimensions of the board
            // execute the loop
            if ((currentCol - 1) >= 0)
            {
                // create new board position instance for the next space towards the left
                BoardPosition next = new BoardPosition(currentRow, (currentCol - 1));

                // check if that position is equal to the token placed
                // if it is, increase the in a row count by 1
                // if not, return false so the loop does not further execute
                // towards the left
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    left = false;
                }
            }
            else
            {
                left = false;
            }
            currentCol--;
        }

        // set the column back to the original place to begin checking
        // towards the right side of the board
        currentCol = pos.getColumn();

        // while the currentCol position isn't outside the board and the
        // number to win hasn't been reached, continue looping towards the right
        // of the board.
        while (currentCol < getNumColumns() && right && inARowCount < getNumToWin())
        {
            // if moving further right doesn't cause you to leave the dimensions of the board
            // execute the loop
            if ((currentCol + 1) < getNumColumns())
            {
                // create new board position instance for the next space towards the left
                BoardPosition next = new BoardPosition(currentRow, (currentCol + 1));

                // check if that position is equal to the token placed
                // if it is, increase the in a row count by 1
                // if not, return false so the loop does not further execute
                // towards the right
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    right = false;
                }
            }
            else
            {
                right = false;
            }
            currentCol++;
        }

        return inARowCount == getNumToWin();
    }

    /**
     * Function will check if the last token played has resulted in a number equal to MAX_IN_A_ROW of the same player's tokens being adjacent on the same column.
     * If so the function will return true indicating that the player that laid the token has won, and false if not.
     *
     * @param pos object indicating spot where last token was placed
     * @param p character indicating the last players token
     *
     * @return true if getNumToWin() of the same player tokens are lined up vertically without any blank spaces
     *         or other tokens in between, else return false
     *
     * @pre (p != ' ')
     *
     * @post [Loop through column and check if getNumToWin() char values equal to p are placed in the column adjacent to each other,
     *        if they are, return true and if not return false] AND self = #self
     */
    default public boolean checkVertWin(BoardPosition pos, char p) {

        // booleans to check if the row can go further up
        // or down without leaving the dimensions of the
        // board
        boolean up = true, down = true;

        // in row count, row and column position
        int inARowCount = 1, currentRow = pos.getRow(), currentCol = pos.getColumn();

        // while the current row is less than the maximum number of rows,
        // the board position can move up without leaving the board dimensions,
        // and the in a row counter is less than the number to win then execute
        // the while loop
        while (currentRow < getNumRows() && up && inARowCount < getNumToWin())
        {
            // if the next row up is less than the maximum number of rows
            // then execute the statement
            if ((currentRow + 1) < getNumRows())
            {
                // initialize a board position with the next row up
                BoardPosition next = new BoardPosition((currentRow + 1), currentCol);

                // check if that next position is the same token
                // if it is, increase the in a row counter
                // if not, return false
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    up = false;
                }
            }
            else
            {
                up = false;
            }
            // move to the next row up to check the next location
            currentRow++;
        }

        // reset the row position before checking the tokens
        // below
        currentRow = pos.getRow();

        // while the row position is within the board dimensions,
        // the next position down is the corresponding token,
        // and the in a row count hasn't been reached
        while (currentRow >= 0 && down && inARowCount < getNumToWin())
        {
            // if the next row down doesn't leave the
            // board dimensions
            if ((currentRow - 1) >= 0)
            {
                // initialize a board position for the next position below
                BoardPosition next = new BoardPosition((currentRow - 1), currentCol);

                // if that next position is that same token, increase the
                // in a row counter by one
                // if not, return false
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    down = false;
                }
            }
            else
            {
                down = false;
            }
            // check the next position down
            currentRow--;
        }

        // return if the row count has reached the win goal
        return inARowCount == getNumToWin();

    }

    /**
     * Function will check if the last token played has resulted in a number equal to getNumToWin() of the same player's tokens being adjacent on a diagonal strip.
     * If so the function will return true indicating that the player that laid the token has won, and false if not.
     *
     * @param pos object indicating spot where last token was placed
     * @param p character indicating the last players token
     *
     * @return true if player has matched getNumToWin() token diagonally on all directions OR false if the player has not had a match
     *
     * @pre p != ' '
     *
     * @post [Loop through and check and if the player has getNumToWin() matching symbols diagonally, return true, if there is no diagonal match, return false]
     * @      AND self = #self
     */
    default public boolean checkDiagWin(BoardPosition pos, char p) {

        // booleans to check if these different directions are valid to continue
        boolean leftUp = true, leftDown = true, rightUp = true, rightDown = true;

        // row counter, row position, column position
        int inARowCount = 1, currentRow = pos.getRow(), currentCol = pos.getColumn();

        // check left up (row+1, col-1)
        // check row and column are within the board size, verify we can continue in the direction
        // and verify that we haven't reached our win goalOW
        while (currentRow < getNumRows() && currentCol >= 0 && leftUp && inARowCount < getNumToWin()) {
            // if the next left up position is in the board size
            if ((currentRow + 1) < getNumRows() && (currentCol - 1) >= 0)
            {
                // initialize new board position at the next left up position
                BoardPosition next = new BoardPosition((currentRow + 1), (currentCol - 1));
                // check that it matches the token
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    leftUp = false;
                }
            }
            else
            {
                leftUp = false;
            }
            // move to next left up position
            currentRow++;
            currentCol--;
        }
        // reset the row and column
        currentRow = pos.getRow();
        currentCol = pos.getColumn();

        // check right down (row-1, col+1)
        // check row and column are within the board size, check that we can continue to the down right position,
        // check that we haven't reached our win goal yet
        while(currentRow >= 0 && currentCol < getNumColumns() && rightDown && inARowCount < getNumToWin())
        {
            // if the next right down position is within the board size
            if ((currentRow - 1) >= 0 && (currentCol + 1) < getNumColumns())
            {
                // initialize new board position in the next right down position
                BoardPosition next = new BoardPosition((currentRow - 1), (currentCol + 1));
                // if the next position token matches
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    rightDown = false;
                }
            }
            else
            {
                rightDown = false;
            }
            // move to the next right down position
            currentRow--;
            currentCol++;
        }

        // if you have reached getNumToWin() in a row diagonally, return true
        // if not, reset row count and test the next diagonal path
        if (inARowCount == getNumToWin())
        {
            return true;
        }
        else
        {
            inARowCount = 1;
        }

        // reset row and column
        currentRow = pos.getRow();
        currentCol = pos.getColumn();

        // check right up (row + 1, col + 1)
        // check that the row and column are within the board size, you can continue to the next right up position,
        // and that the win goal hasn't been reached
        while (currentRow < getNumRows() && currentCol < getNumColumns() && rightUp && inARowCount < getNumToWin())
        {
            // if the next right up position is within the board size
            if ((currentRow + 1) < getNumRows() && (currentCol + 1) < getNumColumns())
            {
                // initialize the next right up board position
                BoardPosition next = new BoardPosition((currentRow + 1), (currentCol + 1));
                // if that next right up board position matches the token
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else
                {
                    rightUp = false;
                }
            }
            else
            {
                rightUp = false;
            }
            // move to the next right up position
            currentRow++;
            currentCol++;
        }
        // reset the row and column
        currentRow = pos.getRow();
        currentCol = pos.getColumn();

        // check left down (row - 1, col - 1)
        // check that the row and column are within the board size, check we can continue to the
        // next left down position, check that we haven't reached the win goal yet
        while (currentRow >= 0 && currentCol >= 0 && leftDown && inARowCount < getNumToWin())
        {
            // if the next position is within the board size
            if ((currentRow - 1) >= 0 && (currentCol - 1) >= 0)
            {
                // initialize new board position with the next left down position
                BoardPosition next = new BoardPosition((currentRow - 1), (currentCol - 1));
                // if the next position matches the token
                if (whatsAtPos(next) == p)
                {
                    inARowCount++;
                }
                else {
                    leftDown = false;
                }
            }
            else
            {
                leftDown = false;
            }
            // move to the next left down position
            currentRow--;
            currentCol--;
        }

        // if the row count has reached the win goal, return true
        // if not, return false
        if (inARowCount == getNumToWin())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** 
     * Function that will return the value of the specified pos
     * 
     * @param pos the position that we are checking for if there is marker
     * 
     * @return character or a blank space at pos
     * 
     * @pre None
     *
     * @post [returns char at pos] AND self = #self
     * 
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Function that will check if a specific token is at position and return true if it is and false if it is not.
     *
     *
     * @param pos object indicating spot on board
     * @param player character indicating the players token
     *
     * @return Returns 1 if the player is at pos, returns false if the player is not at pos
     *
     * @pre [player is a character]
     *
     *
     * @post [Returns the char at the pos it is located on] AND self = #self
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {

        return whatsAtPos(pos) == player;

    }

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
}