package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Derek Smith - DerekGSmith
    Jack Huber - jhuber112
    Justin Kang - Justin1065

 */

/**
 * This class, written for CPSC2150, will contain the logical functions for the game. The class will detect when a player has won, 
 * whether or not a token placement is allowed, and check to see if the board has been filled equalling a tie.
 *
 * @authors Derek Smith, Jack Huber, Justin Kang
 * @version 1.0
 *
 * 
 */

 /**
 * @invariant IGameBoard.MIN_NUM_COLUMNS <= columns <= IGameBoard.MAX_NUM_COLUMNS
 * @invariant IGameBoard.MIN_NUM_ROWS <= rows <= IGameBoard.MAX_NUM_ROWS
 * @invariant IGameBoard.MIN_IN_A_ROW <= inARow <= IGameBoard.MAX_IN_A_ROW
 * @invariant inARow < rows AND inARow < columns
 * 
 * Correspondence self = boardSize[0...rows - 1][0...cols - 1]
 * 
 */

public class GameBoard extends AbsGameBoard implements IGameBoard
{

    private char[][] boardSize;
    private int rows;
    private int columns;
    private int inARow;
    /**
     * constructor for GameBoard class. Will set each value in all rows and columns to ' '
     * 
     * @param row number that represents maximum rows
     * @param column number that represents maximum columns
     * @param numWin number that represents number of characters in a row to win
     * 
     * @pre none
     *
     * @post [each board position in the boardSize will be equal to ' ', a blank char]
     * @post rows = row AND columns = column AND inARow = numWin
     *
     */

    public GameBoard(int row, int column, int numWin)
    {
        rows = row;
        columns = column;
        inARow = numWin;
        boardSize = new char[rows][columns];
        for (int i = rows - 1; i >= 0; i--){
            for (int j = 0; j < columns; j++){
                boardSize[i][j] = ' ';
            }
        }
    }

    public GameBoard(){}

    /** 
     * Function that will insert char p into the lowest row that is not already filled in column c.
     * 
     * @param p character that represents token
     * @param c number that represents column 
     * 
     * @pre checkIfFree(c) == true
     * @pre (p != ' ')
     * @pre c >= 0 AND c < columns
     * 
     * 
     * @post [the lowest empty row in column c should contain p]
     * @post self = #self
     * 
     * 
     */
    public void dropToken(char p, int c)
    {
            // loop through rows to check for empty column
            for (int r = 0; r < getNumRows(); r++)
            {
                // if the column is empty, place token
                if (boardSize[r][c] == ' ')
                {
                    boardSize[r][c] = p;
                    // exit loop
                    r = getNumRows();
                }
            }

        //places the character p in column c. The token will be placed in the lowest available row in column c.
    }

    /** 
     * Function that will return the value of the board at the row and column values of pos
     * 
     * @param pos the position that we are checking for if there is marker
     * 
     * @return character or a blank space at pos
     * 
     * @pre None
     *
     * @post [char can be the character OR char can be a blank space based on pos]
     * @post self = #self
     */
    public char whatsAtPos(BoardPosition pos)
    {
        return boardSize[pos.getRow()][pos.getColumn()];

        //returns what is in the GameBoard at position pos. If no marker is there, it returns a blank space char.
    }

    /**
     * This function will return the number of rows in the board
     *
     * @return the number of rows in the board
     *
     * @pre None
     *
     * @post getNumRows = rows AND self = #self
     */
    public int getNumRows()
    {
        return rows;
    }

    /**
     * This function will return the number of columns in the board
     *
     * @return the number of columns in the board
     *
     * @pre None
     *
     * @post getNumColumns = columns AND self = #self
     */
    public int getNumColumns()
    {
        return columns;
    }

    /**
     * This function will return the max number of tokens you need
     * in a row to win the game
     *
     * @return the max number of tokens in a row to win the game
     *
     * @pre None
     *
     * @post getNumToWin = inARow AND self = #self
     */
    public int getNumToWin()
    {
        return inARow;
    }
}