#ifndef LINKLIST_H
#define LINKLIST_H

#include <iostream>
#include <sstream>
#include <string>

#include "Node.h"

using namespace std;

template <class T>
class LinkList {
    Node<T>*head, *tail;

   public:
    LinkList() {
        head = NULL;
        tail = NULL;
    }

    /** Creates a node and adds it to the linked list
     *
     * @param info of a template type representing the new nodes data
     *
     * @pre none
     * @post [LinkedList will contain one more node]
     *
     */
    void add(T info) {
        // empty list case
        if (head == NULL) {
            head = new Node<T>;  // Create new node of type T
            head->setValue(info);
            tail = head;
        }

        // if not empty add to the end and move the tail
        else {
            Node<T>* temp = new Node<T>;
            temp->setValue(info);
            temp->setNext(NULL);
            tail->setNext(temp);
            tail = tail->getNext();
        }
    }

    /** Returns the linked list's data formatted
     *
     * @pre none
     * @post #self = self [linkedList will not change]
     *
     * @return string of the lists data
     */
    string print() {
        stringstream outputString;
        // to be used to iterate through list
        Node<T>* cur = this->head;

        if (head == NULL) {
            cout << "List is empty" << endl;
        }

        while (cur != NULL) {
            outputString << cur->getValue() << " ";
            cur = cur->getNext();
        }
        outputString << endl;
        return outputString.str();
    }
};

#endif