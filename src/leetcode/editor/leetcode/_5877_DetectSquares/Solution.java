package leetcode.editor._5877_DetectSquares;

class DetectSquares {
    int[][] board = new int[1000 + 1][1001];

    public DetectSquares() {
        // 大小有限,直接使用数组?
        // 只需存放数量
    }

    public void add(int[] point) {
        board[point[0]][point[1]]++;
    }

    public int count(int[] point) {
        int x = point[0];
        int ans = 0;
        for (int i = 0; i <= 1000; i++) {
            if (i == point[1]) {
                continue;
            }
            if (board[x][i] > 0) {
                int a = board[x][i];
                int b, c;
                int length = Math.abs(i - point[1]);
                // 向左
                if (x >= length) {
                    b = board[x - length][point[1]];
                    c = board[x - length][i];
                    ans += a * b * c;
                }
                // 向右
                if (x + length <= 1000) {
                    b = board[x + length][point[1]];
                    c = board[x + length][i];
                    ans += a * b * c;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        detectSquares.count(new int[]{11, 10}); // 返回 1 。你可以选择：
        //   - 第一个，第二个，和第三个点
        detectSquares.count(new int[]{14, 8});  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
        detectSquares.add(new int[]{11, 2});    // 允许添加重复的点。
        detectSquares.count(new int[]{11, 10}); // 返回 2 。你可以选择：
        //   - 第一个，第二个，和第三个点
        //   - 第一个，第三个，和第四个点
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */