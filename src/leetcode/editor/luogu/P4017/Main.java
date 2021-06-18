package leetcode.editor.luogu.P4017;

import java.util.*;
import java.io.*;

public class Main {
//    public static int ret = 0, s = 80112002;
//    public static int[] rec;
//    public static Map<Integer, List<Integer>> in = new HashMap<>();
//    public static Map<Integer, List<Integer>> out = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int ret = 0, s = 80112002;
        int[] rec;
        Map<Integer, List<Integer>> in = new HashMap<>();
        Map<Integer, List<Integer>> out = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;
        for (int i = 1; i <= n; i++) {
            //吃谁
            in.put(i, new ArrayList<>());
            //被谁吃
            out.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st.nextToken();
            int beichi = (int) st.nval;
            st.nextToken();
            int chi = (int) st.nval;
            in.get(chi).add(beichi);
            out.get(beichi).add(chi);
        }
        rec = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (in.get(i).isEmpty()) {
                rec[i] = 1;
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            int size = out.get(cur).size();
            for (int i = 0; i < size; i++) {
                // 遍历cur的每个入度
                int in_i = out.get(cur).get(i);
                // 在移除in_i的cur出度
                in.get(in_i).remove((Integer) cur);
                rec[in_i] += rec[cur];
                rec[in_i] = rec[in_i] % s;
                if (in.get(in_i).isEmpty()) {
                    q.offer(in_i);
                }
                if (out.get(in_i).isEmpty() && in.get(in_i).isEmpty()) {
                    ret = (ret + rec[in_i]) % s;
                }
            }
        }

        System.out.println(ret);
    }
}