// List class defines a generic type called List, and provides
// its accessor methods.

// We do not put nil and cons methods here because they need to be
// generic static methods.

class List<E> {
    protected boolean empty;
    protected E head;
    protected List<E> tail;

    List(E x, List<E> t) {
        empty = false; head = x; tail = t;
    }
    List() {
        empty = true;
    }
    boolean isEmpty() {
        return empty;
    }
    E getHead() {
        return head;
    }
    List<E> getTail() {
        return tail;
    }
}
