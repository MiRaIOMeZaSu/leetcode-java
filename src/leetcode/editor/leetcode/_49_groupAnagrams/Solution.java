package leetcode.editor._49_groupAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    List<List<String>> ret;
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        ret = new ArrayList<>();
        for (String i : strs) {
            int[] alp = new int[26];
            for (char c : i.toCharArray()) {
                alp[c - 'a'] += 1;
            }
            StringBuffer key = new StringBuffer();
            for (int s : alp) {
                key.append(s);
                key.append(',');
            }
            if(!map.containsKey(key.toString())){
                List<String> val = new ArrayList<>();
                val.add(i);
                map.put(key.toString(),val);
            }else{
                map.get(key.toString()).add(i);
            }
        }
        for(List<String> i:map.values()){
            ret.add(i);
        }
        return ret;
    }
}