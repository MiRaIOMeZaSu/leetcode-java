package leetcode.editor._773_slidingPuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    String target = "123450";

    public int slidingPuzzle(int[][] board) {
        // 华容道
        // 深度优先遍历
        int curr = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                curr *= 10;
                curr += board[i][j];
            }
        }
        String start = String.valueOf(curr);
        if (start.length() != 6) {
            start = "0" + start;
        }
        solve(start, 0);
        if (!map.containsKey(target)) {
            return -1;
        }
        return map.get(target);
    }

    private void solve(String curr, int num) {
        String[] pivot = new String[]{target, curr};
        for (int i = 0; i < 2; i++) {
            if (map.containsKey(pivot[i])) {
                if (map.get(pivot[i]) <= num) {
                    return;
                }
            }
        }
        map.put(curr, num);
        List<String> next = getNext(curr);
        for (int i = 0; i < next.size(); i++) {
            solve(next.get(i), num + 1);
        }
    }

    private List<String> getNext(String curr) {
        List<String> res = new ArrayList<>();
        char[] arr = curr.toCharArray();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                index = i;
                break;
            }
        }
        char temp;
        if (index >= 3) {
            temp = arr[index - 3];
            arr[index] = temp;
            arr[index - 3] = '0';
            res.add(new String(arr));
            arr[index - 3] = temp;
            arr[index] = '0';
        }
        if (index < 3) {
            temp = arr[index + 3];
            arr[index] = temp;
            arr[index + 3] = '0';
            res.add(new String(arr));
            arr[index + 3] = temp;
            arr[index] = '0';
        }
        if (index != 3 && index != 0) {
            temp = arr[index - 1];
            arr[index] = temp;
            arr[index - 1] = '0';
            res.add(new String(arr));
            arr[index - 1] = temp;
            arr[index] = '0';
        }
        if (index != 2 && index != 5) {
            temp = arr[index + 1];
            arr[index] = temp;
            arr[index + 1] = '0';
            res.add(new String(arr));
            arr[index + 1] = temp;
            arr[index] = '0';
        }
        return res;
    }
}