package ua.edu.ucu.iterator;

import lombok.Getter;
import ua.edu.ucu.collections.Queue;
@Getter
public class WordsContainer implements Container<String> {
    private Queue elements;

    public WordsContainer(Queue queue) {
        this.elements = queue;
    }

    @Override
    public Iterator getIterator() {
        return new QueueIterator(elements);
    }
    @Getter
    private static class QueueIterator implements Iterator<String> {
        private Queue queue;

        QueueIterator(Queue elements) {
            this.queue = elements;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public String next() {
            return (String) queue.dequeue();
        }
    }
}
