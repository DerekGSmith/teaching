#include "Game.h"
#include "Player.h"

int main() {
    string playerName, gameName;
    cout << "Enter Player Name: ";
    cin >> playerName;
    cout << "Enter Game Name: ";
    cin >> gameName;

    Game gameOne(gameName);

    gameOne.addPlayer(playerName, playerName[0], 0);
    gameOne.addPlayer("Bill", 'B', 0);
    gameOne.getPlayer(0).printPlayer();
    gameOne.getPlayer(1).printPlayer();

    cout << "The game is: " << gameOne.getGameName() << endl;

    return 0;
}