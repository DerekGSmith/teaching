package cpsc2150.extendedConnectX.models;

import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestGameBoardMem
{
    // Private helper factory function to make calls to GameBoard easier
    private IGameBoard GameBoardFactory(int r, int c, int numWin) { return new GameBoardMem(r, c, numWin); }

    // Private helper factory function to create a string version of the 2D array

    private String toTestString(char[][] boardSize) {

        int row = boardSize.length;
        int col = boardSize[0].length;
        // initialize string builder to create the board
        StringBuilder board = new StringBuilder("|");
        // column counter to display each column number above the board

        int colCounter = 0;
        char line = '|';
        // loop through each column position in the board
        // and place it's column number above
        // (this creates the header)
        for (int c = 0; c < col; c++)
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
        for(int r = (row - 1); r >= 0; r--)
        {
            for (int c = 0; c < col; c++)
            {
                char val = boardSize[r][c];
                board.append(val);
                board.append(" ");
                board.append(line);
            }
            board.append("\n");
            if (r != 0) {
                board.append(line);
            }
        }
        return board.toString();
    }

    // This Constructor tests that the board is made in the smallest size possible
    @Test
    public void testConstructor_r3_c3_win3_Smallest_Size()
    {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[3][3];
        for (int r = 0; r < boardSize.length; r++)
        {
            for (int c = 0; c < boardSize[0].length; c++)
            {
                boardSize[r][c] = ' ';
            }
        }
        expectedBoard = toTestString(boardSize);

        IGameBoard gb = GameBoardFactory(3,3,3);
        actualBoard = gb.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This Constructor tests that the board is made in the Largest size possible
    @Test
    public void testConstructor_r100_c100_win25_Largest_Size()
    {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[100][100];
        for (int r = 0; r < boardSize.length; r++)
        {
            for (int c = 0; c < boardSize[0].length; c++)
            {
                boardSize[r][c] = ' ';
            }
        }
        expectedBoard = toTestString(boardSize);

        IGameBoard gb = GameBoardFactory(100,100,25);
        actualBoard = gb.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This Constructor tests that the board is made with more rows than columns
    @Test
    public void testConstructor_r10_c5_win5()
    {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[10][5];
        for (int r = 0; r < boardSize.length; r++)
        {
            for (int c = 0; c < boardSize[0].length; c++)
            {
                boardSize[r][c] = ' ';
            }
        }
        expectedBoard = toTestString(boardSize);

        IGameBoard gb = GameBoardFactory(10,5,5);
        actualBoard = gb.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This checkIfFree test checks to makes sure that it returns true even though the column has all but one space left
    @Test
    public void testCheckIfFree_r5_c5_win5_Check_Middle_Column_One_Space_Left()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        actualResult = gb.checkIfFree(2);

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkIfFree test checks if the largest board size with the last column empty returns true
    @Test
    public void testCheckIfFree_r100_c100_win25_Largest_Size_Full_Board()
    {
        boolean expectedResult = false;
        boolean actualResult;

        IGameBoard gb = GameBoardFactory(100,100,25);

        for(int j = 0; j < 100; j++)
        {
            for(int i = 0; i < 100; i++)
            {
                gb.dropToken('X', j);
            }
        }

        actualResult = gb.checkIfFree(99);

        assertFalse(actualResult);
    }

    // This checkIfFree test case checks if the correct output is returned with an empty board
    @Test
    public void testCheckIfFree_r5_c6_win4_Empty_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,6,4);

        actualResult = gb.checkIfFree(1);

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This horizontalTestCase checks to see if the checkHorizWin returns true if the win condition is met on the very bottom row
    @Test
    public void testHorizontalWin_r10_c10_win5_String_Of_Character_At_Bottom()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,5);

        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);

        BoardPosition actualPos = new BoardPosition(0,4);
        actualResult = gb.checkHorizWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }
        assertTrue(answer);
    }

    // This checkHorizontalWin test case tests the character in the middle of the string of characters making sure that checkHorizontalWin also checks left and right
    @Test
    public void testHorizontalWin_r5_c5_win5_Last_Character_Is_Middle_Of_String()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);

        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);
        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);
        gb.dropToken('X', 4);
        gb.dropToken('X', 4);

        gb.dropToken('X', 2);

        BoardPosition actualPos = new BoardPosition(2,2);
        actualResult = gb.checkHorizWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkHorizontalWin test case tests if checkHorizontalWin works with the top most row of the board
    @Test
    public void testHorizontalWin_r4_c4_win3_Character_String_At_Top_Most_Row()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(4,4,3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);
        gb.dropToken('X', 3);

        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);

        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);

        BoardPosition actualPos = new BoardPosition(3,3);
        actualResult = gb.checkHorizWin(actualPos, 'O');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkHorizWin test case tests if the last character is last of the string of character making sure that checkHorizontalWin also checks backwards as well
    @Test
    public void testHorizontalWin_r10_c10_win5_Last_Character_Is_In_End_Of_String()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,5);

        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 4);

        gb.dropToken('X', 5);

        BoardPosition actualPos = new BoardPosition(1,5);
        actualResult = gb.checkHorizWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testVerticalWin test case makes sure the checkVertWin returns true when the win condition is in the bottom left of the board
    @Test
    public void testVerticalWin_r10_c10_win5_Bottom_Left_Corner()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,5);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('O', 2);
        gb.dropToken('X', 0);
        gb.dropToken('O', 3);

        gb.dropToken('X', 0);

        BoardPosition actualPos = new BoardPosition(4,0);
        actualResult = gb.checkVertWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testVerticalWin test case makes sure the checkVertWin returns true when the win condition is in the top left of the board
    @Test
    public void testVerticalWin_r10_c10_win5_Top_Left()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,5);
        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);

        BoardPosition actualPos = new BoardPosition(10,0);
        actualResult = gb.checkVertWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testVerticalWin test case makes sure the checkVertWin returns true when the win condition is in the top right of the board
    @Test
    public void testVerticalWin_r10_c10_win5_Top_Right()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,5);
        gb.dropToken('X', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);
        gb.dropToken('X', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);
        gb.dropToken('I', 9);

        BoardPosition actualPos = new BoardPosition(9,9);
        actualResult = gb.checkVertWin(actualPos, 'I');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testVerticalWin test case makes sure the checkVertWin returns true when the win condition is in the bottom right of the board
    @Test
    public void testVerticalWin_r5_c5_win3_Bottom_Right()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(6,6,3);
        gb.dropToken('X', 5);
        gb.dropToken('O', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 5);
        gb.dropToken('X', 5);


        BoardPosition actualPos = new BoardPosition(2,5);
        actualResult = gb.checkVertWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever there is a right diagonal
    @Test
    public void checkDiagWin_r5_c5_win5_Right_Diagonal()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);

        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);
        gb.dropToken('O', 4);

        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);

        gb.dropToken('X', 2);
        gb.dropToken('O', 3);
        gb.dropToken('X', 3);
        gb.dropToken('O', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);

        BoardPosition actualPos = new BoardPosition(4,4);
        actualResult = gb.checkDiagWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever there is a left diagonal
    @Test
    public void checkDiagWin_r5_c5_win5_Left_Diagonal()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);

        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);

        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);

        gb.dropToken('X', 2);
        gb.dropToken('O', 0);
        gb.dropToken('X', 3);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 4);

        gb.dropToken('X', 0);
        gb.dropToken('X', 0);

        BoardPosition actualPos = new BoardPosition(4,0);
        actualResult = gb.checkDiagWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever there is a left diagonal and the last character is placed in the middle of the string of characters
    @Test
    public void checkDiagWin_r5_c5_win5_Left_Diagonal_Last_Character_Middle_Of_String()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);

        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 0);
        gb.dropToken('X', 3);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 4);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);

        gb.dropToken('X', 2);

        BoardPosition actualPos = new BoardPosition(2,2);
        actualResult = gb.checkDiagWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever the right diagonal is in the bottom left of the board
    @Test
    public void checkDiagWin_r7_c7_win3_Right_Diagonal_Bottom_Left_Of_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(7,7,3);

        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 1);

        gb.dropToken('O', 0);

        BoardPosition actualPos = new BoardPosition(0,0);
        actualResult = gb.checkDiagWin(actualPos, 'O');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever the right diagonal is in the top left of the board
    @Test
    public void checkDiagWin_r7_c7_win3_Right_Diagonal_Top_Left_Of_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(7,7,3);

        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 2);


        gb.dropToken('O', 0);

        BoardPosition actualPos = new BoardPosition(6,2);
        actualResult = gb.checkDiagWin(actualPos, 'O');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever the right diagonal is in the bottom right of the board
    @Test
    public void checkDiagWin_r7_c7_win3_Right_Diagonal_Bottom_Right_Of_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(7,7,3);

        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('O', 6);
        gb.dropToken('X', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);


        BoardPosition actualPos = new BoardPosition(2,6);
        actualResult = gb.checkDiagWin(actualPos, 'X');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This testDiagWin test case tests to make sure that checkDiagWin returns true whenever the right diagonal is in the top right of the board
    @Test
    public void checkDiagWin_r7_c7_win3_Right_Diagonal_Top_Right_Of_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(7,7,3);

        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 6);
        gb.dropToken('O', 5);
        gb.dropToken('X', 6);
        gb.dropToken('O', 6);
        gb.dropToken('X', 4);
        gb.dropToken('X', 5);
        gb.dropToken('O', 6);
        gb.dropToken('X', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('O', 4);
        gb.dropToken('O', 4);
        gb.dropToken('O', 5);
        gb.dropToken('O', 6);
        gb.dropToken('O', 5);
        gb.dropToken('O', 4);


        BoardPosition actualPos = new BoardPosition(4,4);
        actualResult = gb.checkDiagWin(actualPos, 'O');

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkTie test case makes sure that checkTie returns true whenever the board is completely full
    @Test
    public void checkTie_r3_c5_win3_Full_Board()
    {
        boolean expectedResult = true;
        boolean actualResult;
        boolean answer;

        IGameBoard gb = GameBoardFactory(3,5,3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);

        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);

        gb.dropToken('X', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 3);

        gb.dropToken('X', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);

        actualResult = gb.checkTie();

        if(expectedResult == actualResult)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkTie test case makes sure that checkTie returns false whenever the board is has all but one space left
    @Test
    public void checkTie_r3_c5_win3_One_Space_Open() {

        boolean actualResult;

        IGameBoard gb = GameBoardFactory(3, 5, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);

        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);

        gb.dropToken('X', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 3);

        gb.dropToken('X', 4);
        gb.dropToken('O', 4);

        actualResult = gb.checkTie();

        assertFalse(actualResult);
    }

    // This checkTie test case makes sure that checkTie returns false whenever the board is has all but one column empty
    @Test
    public void checkTie_r5_c5_win5_Left_Most_Column_Empty()
    {
        boolean actualResult;

        IGameBoard gb = GameBoardFactory(5,5,3);

        for(int i = 0; i < 4; i++)
        {
            for(int c = 0; c < 5; c++)
            {
                gb.dropToken('X', i);
                gb.dropToken('O', i);
            }
        }

        actualResult = gb.checkTie();

        assertFalse(actualResult);
    }

    // This checkTie test case makes sure that checkTie returns false whenever the board is has all but one row empty
    @Test
    public void checkTie_r4_c4_win3_Top_Most_Row_Empty()
    {
        boolean actualResult;

        IGameBoard gb = GameBoardFactory(4,4,3);

        gb.dropToken('X', 0);
        gb.dropToken('I', 1);
        gb.dropToken('I', 2);
        gb.dropToken('X', 3);
        gb.dropToken('X', 0);
        gb.dropToken('I', 1);
        gb.dropToken('I', 2);
        gb.dropToken('X', 3);
        gb.dropToken('X', 0);
        gb.dropToken('I', 1);
        gb.dropToken('I', 2);
        gb.dropToken('X', 3);

        actualResult = gb.checkTie();

        assertFalse(actualResult);
    }

    // This checkWhatsAtPos test case returns the character at the bottom left of the board
    @Test
    public void checkWhatsAtPos_r5_c5_numWin_5_What_Character_At_Bottom_Left()
    {
        char expectedChar = 'X';
        char actualChar;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);
        BoardPosition actualPos = new BoardPosition(0,0);

        gb.dropToken('X', 0);

        actualChar = gb.whatsAtPos(actualPos);

        if(expectedChar == actualChar)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkWhatsAtPos test case returns the correct character even if it is a blank space
    @Test
    public void checkWhatsAtPos_r5_c5_numWin_5_Character_Is_Space()
    {
        char expectedChar = ' ';
        char actualChar;
        boolean answer;

        IGameBoard gb = GameBoardFactory(5,5,5);
        BoardPosition actualPos = new BoardPosition(2,3);
        gb.dropToken('X', 3);
        gb.dropToken('O', 3);

        actualChar = gb.whatsAtPos(actualPos);

        if(expectedChar == actualChar)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkWhatsAtPos test case returns the character at the bottom right of the board
    @Test
    public void checkWhatsAtPos_r10_c10_numWin_5_What_Character_At_Bottom_Right()
    {
        char expectedChar = 'X';
        char actualChar;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(0,9);

        gb.dropToken('X', 9);

        actualChar = gb.whatsAtPos(actualPos);

        if(expectedChar == actualChar)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkWhatsAtPos test case returns the character at the top right of the board
    @Test
    public void checkWhatsAtPos_r5_c5_numWin_5_What_Character_At_Top_Right()
    {
        char expectedChar = '9';
        char actualChar;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(9,9);

        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('9', 9);

        actualChar = gb.whatsAtPos(actualPos);

        if(expectedChar == actualChar)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This checkWhatsAtPos test case returns the character at the top left of the board
    @Test
    public void checkWhatsAtPos_r5_c5_numWin_5_What_Character_At_Top_Left()
    {
        char expectedChar = 'S';
        char actualChar;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(9,0);

        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('S', 0);

        actualChar = gb.whatsAtPos(actualPos);

        if(expectedChar == actualChar)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This isPlayerAtPos test case checks if the correct output is given when the character is at the bottom left position of the board
    @Test
    public void checkIsPlayerAtPos_r10_c10_numWin_7_Player_Character_At_Bottom_Left()
    {
        boolean expected = true;
        boolean actual;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(0,0);

        gb.dropToken('X', 0);

        actual = gb.isPlayerAtPos(actualPos, 'X');

        if(expected == actual)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This isPlayerAtPos test case checks if the correct output is given when the character is at the bottom right position of the board
    @Test
    public void checkIsPlayerAtPos_r10_c10_numWin_7_Character_At_Bottom_Right()
    {
        boolean expected = true;
        boolean actual;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(0,9);

        gb.dropToken('X', 9);

        actual = gb.isPlayerAtPos(actualPos, 'X');

        if(expected == actual)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This isPlayerAtPos test case checks if the correct output is given when the character is at the top left position of the board
    @Test
    public void checkIsPlayerAtPos_r10_c10_numWin_7_Character_At_Top_Left()
    {
        boolean expected = true;
        boolean actual;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10,10,7);
        BoardPosition actualPos = new BoardPosition(9,0);

        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);

        gb.dropToken('I', 0);

        actual = gb.isPlayerAtPos(actualPos, 'I');

        if(expected == actual)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }

        assertTrue(answer);
    }

    // This isPlayerAtPos test case checks if the correct output is given when the character is at the top right position of the board
    @Test
    public void checkIsPlayerAtPos_r10_c10_numWin_7_Character_At_Top_Right() {
        boolean expected = true;
        boolean actual;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10, 10, 7);
        BoardPosition actualPos = new BoardPosition(9, 9);

        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);
        gb.dropToken('X', 9);

        gb.dropToken('I', 9);

        actual = gb.isPlayerAtPos(actualPos, 'I');

        if (expected == actual) {
            answer = true;
        } else {
            answer = false;
        }

        assertTrue(answer);
    }

    // This isPlayerAtPos test case checks if the correct output is given even when the character is at the top row
    @Test
    public void checkIsPlayerAtPos_r10_c10_numWin_7_Character_Is_At_Top_Row() {
        boolean expected = true;
        boolean actual;
        boolean answer;

        IGameBoard gb = GameBoardFactory(10, 10, 7);
        BoardPosition actualPos = new BoardPosition(9, 3);

        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 3);
        gb.dropToken('I', 3);

        actual = gb.isPlayerAtPos(actualPos, 'I');

        if (expected == actual) {
            answer = true;
        } else {
            answer = false;
        }

        assertTrue(answer);
    }


    // This dropToken test case tests to see if the token correctly dropped in an empty column
    @Test
    public void dropToken_r5_c5_numWin_5_Drop_Token_Empty_Column()
    {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[5][5];
        for (int r = 0; r < boardSize.length; r++)
        {
            for (int c = 0; c < boardSize[0].length; c++)
            {
                if(r == 0 && c == 3)
                {
                    boardSize[0][3] = 'X';
                }
                else
                {
                    boardSize[r][c] = ' ';
                }
            }
        }

        expectedBoard = toTestString(boardSize);

        IGameBoard actual = GameBoardFactory(5,5,5);
        actual.dropToken('X', 3);

        actualBoard = actual.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This test case makes sure that dropTokens works for all row and columns of the board
    @Test
    public void dropToken_r7_c7_numWin_7_All_Positions() {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[7][7];
        for (int r = 0; r < boardSize.length; r++) {
            for (int c = 0; c < boardSize[0].length; c++) {
                boardSize[r][c] = 'X';
            }
        }

        expectedBoard = toTestString(boardSize);

        IGameBoard actual = GameBoardFactory(7, 7, 7);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                actual.dropToken('X', i);
            }
        }

        actualBoard = actual.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This dropToken test case tests to see if the token correctly dropped in a column that already has a token
    @Test
    public void dropToken_r5_c5_numWin_5_Column_With_Token_In_It()
    {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[5][5];
        for (int r = 0; r < boardSize.length; r++)
        {
            for (int c = 0; c < boardSize[0].length; c++)
            {
                if(r == 0 && c == 3)
                {
                    boardSize[0][3] = 'X';
                }
                else if(r == 1 && c == 3)
                {
                    boardSize[1][3] = 'O';
                }
                else
                {
                    boardSize[r][c] = ' ';
                }
            }
        }

        expectedBoard = toTestString(boardSize);

        IGameBoard actual = GameBoardFactory(5,5,5);
        actual.dropToken('X', 3);
        actual.dropToken('O', 3);

        actualBoard = actual.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This dropToken test case tests to see if the token correctly dropped in a column that has all but one space left
    @Test
    public void dropToken_r5_c5_numWin_5_Almost_Full_Column() {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[5][5];
        for (int r = 0; r < boardSize.length; r++) {
            for (int c = 0; c < boardSize[0].length; c++) {
                if (r == 0 && c == 3) {
                    boardSize[0][3] = 'X';
                } else if (r == 1 && c == 3) {
                    boardSize[1][3] = 'O';
                } else if (r == 2 && c == 3) {
                    boardSize[2][3] = 'X';
                } else if (r == 3 && c == 3) {
                    boardSize[3][3] = 'O';
                } else if (r == 4 && c == 3) {
                    boardSize[4][3] = 'O';
                } else {
                    boardSize[r][c] = ' ';
                }
            }
        }

        expectedBoard = toTestString(boardSize);

        IGameBoard actual = GameBoardFactory(5, 5, 5);
        actual.dropToken('X', 3);
        actual.dropToken('O', 3);
        actual.dropToken('X', 3);
        actual.dropToken('O', 3);
        actual.dropToken('O', 3);

        actualBoard = actual.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    // This dropToken test case tests to see if the token correctly dropped in the last column of the board
    @Test
    public void dropToken_r5_c5_numWin_5_Token_Dropped_Last_Column() {
        String expectedBoard;
        String actualBoard;

        char[][] boardSize = new char[5][5];
        for (int r = 0; r < boardSize.length; r++) {
            for (int c = 0; c < boardSize[0].length; c++) {
                if (r == 0 && c == 4) {
                    boardSize[0][4] = 'X';
                } else if (r == 1 && c == 4) {
                    boardSize[1][4] = 'O';
                } else if (r == 2 && c == 4) {
                    boardSize[2][4] = 'X';
                } else if (r == 3 && c == 4) {
                    boardSize[3][4] = 'O';
                } else if (r == 4 && c == 4) {
                    boardSize[4][4] = 'O';
                } else {
                    boardSize[r][c] = ' ';
                }
            }
        }

        expectedBoard = toTestString(boardSize);

        IGameBoard actual = GameBoardFactory(5, 5, 5);
        actual.dropToken('X', 4);
        actual.dropToken('O', 4);
        actual.dropToken('X', 4);
        actual.dropToken('O', 4);
        actual.dropToken('O', 4);

        actualBoard = actual.toString();

        assertEquals(expectedBoard, actualBoard);
    }
}
