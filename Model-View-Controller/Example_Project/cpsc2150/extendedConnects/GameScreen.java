package cpsc2150.extendedConnects;

import java.util.Scanner;

import cpsc2150.extendedConnectX.models.AbsGameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Derek Smith - DerekGSmith
    Jack Huber - jhuber112
    Justin Kang - Justin1065

 */
public class GameScreen {
public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        IGameBoard board = new GameBoard();
        int columnInput, check = 0, round = 0;
        char end = 'Y', currentPlayer;

        char[] players = findPlayersInfo(sc);

        board = initNewGame(board, sc);


        // While loop for entire game
        // only exists when a player wins or game ties and a player inputs to stop
        do{
            // Initializations for the loop
            currentPlayer = players[(round % players.length)];
            round++;
            check = 0;
            // printing out board values
            System.out.println(board.toString());

            System.out.println("Player " + currentPlayer + ", What column do you want to place your marker in?");
            columnInput = sc.nextInt();

            // validating user columnInput for column
            while (check != 1){
                while (columnInput < 0){
                    System.out.println("Column cannot be less than 0 ");
                    System.out.println("Player " + currentPlayer + ", What column do you want to place your marker in?");
                    columnInput = sc.nextInt();
                }
                while (columnInput >= board.getNumColumns()){
                    System.out.println("Column cannot be greater than " + (board.getNumColumns()-1));
                    System.out.println("Player " + currentPlayer + ", What column do you want to place your marker in?");
                    columnInput = sc.nextInt();
                }
                if ((columnInput >= 0) && (columnInput < board.getNumColumns()) && (!board.checkIfFree(columnInput))){
                    System.out.println("Column is full");
                    System.out.println("Player " + currentPlayer + ", What column do you want to place your marker in?");
                    columnInput = sc.nextInt();

                }
                if ((columnInput >= 0) && (columnInput < board.getNumColumns()) && (board.checkIfFree(columnInput))){
                    check = 1;
                }
            }

            board.dropToken(currentPlayer, columnInput);

            // checking for game ending in case of win
            if (board.checkForWin(columnInput)){
                System.out.println("Player " + currentPlayer + " Won!");
                do{
                    // resetting game or ending game based on user input
                    System.out.println("Would you like to play again? Y/N");
                    end = sc.next().charAt(0);
                    if ((end == 'Y') || (end == 'y')){
                        board = initNewGame(board, sc);
                        round = 0;
                    }
                }
                while((end != 'N') && (end != 'n') && (end != 'Y') && (end != 'y'));
            }

            // checking for game ending in case of tie
            else if (board.checkTie()){
                System.out.println("Game Tied!");
                do{
                    // resetting game or ending game based on user input
                    System.out.println("Would you like to play again? Y/N");
                    end = sc.next().charAt(0);
                    if ((end == 'Y') || (end == 'y')){
                        board = initNewGame(board, sc);
                        round = 0;
                    }
                }
                while((end != 'N') && (end != 'n') && (end != 'Y') && (end != 'y'));
            }

        }
        while((end != 'N') && (end != 'n'));
        // closing scanner object
        sc.close();
    }

    /**
      * Static function that will insert char p into the lowest row that is not already filled in column c
      *
      * @param sc scanner for the entire class
      *
      * @return char array with each character being capitalized representing a player in the game
      */

    public static char[] findPlayersInfo(Scanner sc){
        int numPlayers;
        char[] players;
        char input;
        boolean invalid = false;

        do{
            System.out.println("How many players?");
            numPlayers = sc.nextInt();

            if (numPlayers < IGameBoard.MIN_NUM_PLAYERS){
                System.out.print("Must be at least ");
                System.out.print(IGameBoard.MIN_NUM_PLAYERS);
                System.out.println(" players");
                invalid = true;

            }
            else if (numPlayers > IGameBoard.MAX_NUM_PLAYERS){
                System.out.print("Must be ");
                System.out.print(IGameBoard.MAX_NUM_PLAYERS);
                System.out.println(" players or fewer");
                invalid = true;
            }
            else if ((numPlayers >= IGameBoard.MIN_NUM_PLAYERS) && (numPlayers <= IGameBoard.MAX_NUM_PLAYERS)){
                invalid = false;
            }
        }while (invalid != false);

        players = new char[numPlayers];

        for (int i = 1; i <= numPlayers; i++){
            invalid = false;
            System.out.print("Enter the character to represent player ");
            System.out.println(i);
            input = sc.next().charAt(0);
            input = Character.toUpperCase(input);
            for (int j = 1; j <= numPlayers; j++){
                if (players[j-1] == input){
                    System.out.print(input);
                    System.out.println(" is already taken as a player token!");
                    i--;
                    invalid = true;
                }
            }
            if (invalid != true){
                players[i-1] = input;
            }
        }
        return players;
    }

    /**
      * Static function that will create an int array with all of the information for the game to begin
      *
      * @param sc scanner for the entire class
      * 
      * @return int array with int[0] == rows, int[1] == columns, and int[2] == numWin
      */

    public static int[] findBoardInfo(Scanner sc){
        int rows, columns, numWin;
        int []returnArray;
        boolean invalid = false;

        // receive input for rows
        do{
            System.out.println("How many rows should be on the board?");
            rows = sc.nextInt();
            if (rows < IGameBoard.MIN_NUM_ROWS){
                System.out.println("Must be at least " + IGameBoard.MIN_NUM_ROWS + " rows!");
                invalid = true;
            }
            else if (rows > IGameBoard.MAX_NUM_ROWS){
                System.out.println("Must be " + IGameBoard.MAX_NUM_ROWS + " rows or fewer!");
                invalid = true;
            }
            if ((rows >= IGameBoard.MIN_NUM_ROWS) && (rows <= IGameBoard.MAX_NUM_ROWS)){
                invalid = false;
            }

        }while(invalid != false);

        // receive input for columns
        do{
            System.out.println("How many columns should be on the board?");
            columns = sc.nextInt();
            if (columns < IGameBoard.MIN_NUM_COLUMNS){
                System.out.println("Must be at least " + IGameBoard.MIN_NUM_COLUMNS + " columns!");
                invalid = true;
            }
            else if (columns > IGameBoard.MAX_NUM_COLUMNS){
                System.out.println("Must be " + IGameBoard.MAX_NUM_COLUMNS + " columns or fewer!");
                invalid = true;
            }
            if ((columns >= IGameBoard.MIN_NUM_COLUMNS) && (columns <= IGameBoard.MAX_NUM_COLUMNS)){
                invalid = false;
            }

        }while(invalid != false);

        do{
            System.out.println("How many in a row to win?");
            numWin = sc.nextInt();

            if (numWin < IGameBoard.MIN_IN_A_ROW){
                System.out.println("Must be at least " + IGameBoard.MIN_IN_A_ROW + " in a row to win!");
                invalid = true;
            }
            else if (numWin > IGameBoard.MAX_IN_A_ROW){
                System.out.println("Must be " + IGameBoard.MAX_IN_A_ROW + " in a row to win or fewer!");
                invalid = true;
            }
            if ((numWin >= columns) || (numWin >= rows)){
                System.out.println("Number to win in a row must be less than the number of rows and columns!");
                invalid = true;
            }
            if ((numWin >= IGameBoard.MIN_IN_A_ROW) && (numWin <= IGameBoard.MAX_IN_A_ROW) && ((numWin < columns) && (numWin < rows))){
                invalid = false;
            }

        }while(invalid != false);

        returnArray = new int[]{rows,columns,numWin};
        return returnArray;
    }

    /**
      * This function will initialize an object with data based on user input
      *
      * @param IGameBoard object of type gameBoard
      *
      * @return Object that implements IGameBoard with its member variables initialized
      *
      */

    public static IGameBoard initNewGame(IGameBoard board, Scanner sc){
        char gameType;
        int[] boardInfo = findBoardInfo(sc);

        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)");
        gameType = sc.next().charAt(0);
        while((gameType != 'f') && (gameType != 'F') && (gameType != 'M') && (gameType != 'm')){
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)");
            gameType = sc.next().charAt(0);
        }
        if ((gameType == 'f') || (gameType == 'F')){
            board = new GameBoard(boardInfo[0],boardInfo[1], boardInfo[2]);
            return board;
        }
        else{
            board = new GameBoardMem(boardInfo[0],boardInfo[1], boardInfo[2]);
            return board;
        }
    }
 
}