package leetcode.editor._443_compress;

class Solution {
    public int compress(char[] chars) {
        char lastChar = chars[0];
        int size = chars.length;
        int lastIndex = 0;
        int count = 1;
        for (int i = 1; i <= size; i++) {
            if (i < size && chars[i] == lastChar) {
                count += 1;
            } else {
                chars[lastIndex] = lastChar;
                // 开始填入结果
                String toAdd = String.valueOf(count);
                int j = 0;
                if (!toAdd.equals("1")) {
                    for (; j < toAdd.length(); j++) {
                        chars[lastIndex + j + 1] = toAdd.charAt(j);
                    }
                }
                lastIndex = lastIndex + j + 1;
                if (i < size) {
                    lastChar = chars[i];
                }
                count = 1;
            }
        }
        return lastIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] input = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(solution.compress(input));
        System.out.println(input);
    }
}
