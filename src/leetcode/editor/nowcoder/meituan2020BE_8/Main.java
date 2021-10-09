package leetcode.editor.nowcoder.meituan2020BE_8;

/*
2110年美团外卖火星第3000号配送站点有26名骑手，分别以大写字母A-Z命名，因此可以称呼这些骑手为黄家骑士特工A，黄家骑士特工B…黄家骑士特工Z，
某美团黑珍珠餐厅的外卖流水线上会顺序产出一组包裹，美团配送调度引擎已经将包裹分配到骑手，并在包裹上粘贴好骑手名称，
如RETTEBTAE代表一组流水线包裹共9个，同时分配给了名字为A B E R T的5名骑手。请在不打乱流水线产出顺序的情况下，
把这组包裹划分为尽可能多的片段，同一个骑手只会出现在其中的一个片段，返回一个表示每个包裹片段的长度的列表。


输入描述:
输入数据只有一行，为一个字符串(不包含引号)，长度不超过1000，只包含大写字母'A'到'Z'，字符之间无空格。



输出描述:
输出每个分割成片段的包裹组的长度，每个长度之间通过空格隔开

示例1
输入
MPMPCPMCMDEFEGDEHINHKLIN
输出
9 7 8
说明
划分结果为MPMPCPMCM,DEFEGDE,HINHKLIN。

每个骑手最多出现在一个片段中。

像MPMPCPMCMDEFEGDE,HINHKLIN的划分是错误的，因为划分的片段数较少。
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 同 leetcode763 : partition-labels
public class Main {
    public static List<Integer> partitionLabels(String s) {
        // 直接遍历
        int size = s.length();
        char a = 'A';
        int[] lastIndex = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < size; i++) {
            lastIndex[chars[i] - a] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int left, right;
        int index = 0;
        while (index < size) {
            left = index;
            right = lastIndex[chars[index] - a];
            while (index < right) {
                index++;
                right = Math.max(right, lastIndex[chars[index] - a]);
            }
            // 此时index==right
            // 此时可以开始分割
            ans.add(right - left + 1);
            index++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        List<Integer> list = Main.partitionLabels(input);
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.print(list.get(list.size() - 1));
    }
}
