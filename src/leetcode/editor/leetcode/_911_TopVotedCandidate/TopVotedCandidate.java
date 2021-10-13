package leetcode.editor._911_TopVotedCandidate;


import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

class TopVotedCandidate {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    Comparator<int[]> personComparator = (o1, o2) -> o2[1] - o1[1];

    public TopVotedCandidate(int[] persons, int[] times) {
        // 一共有5000票,为不同票数的选举人进行表格区分
        // 使用全局id作为判断最后投票的指标?
        // 使用什么方式存储实时票箱?
        // 全部都用treeMap(不需要
        HashMap<Integer, TreeSet<int[]>> voteBox = new HashMap<>();
        voteBox.put(1, new TreeSet<>(personComparator));
        int size = persons.length;
        int[] table = new int[size];
        int[][] idTable = new int[size][2];
        int currMax = 0;
        for (int i = 0; i < size; i++) {
            // 插入表格
            if (table[persons[i]] == 0) {
                idTable[persons[i]] = new int[]{persons[i], times[i]};
                voteBox.get(1).add(idTable[persons[i]]);
            } else {
                // 已经存在的候选人
                int nextPoint = table[persons[i]] + 1;
                if (!voteBox.containsKey(nextPoint)) {
                    voteBox.put(nextPoint, new TreeSet<>(personComparator));
                }
                voteBox.get(table[persons[i]]).remove(idTable[persons[i]]);
                idTable[persons[i]][1] = times[i];
                voteBox.get(nextPoint).add(idTable[persons[i]]);
            }
            table[persons[i]]++;
            currMax = Math.max(currMax, table[persons[i]]);
            // 此时寻找
            treeMap.put(times[i], voteBox.get(currMax).first()[0]);
        }

    }

    public int q(int t) {
        // 每次q的是指定的时间点
        // 由于q是依次调用,无法通过构造时计算得出结果
        // 使用treeMap存储所有投票时间点的当选人即可
        return treeMap.floorEntry(t).getValue();
    }

    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
                new int[]{0, 0, 0, 0, 1},
                new int[]{0, 6, 39, 52, 75}
        );
        System.out.println(topVotedCandidate.q(45));
        System.out.println(topVotedCandidate.q(49));
        System.out.println(topVotedCandidate.q(59));
        System.out.println(topVotedCandidate.q(68));
        System.out.println(topVotedCandidate.q(42));
        System.out.println(topVotedCandidate.q(37));
        System.out.println(topVotedCandidate.q(99));
        System.out.println(topVotedCandidate.q(26));
        System.out.println(topVotedCandidate.q(78));
        System.out.println(topVotedCandidate.q(43));
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */