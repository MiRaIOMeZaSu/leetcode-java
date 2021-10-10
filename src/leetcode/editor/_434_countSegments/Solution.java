package leetcode.editor._434_countSegments;

class Solution {
    public int countSegments(String s) {
        int size = s.length();
        int index = 0;
        int ans = 0;
        while (index < size) {
            while (index < size && s.charAt(index) == ' ') {
                index++;
            }
            if(index >= size){
                break;
            }
            while (index < size && s.charAt(index) != ' ') {
                index++;
            }
            ans++;
        }
        return ans;
    }
}