package leetcode.editor._981_TimeMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {
    Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        // 使用搜索树
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<Integer, String>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeMap<Integer,String> treeMap = map.get(key);
        String temp = treeMap.get(timestamp);
        if(temp!=null){
            return temp;
        }
        Map.Entry<Integer,String> entry = treeMap.lowerEntry(timestamp);
        if (entry == null) {
            return "";
        }
        return entry.getValue();
    }

    public static void main(String[] args) {
        TimeMap obj = new TimeMap();
        obj.set("foo", "boo", 1);
        String param_2 = obj.get("foo", 1);
        param_2 = obj.get("foo", 3);
        obj.set("foo", "boo2", 4);
        param_2 = obj.get("foo", 4);
        param_2 = obj.get("foo", 5);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */