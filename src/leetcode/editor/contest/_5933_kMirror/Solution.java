package leetcode.editor.contest._5933_kMirror;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int[] arr;
    boolean isOdd = true;
    long ans = 0;
    int k;
    int pivot;

    public long kMirror(int k, int n) {
        // 使用遍历
        // 10进制序列是相同的
        // 需要取出的是k进制镜像数与10进制镜像数的公共序列
        // 前x位的序列
        this.k = k;
        this.pivot = n;
        for (int i = 1; pivot != 0; i++) {
            // 奇偶交替
            // 奇数位
            isOdd = true;
            arr = new int[i];
            for (int x = 0; x < 2 && pivot != 0; x++) {
                for (int j = 1; j < 10 && pivot != 0; j++) {
                    arr[0] = j;
                    solve(i - 1);
                }
                isOdd = !isOdd;
            }
        }
        return ans;
    }

    private void solve(int times) {
        if (times == 0) {
            // 从arr中取值
            long num = 0;
            if (isOdd) {
                // 长度为奇数
                for (int i = 0; i < arr.length; i++) {
                    num *= 10;
                    num += arr[i];
                }
                for (int i = arr.length - 2; i >= 0; i--) {
                    num *= 10;
                    num += arr[i];
                }
            } else {
                // 长度为偶数
                for (int i = 0; i < arr.length; i++) {
                    num *= 10;
                    num += arr[i];
                }
                for (int i = arr.length - 1; i >= 0; i--) {
                    num *= 10;
                    num += arr[i];
                }
            }
            // 开始处理num
            convert(num);
            return;
        }
        for (int i = 0; i < 10 && pivot != 0; i++) {
            arr[arr.length - times] = i;
            solve(times - 1);
        }
    }

    private void convert(long num) {
        // 进制转换
        List<Integer> list = new ArrayList<>();
        long curr =  num;
        long currK = k;
        while (curr > 0) {
            long temp = curr % currK;
            list.add((int) (temp / (currK / k)));
            curr -= temp;
            currK *= k;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return;
            }
            left++;
            right--;
        }
        if (pivot == 0) {
            return;
        }
        ans += num;
        pivot--;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.kMirror(5, 25));
    }
}