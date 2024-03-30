/*
Creating Contracts

Contracts are guarantees and comments for classes and functions
Contracts should be written before the code for the function is written
Contracts begin with a description (look below for an example)
Applicable fields for the contracts begin after that and start with @

Note: Contracts for classes and functions are different, look for the class
example to see those
---Fields---
@param - create one for each parameter in the function || describe each
parameter's use
@pre - will state prerequisites for the function to be called
@post - conveys the state of variables after the function is called (mostly
applicable with classes and functions with variables passed by reference)
@return - describes what the function returns
*/

#include <iostream>
using namespace std;

int multiply(int, int);
double divide(int, int);
void add(int &);

int main() {
    int one = 5, two = 2;

    cout << "The result of multiply is " << multiply(one, two) << endl;
    cout << "The result of divide is " << divide(one, two) << endl;
    add(one);
    cout << "The result of add to is " << one << endl;

    return 0;
}

/**
 * Function multiply two different integers with each other and return the
 * result
 *
 * @param a number that represents an integer to be multiplied
 * @param b number that represents an integer to be multiplied
 *
 * @pre none
 *
 * @post none
 *
 * @return a*b
 *
 */
int multiply(int a, int b) { return a * b; }

/**
 * Function divide two different integers that do not equal 0 with each other
 * and return the result
 *
 * @param a number that represents an integer to be divided
 * @param b number that represents an integer to be divided
 *
 * @pre b != 0
 *
 * @post none
 *
 * @return a/b
 *
 */
double divide(int a, int b) { return a / b; }

/**
 * Function adds one to a variable passed by reference
 *
 * @param a number that represents an integer that will have one added to it
 *
 * @pre none
 *
 * @post a+=1
 *
 * @return none
 *
 */
void add(int &a) { a += 1; }