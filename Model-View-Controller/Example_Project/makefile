classpath = .:junit-4.13.2.jar:hamcrest-core-1.3.jar

default: cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnectX/models/AbsGameBoard.java
	javac cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java
	
run: cpsc2150/extendedConnects/GameScreen.class
	java cpsc2150/extendedConnects.GameScreen

test: cpsc2150/extendedConnectX/models/TestGameBoard.java cpsc2150/extendedConnectX/models/TestGameBoardMem.java
	javac -cp $(classpath) cpsc2150/extendedConnectX/models/TestGameBoard.java cpsc2150/extendedConnectX/models/TestGameBoardMem.java

testGB:
	java -cp $(classpath) org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoard

testGBMem:
	java -cp $(classpath) org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoardMem
	
clean: 
	rm -f cpsc2150/extendedConnectX/models/*.class
	rm -f cpsc2150/extendedConnects/*.class