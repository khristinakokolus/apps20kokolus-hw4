package ua.edu.ucu.tries;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ucu.collections.Queue;

// implemented using book "Algorithms" 4th Edition
public class RWayTrie implements Trie {
    private static final int R = 26;
    private static final int aValue = 97;
    private int size = 0;
    private Node root = new Node('\0');

    @Setter
    @Getter
    public static class Node {
        private int weight;
        private char character;
        private Node[] next;

        public Node(char character) {
            this.weight = - 1;
            this.character = character;
            this.next = new Node[R];
        }
    }


    @Override
    public void add(Tuple t) {
        Node current = root;
        for (int i = 0; i < t.weight; i++) {
            char c = t.term.charAt(i);
            if (current.next[c - aValue] == null) {
                current.next[c - aValue] = new Node(c);
            }
            current = current.next[c - aValue];
        }
        size++;
        current.weight = t.weight;
    }


    @Override
    public boolean contains(String word) {
        Node neededWord = get(word);
        return neededWord != null && neededWord.getWeight() != -1;
    }

    private Node get(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.next[c - aValue] == null) {
                return null;
            }
            current = current.next[c - aValue];
        }
        return current;
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            Node nodeToDelete = get(word);
            nodeToDelete.setWeight(-1);
            size--;
            return true;
        }
        return false;
    }


    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue queue = new Queue();
        collect(get(s), s, queue);
        return queue;
    }

    private void collect(Node word, String prefix, Queue queue) {
        if (word == null) {
            return;
        }
        if (word.getWeight() != -1)  {
            queue.enqueue(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(word.next[c], prefix + (char) (c + aValue),
                    queue);
        }
    }

    @Override
    public int size() {
        return size;
    }

}
