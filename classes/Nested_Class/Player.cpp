#include "Player.h"

using namespace std;

Player::Player(string n, char t, int p) : name(n), token(t), points(p) {}

void Player::printPlayer() {
    cout << name << "(" << token << ") has " << points << " points." << endl;
}

string Player::getName() { return name; }

int Player::getPoints() { return points; }

char Player::getToken() { return token; }
