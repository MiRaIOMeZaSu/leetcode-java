package leetcode.editor._946_validateStackSequences;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int size = pushed.length;
        if(size==0){
            return true;
        }
        Deque<Integer> stack = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        int iPush = 0;
        int iPop = 0;
        while (iPop < size) {
            if(stack.isEmpty()){
                if(iPush >= size){
                    return false;
                }
                stack.push(pushed[iPush]);
                visit.add(pushed[iPush]);
                iPush++;
            }
            while (iPush < size && stack.peek() != popped[iPop]) {
                if (visit.contains(popped[iPop])) {
                    return false;
                }
                stack.push(pushed[iPush]);
                visit.add(pushed[iPush]);
                iPush++;
            }
            if (stack.peek() != popped[iPop]) {
                return false;
            }
            stack.pop();
            iPop++;
        }
        return true;
    }
}