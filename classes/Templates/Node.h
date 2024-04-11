#ifndef NODE_H
#define NODE_H

template <class T>
class Node {
   private:
    T data;      // the object information
    Node* next;  // pointer to the next node element

   public:
    // setters
    void setValue(T newData) { this->data = newData; }
    void setNext(Node* node) { this->next = node; }

    // getters
    T getValue() { return this->data; }
    Node* getNext() { return this->next; }
};

#endif