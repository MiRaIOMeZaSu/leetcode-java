package leetcode.editor.interview.mihoyo._2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 截取字符串
     *
     * @param original string字符串
     * @param count    int整型
     * @return string字符串
     */

    public String cutString(String original, int count) {
        // write code here
//        StringReader stringReader = new StringReader(original);
//
//        try {
//            int temp = stringReader.read();
//            while (temp!=-1){
//                temp = stringReader.read();
//                System.out.println(temp);
//            }
//        } catch (Exception e) {
//            return _solve(original, count);
//        }
        return _solve(original, count);
    }

    private String _solve(String original, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = original.length();
        int curr = 0;
        for (int i = 0; i < size; i++) {
            // 汉字的区间,转换为字节流?
            char ch = original.charAt(i);
            if (ch > Byte.MAX_VALUE) {
                curr += 2;
            } else {
                curr += 1;
            }
            if (curr <= count) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cutString("1{* 我ABC汉DEF", 100));
    }
}
