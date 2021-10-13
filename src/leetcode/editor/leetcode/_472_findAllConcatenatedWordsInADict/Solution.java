package leetcode.editor._472_findAllConcatenatedWordsInADict;

import java.util.ArrayList;
import java.util.List;

class Util {
    static char zero = 'a';

    public static int charToInt(char ch) {
        return ch - 'a';
    }

    public static char IntToChar(int i) {
        return (char) (zero + i);
    }
}

class Node {
    private static final char zero = 'a';
    public int chIndex;
    public char ch;
    public final Node[] nodes = new Node[26];
    public boolean isEnd;

    public Node(int index) {
        this.chIndex = index;
        this.ch = Util.IntToChar(index);
    }

    public Node(char ch) {
        this.chIndex = Util.charToInt(ch);
        this.ch = ch;
    }
}

class Solution {
    List<String> ans = new ArrayList<>();
    Node root = new Node('a');
    boolean[] visited;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        //前缀树问题
        //可以重复使用
        //只输出满足的单词,不需要考虑多种方法(如果满足则直接跳过)
        //先确认每个单词是否是其他单词的前缀,再在此基础上寻找
        //前缀存储单词或单词存储前缀?
        //回溯法

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            minLen = Math.min(words[i].length(), minLen);
            Node curr = root;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = Util.charToInt(ch);
                if (curr.nodes[index] == null) {
                    curr.nodes[index] = new Node(ch);
                }
                curr = curr.nodes[index];
            }
            curr.isEnd = true;
        }
        // 前缀树构造完毕
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < minLen * 2) {
                continue;
            }
            visited = new boolean[words[i].length()];
            if (solve(words[i], 0, 0)) {
                ans.add(words[i]);
            }
        }
        return ans;
    }

    private boolean solve(String word, int index, int count) {
        if (index == word.length()) {
            return count >= 2;
        }
        Node curr = root;
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node next = curr.nodes[Util.charToInt(ch)];
            if (next != null) {
                if (!visited[i] && next.isEnd) {
                    if (solve(word, i + 1, count + 1)) {
                        return true;
                    }
                    visited[i] = true;
                }
                curr = next;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findAllConcatenatedWordsInADict(new String[]{"cat", "dog", "catdog"});
    }
}