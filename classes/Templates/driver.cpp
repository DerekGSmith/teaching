#include <iostream>

#include "LinkList.h"
#include "Node.h"

using namespace std;

int main() {
    LinkList<int> scores;
    LinkList<string> people;
    string name = "Person";

    for (int i = 1; i <= 10; i++) {
        // populate scores
        scores.add(i);

        // populate people
        people.add(name + to_string(i));
    }

    cout << scores.print();
    cout << people.print();
    return 0;
}
