package leetcode.editor._212_findWords;

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
    private static char zero = 'a';
    public int chIndex;
    public char ch;
    private Node[] nodes = new Node[26];
    public boolean isEnd;
    private int count = 0;

    public Node getChild(int index) {
        return nodes[index];
    }

    public void remove(int index) {
        nodes[index] = null;
        count--;
    }

    public void add(int index, Node node) {
        if (nodes[index] == null) {
            count++;
        }
        nodes[index] = node;
    }

    public Node(int index) {
        this.chIndex = index;
        this.ch = Util.IntToChar(index);
    }

    public Node(char ch) {
        this.chIndex = Util.charToInt(ch);
        this.ch = ch;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}

class Solution {
    List<String> ans = new ArrayList<>();
    char[][] board;
    static final private char whiteBlank = ' ';
    int[][] items = new int[][]{
            {0, -1}, {0, 1}, {1, 0}, {-1, 0}
    };

    public List<String> findWords(char[][] board, String[] words) {
        // 已经查找到的单词对其进行删除操作
        // 对已经存在的单词进行字典数的构建
        this.board = board;
        Node root = new Node(0);
        for (int i = 0; i < words.length; i++) {
            Node curr = root;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = Util.charToInt(ch);
                if (curr.getChild(index) == null) {
                    curr.add(index, new Node(ch));
                }
                curr = curr.getChild(index);
            }
            curr.isEnd = true;
        }
        // 字典树构造完毕
        // 从某点出发-回溯法
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                solve(i, j, new StringBuilder(), root);
            }
        }
        return ans;
    }

    private void solve(int i, int j, StringBuilder stringBuilder, Node curr) {
        if (curr.isEnd) {
            // 如果找到一个单词,将其移去
            curr.isEnd = false;
            ans.add(stringBuilder.toString());
        }
        char ch = board[i][j];
        int index = Util.charToInt(ch);
        // 防止重复使用
        if (board[i][j] == whiteBlank) {
            return;
        }
        board[i][j] = whiteBlank;
        Node child = curr.getChild(index);
        if (child != null) {
            for (int k = 0; k < items.length; k++) {
                int nextI = i + items[k][0];
                int nextJ = j + items[k][1];
                if (nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length) {
                    // 满足要求
                    stringBuilder.append(ch);
                    solve(nextI, nextJ, stringBuilder, child);
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
            // 搜索完毕后删除
            if (!child.isEnd && child.isEmpty()) {
                curr.remove(child.chIndex);
            }
        }
        // 搜索完毕后恢复表格
        board[i][j] = ch;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"});
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}