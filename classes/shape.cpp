/*
Classes
    Abstraction
    Encapsulation
    Inheritance
    Polymorphism
    Object Oriented Programming

Important Terms

Creating a Class
    Classes are objects consisting of a name, access specifiers,
    and methods and members that fall under access specifiers.

    Access Specifiers:
        protected: Same as private but classes that inherit the class can access
        private: Only accessible inside of the class
        public: Accessible anywhere

    Methods:
        Getters - Getters will get private variables.
        Setters - Setters will set private variables.
        Other functions

    Members:
        Look below for examples.

Inheritance
    Inheritance is when one class 'inherits' from another
    A class that is inherited from is called a parent
    A class that inherits a class is called a child or a derived class
    parent
      |
     child

    *very simple concept but struggle often comes from knowing
    *what an inherited class has access to
    Polymorphism
        This can be a difficult concept
*/
#include <iostream>
#include <string>

using namespace std;

// i should be writing contracts for these but I am too lazy rn
class player {
   private:
    string name;

   public:
    string getName();
    void setName(string a) { name = a; }
};

class game {
   protected:
   public:
};

int main() { return 0; }