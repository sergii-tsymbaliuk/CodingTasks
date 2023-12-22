package net.tsymbaliuk.leetcode.stringpatternbiject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> index = new HashMap<>();
        Set<String> usedWords = new HashSet<>();

        String[] words = s.split(" ");
        ListIterator<String> iter = Arrays.asList(words).listIterator();

        for (char c : pattern.toCharArray()) {
            String w = null;
            do {
                if (!iter.hasNext()) return false;
                w = iter.next().trim();
            } while (w.isEmpty());

            if (!index.containsKey(c)) {
                if (usedWords.contains(w)) return false;
                index.put(c, w);
            } else {
                if (!w.equals(index.get(c))) {
                    return false;
                }
            }
        }

        return !iter.hasNext();
    }
}
