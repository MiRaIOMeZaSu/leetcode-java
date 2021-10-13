package leetcode.editor.leetcode._412_fizzBuzz;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int bit = 0;
            if (i % 3 == 0) {
                bit |= 1;
            }
            if (i % 5 == 0) {
                bit |= 2;
            }
            switch (bit) {
                case 1:
                    list.add("Fizz");
                    break;
                case 2:
                    list.add("Buzz");
                    break;
                case 3:
                    list.add("FizzBuzz");
                    break;
                default:
                    list.add(String.valueOf(i));
                    break;
            }
        }
        return list;
    }
}