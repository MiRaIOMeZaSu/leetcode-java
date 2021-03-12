package leetcode.editor._739_dailyTemperatures;

public class Main {
    public static void main(String[] args) {
        Solution solution =  new Solution();
        int[]  result = solution.dailyTemperatures(new int[]{89,62,70,58,47,47,46,76,100,70});
        for (int i:
             result) {
            System.out.println(i);
        }
    }
}
