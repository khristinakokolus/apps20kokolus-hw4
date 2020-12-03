package ua.edu.ucu.iterator;

import ua.edu.ucu.collections.Queue;

public class WordsContainer implements Container<String>{
    Queue elements;

    public WordsContainer(Queue queue) {
        this.elements = queue;
    }

    @Override
    public Iterator getIterator() {
        return new QueueIterator(elements);
    }

    private static class QueueIterator implements Iterator<String> {
        Queue queue;

        public QueueIterator(Queue elements) {
            this.queue = elements;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public String next() {
            return (String)queue.dequeue();
        }
    }
}
