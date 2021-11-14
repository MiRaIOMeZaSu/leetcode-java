package leetcode.editor.leetcode._409_longestPalindrome;

class Solution {
    public int longestPalindrome(String s) {
        // 统计数量即可,最多有一个字母数量为奇数
        int[] count = new int[26 * 2];
        char a = 'a';
        char A = 'A';
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                count[ch - A + 26]++;
            } else {
                count[ch - a]++;
            }
        }
        int ans = 0;
        int oddCount = 0;
        for (int i = 0; i < 26 * 2; i++) {
            ans += count[i];

            if (count[i] % 2 != 0) {
                oddCount++;
            }
        }
        if (oddCount > 0) {
            ans -= (oddCount - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth");
    }
}