#include "Game.h"

Game::Game(string n) : gameName(n) {}

void Game::addPlayer(string n, char t, int p) {
    Player player(n, t, p);
    playerList.push_back(player);
}

void Game::addPoints(int pIndex, int points) {
    points += playerList[pIndex].getPoints();
    playerList[pIndex].setPoints(points);
}

Player Game::getPlayer(int a) { return playerList[a]; }

string Game::getGameName() { return gameName; }