package leetcode.editor._1032_StreamChecker;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

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

class StreamChecker {
    Node root;
    Deque<Node> nodeDeque = new LinkedList<>();

    public StreamChecker(String[] words) {
        // 使用初始化时的字符构造前缀树
        root = new Node('a');
        for (int i = 0; i < words.length; i++) {
            Node curr = root;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = Util.charToInt(ch);
                Node child = curr.nodes[index];
                if (child == null) {
                    curr.nodes[index] = new Node(ch);
                }
                curr = curr.nodes[index];
            }
            curr.isEnd = true;
        }
    }

    public boolean query(char letter) {
        // 在前缀树上移动指针
        // 所有指针必须移动一位
        // 指针在树上行走,不存在移动问题
        boolean ans = false;
        int index = Util.charToInt(letter);
        int size = nodeDeque.size();
        for (int i = 0; i < size; i++) {
            Node node = nodeDeque.poll();
            Node next = node.nodes[index];
            if (next != null) {
                nodeDeque.add(next);
                if (next.isEnd) {
                    ans = true;
                }
            }
        }
        if (root.nodes[index] != null) {
            nodeDeque.add(root.nodes[index]);
            if (root.nodes[index].isEnd) {
                ans = true;
            }
        }
        return ans;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */