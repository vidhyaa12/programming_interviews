package leetcode;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    // Initialize your data structure here.
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }
}

public class Trie {
    private static final String EMPTY_STR = "";

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        insertHelper(root, word);
    }

    private void insertHelper(TrieNode trieNode, String word) {
        if (word.equals(EMPTY_STR)) {
            trieNode.isEndOfWord = true;
        } else {
            char firstChar = word.charAt(0);
            TrieNode childForFirstChar = trieNode.children.get(firstChar);

            if (childForFirstChar == null) {
                childForFirstChar = new TrieNode();
                trieNode.children.put(firstChar, childForFirstChar);
            }
            insertHelper(childForFirstChar, word.substring(1));
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return searchHelper(root, word, false);
    }

    private boolean searchHelper(TrieNode trieNode, String word, boolean isPrefixSearch) {
        if (trieNode == null) {
            return false;
        }

        if (word.equals(EMPTY_STR)) {
            // If it's prefix search, there is no need to check whether we have hit a node that has the end of word flag set
            // to true
            return trieNode.isEndOfWord | isPrefixSearch;
        }

        if (!trieNode.children.containsKey(word.charAt(0))) {
            return false;
        }

        return searchHelper(trieNode.children.get(word.charAt(0)), word.substring(1), isPrefixSearch);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchHelper(root, prefix, true);
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");