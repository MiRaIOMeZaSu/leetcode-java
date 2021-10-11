package leetcode.editor.meituan._006_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 解码,获取最长结果
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        char[] chars = line.toCharArray();
        int size = chars.length;
        int leftIndex = 0;
        for (; leftIndex < size; leftIndex++) {
            if (chars[leftIndex] == 'M') {
                leftIndex++;
                break;
            }
        }
        for (; leftIndex < size; leftIndex++) {
            if (chars[leftIndex] == 'T') {
                leftIndex++;
                break;
            }
        }
        int rightIndex = size - 1;
        for (; rightIndex >= leftIndex; rightIndex--) {
            if (chars[rightIndex] == 'T') {
                rightIndex--;
                break;
            }
        }
        for (; rightIndex >= leftIndex; rightIndex--) {
            if (chars[rightIndex] == 'M') {
                rightIndex--;
                break;
            }
        }
        System.out.print(line.substring(leftIndex, rightIndex + 1));
    }
}
