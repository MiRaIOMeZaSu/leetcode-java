package leetcode.editor._739_dailyTemperatures;

import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] T) {
//        if(T.length==0){
//            return new int[]{};
//        }
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.empty() && !(T[i] < T[stack.peek()])) {
                stack.pop();
            }
            // 此时栈顶大于T[i]
            result[i] = !stack.empty() ? stack.peek() - i : 0;
            stack.push(i);
        }
        return result;
    }
}