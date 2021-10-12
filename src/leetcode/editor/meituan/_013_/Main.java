package leetcode.editor.meituan._013_;

/*
小美喜欢字母 E ，讨厌字母 F 。在小美生日时，小团送了小美一个仅包含字母 E 和 F 的字符串，小美想从中选出一个包含字母 E 数量与字母 F 数量之差最大的子串。
*子串：从字符串前面连续删去若干个字符，从后面连续删去若干个字符剩下的字符串（也可以一个都不删），例如 abcab 是 fabcab 的子串，而不是 abcad 的子串。
我们将空串看作所有字符串的子串。

格式：


输入：
- 第一行一个正整数 n 表示字符串的长度。
- 第二行长度为 n ，且仅包含大写字母 'E', 'F' 的字符串（不含引号）
输出：
- 输出一个整数，表示最大的差值。
示例：


输入：
     5
     EFEEF
输出：2
解释：
选择子串 EE ，此时有 2 个 E ，0 个 F ，有最大差值 2-0=2
另外，选择子串 EFEE 也可以达到最大差值。
提示：

对于 30% 的数据，n <= 300
对于 60% 的数据，n <= 3000
对于 100% 的数据，n <= 300000
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        // 输出的是E的数量减去F的最大数量
        // 前后缀存储最小的差值
        char E = 'E';
        char F = 'F';
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bufferedReader.readLine());
        String string = bufferedReader.readLine().trim();
        char[] chars = string.toCharArray();
        int[] prex = new int[size];
        int[] tail = new int[size];
        prex[0] = chars[0] == E ? 1 : -1;
        for (int i = 1; i < size; i++) {
            if (chars[i] == E) {
                prex[i] = prex[i - 1] + 1;
            } else {
                prex[i] = prex[i - 1] - 1;
            }
        }
        // 越小越好,允许重复
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        integers.add(0);
        tail[size - 1] = chars[size - 1] == E ? 1 : -1;
        integers.add(tail[size - 1]);
        for (int i = size - 2; i >= 0; i--) {
            if (chars[i] == E) {
                tail[i] = tail[i + 1] + 1;
            } else {
                tail[i] = tail[i + 1] - 1;
            }
            integers.add(tail[i]);
        }
        int sum = prex[size - 1];
        int ans = sum;
        integers.remove(tail[0]);
        for (int i = 0; i < size - 1; i++) {
            ans = Math.max(sum - prex[i], ans);
            integers.remove(tail[i + 1]);
            ans = Math.max(ans, sum - prex[i] - integers.peek());
        }
        System.out.print(ans);
    }
}
