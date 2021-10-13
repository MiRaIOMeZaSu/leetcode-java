package leetcode.editor._47_permuteUnique;

import java.util.*;

public class Solution {
    List<List<Integer>> ret;
    List<List<Integer>> temp;
    HashSet<Integer> set;
    List<Integer> dup;
    HashSet<String> retSet;
    int length;

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 重点是不重复
        ret = new ArrayList<>();
        temp = new ArrayList<>();
        dup = new ArrayList<>();
        length = nums.length;
        set = new HashSet<>();
        retSet = new HashSet<>();
        // 统计
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                dup.add(nums[i]);
            }
        }
        // 回溯法
        List<Integer> list = new ArrayList<>();
        for (Integer i : set) {
            list.add(i);
        }
        for (int i = 0; i < set.size(); i++) {
            List<Integer> subList = new ArrayList<>(list);
            List<Integer> item = new ArrayList<>();
            item.add(subList.get(i));
            subList.remove(i);
            solve(subList, item);
        }
        // 开始插入
        if (dup.size() != 0) {
            for (List<Integer> l : temp) {
                insert(new ArrayList<>(dup), l);
            }
        }else{
            return temp;
        }
        // 先统计在回溯再插入
        return ret;
    }

    public void solve(List<Integer> list, List<Integer> item) {
        if (list.size() == 0) {
            temp.add(item);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> subList = new ArrayList<>(list);
            List<Integer> _item = new ArrayList<>(item);
            _item.add(subList.get(i));
            subList.remove(i);
            solve(subList, _item);
        }
    }

    public void insert(List<Integer> toInsert, List<Integer> item) {
        if (toInsert.size() == 0) {
            if (item.size() == length) {
                if (!retSet.contains(item.toString())) {
                    ret.add(item);
                    retSet.add(item.toString());
                }
            }
            return;
        }
        int head = toInsert.get(0);
        toInsert.remove(0);
        int pre = -11;
        for (int i = 0; i < item.size(); i++) {
            if (pre != head) {
                // 此时进行插入
                List<Integer> _item = new ArrayList<>();
                // i为插入者的下标
                for (int j = 0; j < item.size(); j++) {
                    if (i == j) {
                        _item.add(head);
                    }
                    _item.add(item.get(j));
                }
                insert(new ArrayList<>(toInsert), _item);
            }
            pre = item.get(i);
        }
        if (pre != head) {
            // 此时进行插入
            List<Integer> _item = new ArrayList<>(item);
            // i为插入者的下标
            _item.add(head);
            insert(new ArrayList<>(toInsert), _item);
        }
    }
}