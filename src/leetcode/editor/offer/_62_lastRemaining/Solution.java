package leetcode.editor.offer._62_lastRemaining;

class Index {
    int max;
    int index;

    Index(int num, int proto) {
        max = num;
        index = proto;
    }

    public int increase(int toAdd) {
        int temp = index + toAdd;
        if (temp >= max) {
            index = temp % max;
        } else {
            index = temp;
        }
        return temp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int num) {
        this.index = num;
    }
}

class Solution {
    public int lastRemaining(int n, int m) {
        // 构成一个环链表
        // 使用线段树进行模拟
        // m会不断减小
        // 构成一个环链表
        // 直接计算出结果?
        // 一共要删除n -1个数
        boolean[] table = new boolean[n];
        int[] next = new int[n];
        int[] pres = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = i + 1;
            pres[i] = i - 1;
        }
        next[n - 1] = 0;
        pres[0] = n - 1;
        Index index = new Index(n, 0);
        for (int i = 0; i < n - 1; i++) {
            int temp = index.increase(m - 1);
            if (temp >= n) {
                m += 1;
            }
            table[index.getIndex()] = true;
            System.out.println(index.getIndex());
            next[pres[index.getIndex()]] = next[index.getIndex()];
            pres[next[index.getIndex()]] = pres[index.getIndex()];
            index.setIndex(next[index.getIndex()]);
        }
        for (int i = 0; i < n; i++) {
            if (!table[i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lastRemaining(5, 3));
    }
}