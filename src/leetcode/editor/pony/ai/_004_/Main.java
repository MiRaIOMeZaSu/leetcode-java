package leetcode.editor.pony.ai._004_;

import java.io.*;
/*
9 5
5 4 3 4 3 3 3 3 3
*/

public class Main {
    public static void main(String[] args) throws IOException {
        // Traffic Signal Lights
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp = bufferedReader.readLine().trim().split(" ");
        // n 和 h
        // red light height
        int h = Integer.parseInt(temp[1]);
        temp = bufferedReader.readLine().trim().split(" ");
        int size = temp.length;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = Integer.parseInt(temp[i]);
        }
        // 往右走
        // 红绿灯在最左边
        // 当前高度是确定的
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            long gap1 = h - nums[i];
            long len1 = i + 1;
            for (int j = i + 1; j < size; j++) {
                long len2 = j - i;
                if (len2 * gap1 > (long)nums[i] * len1) {
                    break;
                }
                if ((long)nums[i] * len1 - len2 * gap1 >= (long)nums[j] * len1) {
                    ans[j] = i + 1;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));
            bufferedWriter.write("\n");
        }
        bufferedWriter.flush();
    }
}
