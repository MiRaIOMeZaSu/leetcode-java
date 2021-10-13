package leetcode.editor._611_triangleNumber;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    class TreeNode {
        int[] arr;
        int limit;

        TreeNode(int size) {
            limit = size;
            arr = new int[limit * 4];
        }

        TreeNode(int[] nums) {
            int size = nums.length;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                max = Math.max(nums[i], max);
            }
            limit = max + 1;
            arr = new int[max * 4 + 1];
            for (int i = 0; i < size; i++) {
                if (nums[i] == 0) {
                    continue;
                }
                add(nums[i]);
            }
        }

        private void _add(int num, int bit, int currStart, int currEnd) {
            if (num < currStart || num > currEnd) {
                return;
            }
            arr[bit]++;
            if (currStart == currEnd) {
                return;
            }
            int mid = (currEnd + currStart) >> 1;
            _add(num, bit * 2 + 1, currStart, mid);
            _add(num, bit * 2 + 2, mid + 1, currEnd);
        }

        public void add(int num) {
            _add(num, 0, 0, limit);
        }

        public void remove(int num) {
            _remove(num, 0, 0, limit);
        }

        private void _remove(int num, int bit, int currStart, int currEnd) {
            if (num < currStart || num > currEnd) {
                return;
            }
            arr[bit]--;
            if (currStart >= currEnd) {
                return;
            }
            int mid = (currEnd + currStart) >> 1;
            _remove(num, bit * 2 + 1, currStart, mid);
            _remove(num, bit * 2 + 2, mid + 1, currEnd);
        }

        public int range(int start, int end) {
            return _range(0, start, end, 0, limit);
        }

        private int _range(int bit, int start, int end, int currStart, int currEnd) {
            if (start > currEnd || end < currStart) {
                return 0;
            }
            if (currStart >= start && currEnd <= end) {
                return arr[bit];
            }
            int mid = (currEnd + currStart) >> 1;
            return _range(bit * 2 + 1, start, end, currStart, mid) + _range(bit * 2 + 2, start, end, mid + 1, currEnd);
        }
    }

    public int triangleNumber(int[] nums) {
        // 使用线段树
        int size = nums.length;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                continue;
            }
            map.merge(nums[i], 1, Integer::sum);
        }
        TreeNode tree = new TreeNode(nums);
        int result = 0;
        for (int i : map.keySet()
        ) {
            // 两个和三个
            int count = map.get(i);
            if (count >= 2) {
                result += times(3, count);
                int rangeCount = tree.range(0, i * 2 - 1);
                result += times(2, count) * (rangeCount - count);
            }
        }
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < size; j++) {
                if (nums[i] == nums[j]) {
                    continue;
                }
                int min = Math.max(nums[i], nums[j]);
                min = Math.max(Math.abs(nums[i] - nums[j]), min);
                int max = nums[i] + nums[j];
                int temp = tree.range(min + 1, max - 1);
//                int[] arr = new int[]{nums[i], nums[j]};
//                for (int x = 0; x < 2; x++) {
//                    if (arr[x] > min && arr[x] < max) {
//                        temp--;
//                    }
//                }
                result += temp;
            }
        }
        return result;
    }

    private int times(int a, int b) {
        if (a == b) {
            return 1;
        } else if (a > b) {
            return 0;
        }
        a = Math.max(a, b - a);
        long result = 1;
        for (int i = b; i > a; i--) {
            result *= i;
        }
        for (int i = 2; i <= b - a; i++) {
            result /= i;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.triangleNumber(new int[]{2, 2, 2, 2, 45, 3, 43});
        System.out.println(ret);
    }
}