package ua.edu.ucu.collections.immutable;


import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int linkedListSize;

    public static class Node {
        private Object data;
        private Node next;

        private Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.linkedListSize = 0;
    }

    public ImmutableLinkedList(Object[] array) {
        this.linkedListSize = array.length;
        head = new Node(array[0], null);
        Node current = head;
        for (int i = 1; i < array.length; i++) {
            Node newNode = new Node(array[i], null);
            current.next = newNode;
            current = newNode;
        }
        tail = current;
    }


    @Override
    public ImmutableList add(Object e) {
        return add(size(), e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] elements = {e};
        return addAll(index, elements);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        linkedListIndexException(index);
        Object[] immutableCopy = new Object[size() + c.length];
        ImmutableLinkedList newLinkedList;
        Node current = head;
        int i = 0;
        int j = 0;
        while (i < index) {
            immutableCopy[i] = current.data;
            current = current.next;
            i++;
        }
        while (i < c.length + index) {
            immutableCopy[i] = c[j];
            j++;
            i++;
        }
        while (current != null) {
            immutableCopy[i] = current.data;
            current = current.next;
            i++;
        }

        newLinkedList = new ImmutableLinkedList(immutableCopy);
        return newLinkedList;
    }

    @Override
    public Object get(int index) {
        linkedListIndexException(index);
        Node current = this.head;
        int i = 0;
        while (i != index) {
            current = current.next;
            i++;
        }
        return current.data;
    }

    @Override
    public ImmutableList remove(int index) {
        linkedListIndexException(index);
        Object[] immutableCopy;
        ImmutableLinkedList newLinkedList;
        immutableCopy = new Object[size() - 1];
        Node current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                current = current.next;
                if (current == null) {
                    break;
                }
                immutableCopy[i] = current.data;
            }
            else {
                immutableCopy[i] = current.data;
            }
            current = current.next;
            i++;
        }
        if (immutableCopy.length == 0) {
            newLinkedList = new ImmutableLinkedList();
        }
        else {
            newLinkedList = new ImmutableLinkedList(immutableCopy);
        }
        return newLinkedList;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        linkedListIndexException(index);
        Object[] immutableCopy;
        immutableCopy = new Object[size()];
        ImmutableLinkedList newLinkedList;

        Node current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                immutableCopy[i] = e;
            }
            else {
                immutableCopy[i] = current.data;
            }
            current = current.next;
            i++;
        }

        newLinkedList = new ImmutableLinkedList(immutableCopy);
        return newLinkedList;
    }

    @Override
    public int indexOf(Object e) {
        int index = 0;
        Node current = this.head;
        while (current != null) {
            if (current.data.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return linkedListSize;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray;
        newArray = new Object[size()];
        Node current = this.head;
        int i = 0;
        while (current != null) {
            newArray[i] = current.data;
            current = current.next;
            i++;
        }
        return newArray;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public void linkedListIndexException(int index) {
        if (size() < index || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public Object getLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }

    public ImmutableLinkedList removeFirst() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (ImmutableLinkedList) remove(size() - 1);
    }
}
