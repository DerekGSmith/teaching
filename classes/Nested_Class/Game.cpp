#include "Game.h"

/**
 * Constructor for Game
 *
 * @param n a string which represents the gameName in Game
 *
 * @pre none
 *
 * @post Game.gameName = n
 *
 * @return none
 *
 */
Game::Game(string n) : gameName(n) {}

/**
 * Function to add a player to Game's list of players
 *
 * @param n a string which represents the gameName in Game
 * @param t a char which represents a player token
 * @param p an int representing a players points
 *
 * @pre p >= 0
 *
 * @post [A new player will be added to the back of the PlayerList with values
 *        initialized]
 *
 * @return none
 *
 */
void Game::addPlayer(string n, char t, int p) {
    Player player(n, t, p);
    playerList.push_back(player);
}

/**
 * Function to add points to a specific player in the game
 *
 * @param pIndex an int representing the index of a player in PlayerList
 * @param points an int representing the points to be added to the player
 *
 * @pre points >= 0
 *
 * @post Game.gameName = n
 *
 * @return none
 *
 */
void Game::addPoints(int pIndex, int points) {
    points += playerList[pIndex].getPoints();
    playerList[pIndex].setPoints(points);
}

// Getters //
// You can write contracts for getters but standard getters really should never
// need them

Player Game::getPlayer(int a) { return playerList[a]; }
string Game::getGameName() { return gameName; }