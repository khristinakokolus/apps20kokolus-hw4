package ua.edu.ucu.collections;

import lombok.Getter;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.iterator.WordsContainer;

import java.util.Iterator;

@Getter
public class Queue implements Iterable<String> {
    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList newQueue) {
        this.queue = newQueue;
    }

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getFirst();
    }

    public Object dequeue() {
        Object first;
        first = queue.getFirst();
        queue = queue.removeFirst();
        return first;
    }

    public void enqueue(Object e) {
        queue = queue.addLast(e);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        Queue newQueue = new Queue(getQueue());
        WordsContainer wordsContainer = new WordsContainer(newQueue);
        return wordsContainer.getIterator();
    }
}
