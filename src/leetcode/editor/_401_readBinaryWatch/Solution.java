package leetcode.editor._401_readBinaryWatch;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<String> ret = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        for (int i = 0; i <= num; i++) {
            solve(i, num - i);
        }
        return ret;
    }

    void solve(int h, int m) {
        if (h >= 4 || m >= 6) {
            return;
        }
        List<String> hP = new ArrayList<>();
        List<String> mP = new ArrayList<>();
        solveH(h, hP, 0, 1, 0);
        solveM(m, mP, 0, 1, 0);
        for (int i = 0; i < hP.size(); i++) {
            for (int j = 0; j < mP.size(); j++) {
                ret.add(hP.get(i) + ":" + mP.get(j));
            }
        }
    }

    void solveH(int h, List<String> hP, int k, int bit, int val) {
        if(val > 11){
            return;
        }
        if (k == h) {
            hP.add(String.valueOf(val));
            return;
        }
        if (bit > 8) {
            return;
        }
        solveH(h, hP, k + 1, (bit << 1), (val | bit));
        solveH(h, hP, k, (bit << 1), val);
    }

    void solveM(int m, List<String> mP, int k, int bit, int val) {
        if(val > 59){
            return;
        }
        if (k == m) {
            String temp = String.valueOf(val);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            mP.add(temp);
            return;
        }
        if (bit > 32) {
            return;
        }
        solveM(m, mP, k + 1, (bit << 1), (val | bit));
        solveM(m, mP, k, (bit << 1), val);
    }
}