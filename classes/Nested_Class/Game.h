#ifndef GAME_H
#define GAME_H

#include <string>
#include <vector>

#include "Player.h"

using namespace std;

/**
 * This class represents a player for a game that will contain the data needed
 * for a player
 *
 * @invariant [Any player in playerList will not have negative points]
 *
 */
class Game {
   private:
    string gameName;
    vector<Player> playerList;

   public:
    Game(string);
    void addPlayer(string, char, int);
    void addPoints(int, int);
    Player getPlayer(int);
    string getGameName();
};
#endif