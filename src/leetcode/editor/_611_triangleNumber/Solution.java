package leetcode.editor._611_triangleNumber;


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
            limit = size + 1;
            arr = new int[limit * 4 + 1];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                max = Math.max(nums[i], max);
            }
            for (int i = 0; i < size; i++) {
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
            if (currStart >= start && currEnd <= end) {
                return arr[bit];
            }
            if (currStart >= currEnd) {
                return arr[bit];
            }
            int mid = (currEnd + currStart) >> 1;
            return _range(bit * 2 + 1, start, end, currStart, mid) + _range(bit * 2 + 2, start, end, mid + 1, currEnd);
        }
    }

    public int triangleNumber(int[] nums) {
        // 使用线段树
        int size = nums.length;
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < size; i++) {
//            set.add(nums[i]);
//        }
        TreeNode tree = new TreeNode(nums);
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                tree.remove(nums[i - 1]);
            }
            for (int j = i + 1; j < size; j++) {
//                int min = Math.min(nums[i], nums[j]);
//                min = Math.min(Math.abs(nums[i] - nums[j]), min);
                int min = Math.max(Math.abs(nums[i] - nums[j]), nums[j] - 1);
                int max = nums[i] + nums[j];
                int[] arr = new int[]{nums[i], nums[j]};
                int temp = tree.range(min + 1, max - 1);
                for (int x = 0; x < 2; x++) {
                    if (arr[x] > min && arr[x] < max) {
                        temp--;
                    }
                }
                result += temp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.triangleNumber(new int[]{2, 2, 3, 4});
        System.out.println(ret);
    }
}