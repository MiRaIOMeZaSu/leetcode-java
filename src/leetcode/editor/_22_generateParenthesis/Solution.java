package leetcode.editor._22_generateParenthesis;


import java.util.ArrayList;
import java.util.List;

class ParenthesisString {
    public String getString() {
        return string;
    }

    String string;

    public int getN() {
        return n;
    }

    int n;

    public int getCountL() {
        return countL;
    }

    public int getCountR() {
        return countR;
    }

    int countL = 0;
    int countR = 0;

    public ParenthesisString(String string, int n) {
        this.string = string;
        if (string == "(") {
            countL++;
        } else {
            countR++;
        }
        this.n = n;
    }

    public boolean push(String parenthesis) {
        if (parenthesis == "(") {
            this.string += "(";
            countL++;
        } else {
            this.string += ")";
            countR++;
        }
        if (countL < countR || countL > n) {
            return false;
        }
        return true;
    }

    public int getLength() {
        return countL + countR;
    }

    public void pop() {
        char removed = this.string.charAt(this.string.length() - 1);
        this.string = this.string.substring(0, this.string.length() - 1);
        if (removed == "(".charAt(0)) {
            this.countL--;
        } else {
            this.countR--;
        }
    }
}

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        if (n == 1) {
            ret.add("()");
            return ret;
        }
        // 使用回溯法
        solve(new ParenthesisString("(", n), ret);
        return ret;
    }

    private void solve(ParenthesisString p, List<String> ret) {
        if (p.getLength() == p.getN() * 2) {
            ret.add(p.getString());
            return;
        }
        if (p.getCountL() == p.getN()) {
            String a = p.getString();
            while (a.length() != p.getN() * 2) {
                a += ")";
            }
            ret.add(a);
            return;
        }
        String[] list = {"(", ")"};
        for (String i : list) {
            if (p.push(i)) {
                solve(p, ret);
            }
            p.pop();
        }
    }
}