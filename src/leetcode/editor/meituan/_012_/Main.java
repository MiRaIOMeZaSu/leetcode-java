package leetcode.editor.meituan._012_;

/*
小美的书架上有很多书。小美是个爱读书的新时代好青年。
小团虽然也喜欢看书，但小团大多数时候都更喜欢来小美家蹭书读。
这就导致小美的书架上很多书都会被小团借走。
小美很烦这一点，就想出了一个招数，小美的书架是一行一行的，他会对一些行加锁，这样小团就借不走了。
现在小团想要借书，请你帮忙看看小团能不能借到书，如果可以借到的话在哪一行书架上有这本书。
为了简单起见，每本书将用一个正整数进行编号，小美的书架一共有 N 行。

格式：


输入：
- 第一行三个正整数 M，N，Q，表示小美书架有 N 行编号 1 到 N ，书本编号从 1 到 M ，接下来有 Q 个操作
- 接下来 Q 行，每行是下列操作中的一种：
  1. x y : x 是书本的编号，y 是书架的行编号，代表小美将编号为 x 的书本放置到 y 行上。
  若该书本在小团手上则放置无效，若原来该书在书架上且原行上锁则放置无效，若该书被放置到一个锁了的行上则放置无效。
  2. y : y 是书架的行编号，代表小美将行编号为 y 的书架加锁，对已经上锁的书架行该操作无效。
  3. y : y 是书架的行编号，代表小美将行编号为 y 的书架锁去掉，对无锁的书架行该操作无效。
  4. x : x 是书本的编号，代表小团想借编号为 x 的书本，对该操作若可以借到输出一行正整数在哪一行，借不到输出一行 -1
  5. x : x 是书本的编号，代表小团还回来编号为 x 的书本。若该书本不在小团手上该操作无效。
输出：
- 对于每个操作 4 ，若可以借到输出一行正整数在哪一行，借不到输出一行 -1 。
示例：


输入：
     5 5 10
     1 1 4
     1 2 3
     1 3 1
     2 1
     4 1
     5 2
     4 3
     4 5
     3 1
     4 2
输出：
     4
     -1
     -1
     3
提示：

对于 30% 的数据有 N<=10, M<=10, Q<=20
对于 80% 的数据有 N<=1000, M<=1000, Q<=100000
对于 100% 的数据有 N<=10000, M<=10000, Q<=100000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/FvoBGh
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 只需要思考存储数据的方式
        // 1:添加书本
        // 2:书架上锁
        // 3:书架解锁
        // 4:借出书本
        // 5:还回书本
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bufferedReader.readLine().trim().split(" ");
        int M, N, Q;
        M = Integer.parseInt(temp[0]);
        N = Integer.parseInt(temp[1]);
        Q = Integer.parseInt(temp[2]);
        boolean[] rowsLocked = new boolean[N + 1];
        int[] bookBelongTo = new int[M + 1];
        boolean[] bookBeingRent = new boolean[M + 1];
        // 只输出操作4的反馈
        for (int i = 0; i < Q; i++) {
            String[] operation = bufferedReader.readLine().trim().split(" ");
            switch (operation[0]) {
                case "1":
                    int book = Integer.parseInt(operation[1]);
                    int row = Integer.parseInt(operation[2]);
                    if (bookBeingRent[book]) {
                        break;
                    }
                    if (rowsLocked[row]) {
                        break;
                    }
                    if (rowsLocked[bookBelongTo[book]]) {
                        break;
                    }
                    bookBelongTo[book] = row;
                    break;
                case "2":
                    rowsLocked[Integer.parseInt(operation[1])] = true;
                    break;
                case "3":
                    rowsLocked[Integer.parseInt(operation[1])] = false;
                    break;
                case "4":
                    // 借书
                    int bookToRent = Integer.parseInt(operation[1]);
                    if (bookBelongTo[bookToRent] == 0 || rowsLocked[bookBelongTo[bookToRent]]) {
                        // 书没放上去,或放上去的行上锁了
                        System.out.println(-1);
                        break;
                    }
                    bookBeingRent[bookToRent] = true;
                    System.out.println(bookBelongTo[bookToRent]);
                    bookBelongTo[bookToRent] = 0;
                    break;
                case "5":
                    bookBeingRent[Integer.parseInt(operation[1])] = false;
                    break;
                default:
                    break;
            }
        }
    }
}
