package leetcode.editor.leetcode._38_countAndSay;

class Solution {
    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder next = new StringBuilder();
        for (int i = 1; i < n; i++) {
            char last = curr.charAt(0);
            int count = 1;
            for (int j = 1; j < curr.length(); j++) {
                if (curr.charAt(j) == last) {
                    count++;
                } else {
                    next.append(count);
                    next.append(last);
                    count = 1;
                    last = curr.charAt(j);
                }
            }
            next.append(count);
            next.append(last);
            curr = next;
            next = new StringBuilder();
        }
        return curr.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(5));
    }
}
