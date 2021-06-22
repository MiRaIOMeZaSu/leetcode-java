package leetcode.editor.offer._45_minNumber;

import java.util.Arrays;

class MyNum implements Comparable {
    String num;
    MyNum(String str) {
        num = str;
    }

    @Override
    public int compareTo(Object o) {
        MyNum other = (MyNum) o;
        String temp = num + other.num;
        return temp.compareTo(other.num + num);
    }
}

class Solution {
    public String minNumber(int[] nums) {
        int size = nums.length;
        MyNum[] arr = new MyNum[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new MyNum(String.valueOf(nums[i]));
        }
        Arrays.sort(arr);
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; i++) {
            ret.append(arr[i].num);
        }
        return ret.toString();
    }
}