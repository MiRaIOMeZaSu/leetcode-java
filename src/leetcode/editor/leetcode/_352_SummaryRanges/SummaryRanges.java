package leetcode.editor._352_SummaryRanges;

//给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
//
// 实现 SummaryRanges 类：
//
//
//
//
// SummaryRanges() 使用一个空数据流初始化对象。
// void addNum(int val) 向数据流中加入整数 val 。
// int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
//
//
//
//
// 示例：
//
//
//输入：
//["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals",
//"addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//[[], [1], [], [3], [], [7], [], [2], [], [6], []]
//输出：
//[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
// null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//解释：
//SummaryRanges summaryRanges = new SummaryRanges();
//summaryRanges.addNum(1);      // arr = [1]
//summaryRanges.getIntervals(); // 返回 [[1, 1]]
//summaryRanges.addNum(3);      // arr = [1, 3]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
//summaryRanges.addNum(7);      // arr = [1, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
//summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
//summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
//
//
//
//
// 提示：
//
//
// 0 <= val <= 10⁴
// 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次
//
//
//
//
//
//
// 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class SummaryRanges {
    Map<Integer, Integer> map = new TreeMap<>();
    int[] table = new int[10000 + 1];

    public SummaryRanges() {
        // 使用并查集
        // 最终往左找
        // 时刻存储所有首位置的坐标
        for (int i = 0; i <= 10000; i++) {
            table[i] = -1;
        }
    }

    public void addNum(int val) {
        if (table[val] != -1) {
            return;
        }
        // 先往右找
        byte flag = 0;
        if (table[val + 1] != -1) {
            // 成功往右找到
            flag++;
            Integer left = map.remove(val + 1);
            table[val + 1] = val;
            table[val] = val;
            map.put(val, left);
        }
        // 往左找
        if (val != 0) {
            if (table[val - 1] != -1) {
                // 成功往左找到
                flag++;
                System.out.println(val);
                setLeft(table[val - 1], map.getOrDefault(val, val), val);
                map.remove(val);
            }
        }
        if (flag == 0) {
            table[val] = val;
            map.put(val, val);
        }
    }

    private void setLeft(int target, int leftPos, int toSet) {
        while (table[target] != target) {
            target = table[target];
        }
        table[toSet] = target;
        table[leftPos] = target;
        map.put(target, leftPos);
    }

    public int[][] getIntervals() {
        int[][] ans = new int[map.size()][2];
        int index = 0;
        Iterator<Map.Entry<Integer, Integer>> integerIterator = map.entrySet().iterator();
        while (integerIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = integerIterator.next();
            ans[index][0] = entry.getKey();
            ans[index][1] = entry.getValue();
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(62);
        summaryRanges.getIntervals();
        summaryRanges.addNum(61);
        summaryRanges.getIntervals();
        summaryRanges.addNum(60);
        summaryRanges.getIntervals();
        summaryRanges.addNum(61);
        summaryRanges.getIntervals();
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
//leetcode submit region end(Prohibit modification and deletion)
