package leetcode.editor.nowcoder.baidu2021javaNo2_21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        int arr[] = new int[m];
        List<Integer> list = new ArrayList<>();
        int temp;
        for (int i = 0; i < m; i++) {
            temp = scanner.nextInt();
            arr[i] = temp;
            if (!map.containsKey(temp)) {
                list.add(temp);
                map.put(temp, 1);
            } else {
                map.put(temp, map.get(temp) + 1);
            }
        }
        int ret = 0;
        // 此处开始针对窗口数量分类(该窗口具有最长队列
        // 最长队列进行分类
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int a_i = list.get(i);
            // 如何计算剩余必然小于当前排队长度的情况
        }
    }
}
