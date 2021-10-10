package leetcode.editor._30_findSubstring;

import java.util.*;

class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> findSubstring(String s, String[] words) {
        // words长度最大为30,可以使用状态压缩法
        // words长度相同!
        // 动态规划!
        // 字典树?
        // 起点范围有限
        // 测试动态规划
        // 滑动窗口法!
        int wordLen = words[0].length();
        Map<String, Integer> originMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            Integer tempNum = originMap.merge(words[i], 1, Integer::sum);
            if (tempNum == 1) {
                list.add(words[i]);
            }
        }
        int answerLen = wordLen * words.length;
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> map = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            String tempStr;
            for (int k = 0; k < list.size(); k++) {
                tempStr = list.get(k);
                map.put(tempStr, originMap.get(tempStr));
            }
            // 滑动窗口起始的位置
            int left = i;
            int curr = left;
            while (left + answerLen - 1 < s.length()) {
                String next = s.substring(curr, curr + wordLen);
                if (originMap.containsKey(next)) {
                    int tempNum = map.merge(next, -1, Integer::sum);
                    if (tempNum == -1) {
                        String out;
                        do {
                            out = queue.poll();
                            left += wordLen;
                            map.merge(out, 1, Integer::sum);
                        } while (!out.equals(next) && left + answerLen - 1 < s.length());
                        if (left + answerLen - 1 >= s.length()) {
                            // 起始点过于靠后!
                            break;
                        }
                        queue.add(next);
                        curr = curr + wordLen;
                    } else {
                        queue.add(next);
                        curr = curr + wordLen;
                    }
                } else {
                    left = curr + wordLen;
                    curr = left;
                    if (left + answerLen - 1 >= s.length()) {
                        break;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        tempStr = list.get(k);
                        map.put(tempStr, originMap.get(tempStr));
                    }
                    queue = new LinkedList<>();
                }
                if (curr - left == answerLen) {
                    ans.add(left);
                    left += wordLen;
                    String out = queue.poll();
                    map.merge(out, 1, Integer::sum);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> ans = solution.findSubstring("bcabbcaabbccacacbabccacaababcbb",
                new String[]{"c", "b", "a", "c", "a", "a", "a", "b", "c"});
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
}