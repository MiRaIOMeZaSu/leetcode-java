package leetcode.editor._402_removeKdigits;

class Solution {
    static final char zero = '0';
    StringBuilder stringBuilder = new StringBuilder();
    int size;

    public String removeKdigits(String num, int k) {
        // 可移除的数量是限定的
        // 尽量使得首位最小
        // 递归回调
        // 如果有零,必须将零前所有去掉(能够降低最后的位数

        size = num.length();
        if (k >= size) {
            return "0";
        }
        char[] chars = num.toCharArray();
        // 直接寻找为零字符数量
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (chars[i] != zero) {
                count++;
            }
        }
        if (count <= k) {
            return "0";
        }
        return privateRemoveKDigits(chars, 0, k);
    }

    private String privateRemoveKDigits(char[] chars, int index, int k) {
        for (int i = k + index; i >= index; i--) {
            if (chars[i] == zero) {
                // 可以开始往前寻找非零数量
                int count = 0;
                for (int j = 0; j < i; j++) {
                    if (chars[j] != zero) {
                        count++;
                    }
                }
                return privateRemoveKDigits(chars, i + 1, k - count);
            }
        }
        // 没有位数的变化(任何情况下都不会再有了!
        // 在前几位中选择每一位
        // 使用递归?
        // 前k + 1个中选择一个
        char last;
        int lastIndex;
        int targetSize = size - index - k;
        StringBuilder stringBuilder = new StringBuilder();
        while (k > 0 && index < size && targetSize > stringBuilder.length()) {
            last = chars[index];
            lastIndex = index;
            for (int i = index + 1; i < Math.min(index + k + 1, size); i++) {
                if (last > chars[i]) {
                    last = chars[i];
                    lastIndex = i;
                }
            }
            stringBuilder.append(last);
            k -= lastIndex - index;
            index = lastIndex + 1;
            if (index >= size) {
                break;
            }
        }
        int rest = targetSize - stringBuilder.length();
        for (int i = index; i < Math.min(size, index + rest); i++) {
            stringBuilder.append(chars[i]);
        }
        String temp = stringBuilder.toString();
        int ansIndex = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != zero) {
                ansIndex = i;
                break;
            }
        }
        return temp.substring(ansIndex);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.removeKdigits("112", 1);
    }
}