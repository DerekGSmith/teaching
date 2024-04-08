#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>
#include <string>

using namespace std;

/**
 * This class represents a player for a game that will contain the data needed
 * for a player
 *
 * @invariant points >= 0
 *
 */

class Player {
   private:
    string name;
    char token;
    int points;

   public:
    Player(string, char, int);
    void printPlayer();

    // getters
    string getName();
    int getPoints();
    char getToken();

    // setters
    void setName(string n) { name = n; }
    void setPoints(int p) { points = p; }
    void setToken(char t) { token = t; }
};
#endif