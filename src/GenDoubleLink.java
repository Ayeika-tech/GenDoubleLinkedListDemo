
/**
   The DLinkedList class implements a doubly 
   Linked list. 
*/

class DLinkedList<T> {
    /**
     * The Node class stores a list element and a reference to the next node.
     */
    private class Node {
        T value; // Value of a list element
        Node next; // Next node in the list
        Node prev; // Previous element in the list

        /**
         * Constructor.
         * 
         * @param val The element to be stored in the node.
         * @param n   The reference to the successor node.
         * @param p   The reference to the predecessor node.
         */

        Node(T val, Node n, Node p) {
            value = val;
            next = n;
            prev = p;
        }

        /**
         * Constructor.
         * 
         * @param val The element to be stored in the node.
         */

        Node(T val) {
            // Just call the other (sister) constructor
            this(val, null, null);
        }
    }

    private Node first; // Head of the list
    private Node last; // Last element on the list

    /**
     * Constructor.
     */

    public DLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int count = 0;
        Node p = first;
        while (p != null) {
            // There is an element at p
            count++;
            p = p.next;
        }
        return count;
    }

    public void add(T e) {
        if (isEmpty()) {
            last = new Node(e);
            first = last;
        } else {
            // Add to end of existing list
            last.next = new Node(e, null, last);
            last = last.next;
        }
    }

    public void add(int index, T e) {
        if (index < 0 || index > size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Index is at least 0
        if (index == 0) {
            // New element goes at beginning
            Node p = first; // Old first
            first = new Node(e, p, null);
            if (p != null)
                p.prev = first;
            if (last == null)
                last = first;
            return;
        }

        // pred will point to the predecessor
        // of the new node.
        Node pred = first;
        for (int k = 1; k <= index - 1; k++) {
            pred = pred.next;
        }

        // Splice in a node with the new element
        // We want to go from pred-- succ to
        // pred--middle--succ
        Node succ = pred.next;
        Node middle = new Node(e, succ, pred);
        pred.next = middle;
        if (succ == null)
            last = middle;
        else
            succ.prev = middle;
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        Node p = first;
        int count = 0;
        while (p != null) {
            strBuilder.append(p.value + " is at Index:" + count + "\n");
            p = p.next;
            count++;
        }
        return strBuilder.toString();
    }

    public T remove(int index) {
        if (index < 0 || index >= size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Locate the node targeted for removal
        Node target = first;
        for (int k = 1; k <= index; k++)
            target = target.next;

        T element = target.value; // Element to return
        Node pred = target.prev; // Node before the target
        Node succ = target.next; // Node after the target

        // Route forward and back pointers around
        // the node to be removed
        if (pred == null)
            first = succ;
        else
            pred.next = succ;

        if (succ == null)
            last = pred;
        else
            succ.prev = pred;

        return element;
    }

    public boolean remove(T element) {
        if (isEmpty())
            return false;

        // Locate the node targeted for removal
        Node target = first;
        while (target != null && !element.equals(target.value))
            target = target.next;

        if (target == null)
            return false;

        Node pred = target.prev; // Node before the target
        Node succ = target.next; // Node after the target

        // Route forward and back pointers around
        // the node to be removed
        if (pred == null)
            first = succ;
        else
            pred.next = succ;

        if (succ == null)
            last = pred;
        else
            succ.prev = pred;

        return true;
    }

    // This method will remove all elements from list.
    public void clear() {

        // Set length var to the size of the list.
        int length = size();

        while (first != null) {
            // Start at the end of the list and start removing all elements
            for (int i = length - 1; i >= 0; i--) {
                remove(i);
            }
        }
        System.out.println("LinkedList has been cleared: " + size() + " Elements on the list");

    }

    public T get(int index) {

        Node current = first; // start at the head looking for index
        int count = 0;
        while (count < index) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException("Element not found at " + index);
            }
            count++;
        }
        return current.value;

    }

    public T set(int index, T element) {

        if (index < 0 || index > size())
            throw new IllegalArgumentException();

            int i = 0;
            Node tempNode = first;
        
            while (i < index)
            {
                tempNode = tempNode.next;
                i++;
            }
        
            T previousElement = tempNode.value;
            tempNode.value = element;
        
            return previousElement;
    }

    public static void main(String[] args) {
        DLinkedList<Double> dList = new DLinkedList<Double>();
        dList.add(12.2);
        dList.add(4.3);
        dList.add(0, 89.9);
        dList.add(1, 4.0);
        dList.add(4, 33.45);

        System.out.println("Testing toString");
        System.out.print(dList);// testing toString
        System.out.println("Using get() method with index 2, then index 4");// Using get() methods
        System.out.println(dList.get(2) + "\n" + dList.get(4));
        System.out.println("Using set() element adding 171.00 to index 0 and 50.00 to index 4");
        System.out.println("removed element @ index 0 was "+dList.set(0, 171.00));// Adding another element using the set method
        System.out.println("removed element @ index 4 was "+dList.set(4, 50.00));// and returning the swapped element
        System.out.print("New list is"+"\n"+dList);// testing toString
        System.out.println("Now I'm clearing list using clear()");
        dList.clear();// testing clear() method

    }
}