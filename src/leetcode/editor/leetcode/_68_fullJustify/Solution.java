package leetcode.editor._68_fullJustify;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int curr = 0;
        char whitespace = ' ';
        int currCount = 0;
        int size = words.length;
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (curr + words[i].length() + currCount > maxWidth) {
                int rest = maxWidth - curr;
                stringBuilder.append(words[i - currCount]);
                if (currCount != 1) {
                    int averageRest = rest / (currCount - 1);
                    int restOfrest = rest % (currCount - 1);
                    for (int j = 1; j < currCount; j++) {
                        for (int x = 0; x < averageRest; x++) {
                            stringBuilder.append(whitespace);
                        }
                        if (restOfrest > 0) {
                            restOfrest--;
                            stringBuilder.append(whitespace);
                        }
                        stringBuilder.append(words[i - currCount + j]);
                    }
                } else {
                    for (int x = 0; x < rest; x++) {
                        stringBuilder.append(whitespace);
                    }
                }
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                currCount = 0;
                curr = 0;
            }
            curr += words[i].length();
            currCount++;
        }
        // 最后一行不插入额外的空格
        for (int i = size - currCount; i < size - 1; i++) {
            stringBuilder.append(words[i]);
            stringBuilder.append(whitespace);
        }
        stringBuilder.append(words[size - 1]);
        while (stringBuilder.length() < maxWidth) {
            stringBuilder.append(whitespace);
        }
        result.add(stringBuilder.toString());
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
    }
}