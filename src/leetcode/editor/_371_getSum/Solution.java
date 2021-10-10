package leetcode.editor._371_getSum;

class Solution {
    public int getSum(int a, int b) {
        // 使用逐位变换法
        int curr = 1;
        int last = 0;
        int ans = 0;
        int count = 1;
        while (true) {
            if (last == 1) {
                count = count << 1;
            }
            if ((a | curr) == a) {
                count = count << 1;
            }
            if ((b | curr) == b) {
                count = count << 1;
            }
            if (count == 2 || count == 8) {
                ans |= curr;
            }
            if (count == 4 || count == 8) {
                last = 1;
            } else {
                last = 0;
            }
            count = 1;
            if(curr==1073741824 * 4){
                break;
            }
            curr = curr << 1;
        }
        if (last == 1) {
            ans |= curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.getSum(3, 2);
        System.out.println(ans);
    }
}