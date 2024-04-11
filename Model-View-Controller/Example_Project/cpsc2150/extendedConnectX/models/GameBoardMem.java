package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

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
 * Correspondence self = board
 * 
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private Map<Character,List<BoardPosition>> board;
    private int rows, columns, inARow;
     /**
      * Constructor for the GameBoardMem class. Will set all instance variables and
      * create an empty game board.
      *
      *
      * @param row number that represents the amount of rows in the board
      * @param column number that represents that amount of columns in the board
      * @param numWin number that represents that amount of tokens in a row needed to win
      *
      * @pre None
      *
      * @post board = [empty hash map with the token as the key and a list of
      *                board positions for that key as the value]
      * @post rows = row AND columns = column AND inARow = numWin
      *
      */
    
    public GameBoardMem(int row, int column, int numWin){
        board = new HashMap<Character, List<BoardPosition>>();
        rows = row;
        columns = column;
        inARow = numWin;
    }


     /**
      * Function that will insert char p into the lowest row that is not already filled in column c
      *
      * @param p character that represents the token
      * @param c character that represents the column
      *
      * @pre [column is not full]
      *
      * @post [self = #self with char p added to the lowest empty row in column c]
      */

    public void dropToken(char p, int c){
        // iterate through each row creating pos object for each value
        // iterate through map looking for pos
        // if not found insert into map
        BoardPosition pos;
        boolean exist = false;
        for (int r = 0; r < getNumRows(); r++){

            // set exist to false each iteration and
            // create a new board position
            exist = false;
            pos = new BoardPosition(r, c);

            // iterate through each position in the board hash map
            for (Entry<Character, List<BoardPosition>> i : board.entrySet()){
                // if the hash map contains the value, return true
                // and exit the for loop
                if(i.getValue().contains(pos)){
                    exist = true;
                    break;
                }
            }
            // if board position doesn't exist, add it to the
            // board
            if (!exist){
                // create key-value list if it doesn't exist
                if (board.get(p) == null){
                    List<BoardPosition> positionList = new ArrayList<BoardPosition>();
                    positionList.add(pos);
                    board.put(p, positionList);
                    break;

                }
                // if the key-list already exists,
                // add it to the correct key
                else{

                    List<BoardPosition> positionList = board.get(p);
                    positionList.add(pos);
                    board.put(p, positionList);
                    break;
                }
            }

        }


    }

     /**
      * Function that will return the value of the board at a board position
      *
      * @param pos the position that we are checking for if there is marker
      *
      * @return the character at that board position, or a blank if no token exists
      *
      * @pre None
      *
      * @post #self = self
      */
    public char whatsAtPos(BoardPosition pos){
        // for each key of the board checks for value
        // if value is found then return key value (*player Character*)

        for (Entry<Character, List<BoardPosition>> i : board.entrySet()){
            if(i.getValue().contains(pos)){
                return i.getKey();
            }
        }
        return ' ';

    }

     /**
      * Function that will return true if player is at a board position and false if not
      *
      *
      * @param pos object indicating spot on board
      * @param player character indicating the players token
      *
      * @return returns true if the player is at the board position, if not return false
      *
      * @pre (player != null) && (player != ' ')
      *
      * @post self = #self
      */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        if(board.get(player) == null){
            return false;
       }

        if (board.get(player).contains(pos)){
            return true;
       }

        return false;

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