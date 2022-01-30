package leetcode.editor.contest._5992_maximumGood;

class Solution {
    int size;

    public int maximumGood(int[][] statements) {
        int ans = 0;
        size = statements.length;
        // 返回好人的最大数量
        // 返回最大的只说真话的人
        // 图?
        // 每次只认定一个人是好人，他会给出所有人的答案，然后从逻辑上判断其对错，跳过坏人的答案
        // 重点来了，除了各自对自己的评价和无评价外，其他的评价都应该相同！
        // 最多15个人
        for (int i = 0; i < size; i++) {
            int[] shouldBe = new int[size];
            System.arraycopy(statements[i], 0, shouldBe, 0, size);
            shouldBe[i] = 3;
            boolean repeat;
            boolean right = true;
            subStat:
            do {
                repeat = false;
                for (int j = 0; j < size; j++) {
                    if (shouldBe[j] == 1) {
                        for (int k = 0; k < size; k++) {
                            if (statements[j][k] == 2) {
                                continue;
                            } else if (statements[j][k] == 1) {
                                // 认为是好人
                                if (shouldBe[k] == 2) {
                                    repeat = true;
                                    shouldBe[k] = 1;
                                } else if (shouldBe[k] == 0) {
                                    right = false;
                                    break subStat;
                                }
                            } else if (statements[j][k] == 0) {
                                // 认为是坏人
                                if (shouldBe[k] == 2) {
                                    shouldBe[k] = 0;
                                } else if (shouldBe[k] != 0) {
                                    right = false;
                                    break subStat;
                                }
                            }
                        }
                        // 使用3来标记已完成
                        shouldBe[j] = 3;
                    }
                }
            } while (repeat);
            if (right) {
                int tempAns = 0;
                for (int j = 0; j < size; j++) {
                    if (shouldBe[j] == 3) {
                        tempAns += 1;
                    }
                }
                for (int j = 0; j < size; j++) {
                    if (shouldBe[j] == 2) {
                        boolean temp = true;
                        for (int k = 0; k < size; k++) {
                            if (statements[j][k] != 2) {
                                if (statements[j][k] != 1 && shouldBe[k] == 0) {
                                    temp = false;
                                    break;
                                }
                                if (statements[j][k] == 0 && shouldBe[k] != 0) {
                                    temp = false;
                                    break;
                                }
                            }
                        }
                        if(temp){
                            tempAns+=1;
                        }
                    }
                }
                ans = Math.max(ans, tempAns);
            }
            // 如何尝试仍然为2的情况?
            // 使用回溯法
        }
        for (int i = 0; i < size; i++) {
            
        }
        return ans;
    }

    private int solve(int[] shouldBe, int[][] statements, int curr, int index) {
        int[] _shouldBe = new int[size];
        // 将index的结果作用到_shouldBe上
        for (int k = 0; k < size; k++) {
            if (statements[index][k] == 2) {
                continue;
            } else if (statements[index][k] == 1) {
                // 认为是好人
                if (_shouldBe[k] == 2) {
                    _shouldBe[k] = 1;
                } else if (_shouldBe[k] == 0) {
                    return curr;
                }
            } else if (statements[index][k] == 0) {
                // 认为是坏人
                if (_shouldBe[k] == 2) {
                    _shouldBe[k] = 0;
                } else if (_shouldBe[k] != 0) {
                    return curr;
                }
            }
        }
        System.arraycopy(shouldBe, 0, _shouldBe, 0, size);
        for (int i = index + 1; i < size; i++) {
            if (_shouldBe[i] == 2) {
                int ans = solve(_shouldBe, statements, curr + 1, i);
                return Math.max(ans, solve(shouldBe, statements, curr + 1, i));
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumGood(new int[][]{
                {2,1,1,2,1,2,1,2,1,2},{2,2,2,2,1,2,1,2,2,1},{1,0,2,0,2,2,2,0,1,0},{0,0,0,2,2,2,0,0,2,0},{0,2,0,1,2,2,0,0,1,1},{1,2,0,2,0,2,0,1,2,1},{1,2,0,0,1,1,2,1,1,2},{2,0,0,2,0,0,2,2,1,1},{2,0,0,0,0,2,1,0,2,0},{2,2,0,0,2,0,0,0,0,2}
        }));
    }
}