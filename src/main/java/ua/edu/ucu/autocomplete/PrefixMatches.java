package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int amountOfWords = 0;
        for (String value : strings) {
            String[] newWords = value.split("\\s+");
            for (String word : newWords) {
                if (!trie.contains(word) && word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                    amountOfWords++;
                }
            }
        }
        return amountOfWords;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("Inappropriate length "
                    + "of the prefix.");
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2 || k < 1) {
            throw new IllegalArgumentException("Inappropriate arguments.");
        }
        List<String> foundWords = new ArrayList<>();
        Iterable<String> wordsWithPrefix = trie.wordsWithPrefix(pref);
        for (String word : wordsWithPrefix) {
            if (word.length() < k + pref.length()) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }

    public int size() {
        return trie.size();
    }
}
