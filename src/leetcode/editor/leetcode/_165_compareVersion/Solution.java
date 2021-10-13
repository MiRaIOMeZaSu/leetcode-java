package leetcode.editor._165_compareVersion;

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] strings1 = version1.split("\\.");
        String[] strings2 = version2.split("\\.");
        int size = Math.min(strings1.length, strings2.length);
        int Lsize;
        String[] Lstrings;
        int flag;
        if (strings1.length > strings2.length) {
            Lsize = strings1.length;
            Lstrings = strings1;
            flag = 1;
        } else {
            Lsize = strings2.length;
            Lstrings = strings2;
            flag = -1;
        }
        for (int i = 0; i < size; i++) {
            int temp1 = Integer.parseInt(strings1[i]);
            int temp2 = Integer.parseInt(strings2[i]);
            if (temp1 == temp2) {
                continue;
            } else if (temp1 > temp2) {
                return 1;
            }
            return -1;
        }
        for (int i = size; i < Lsize; i++) {
            if (Integer.parseInt(Lstrings[i]) != 0) {
                return flag;
            }
        }
        return 0;
    }
}