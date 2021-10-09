package leetcode.editor.acmcoder.meituan2020BE_2;

//为了激励更多的用户发表点评，大众点评在近期组织了一次促评排行榜的活动。
// 在活动期间, 写评价数排名靠前的用户将获得对应的奖励，奖励分为积分、优惠券、贡献值三类。
// 为了让活动更有趣味性，不同排名的用户将获得不同类型的激励。
// 同时，为了保证激励发放效率，这三类激励会并行发放。
//
//        我们把问题简单描述一下，假定有一个激励发放的类，如下所示：
//
//class ReviewEncourage {
//
//    public ReviewEncourage(int n) { ... }      // 构造函数,n为中奖用户数
//
///*
//
//    PrizePool类仅有一个send方法，实现如下：
//
//    public class PrizePool {
//
//        public void send(String input) {
//
//            System.out.print(input);
//
//        }
//
//    }
//
//  */
//
//    public void bonus(PrizePool prizePool) { ... }  // 仅能打印A，表示发放积分
//
//    public void coupon(PrizePool prizePool) { ... }  // 仅能打印B，表示发放优惠券
//
//    public void contribution(PrizePool prizePool) { ... }  // 仅能打印C，表示发放贡献值
//
//}
//
//同一个ReviewEncourage实例将会传递给三个不同的线程用于激励发放：
//
//        1.积分发放线程将会调用bonus方法发放积分
//
//        2.优惠券发放线程将会调用coupon方法发放优惠券
//
//        3.贡献值发放线程将会调用contribution方法发放贡献值
//
//        要求依次对不同排位的用户发放不同类型的奖励，其中排位为奇数的用户发放积分，排位为偶数的用户交替发放优惠券和贡献值。
//
//        例如一共5个中奖用户，要求对第一个用户发放积分，第二个用户发放优惠券，第三个用户发放积分，第四个用户发放贡献值，第五个用户发放积分，即输出ABACA
//
//        要求补全以上代码，输出指定字符串序列

/*
*输入

获奖用户数n，n大于0，小于等于100

输出

由A、B、C组成的字符串，长度为n，奇数位为A，偶数位交替为B和C

提示: 三个激励发放线程异步执行，不保证执行顺序

题目限制

时间限制：C/C++语言 1000 MS；其他语言 3000 MS
内存限制：C/C++语言 65536KB；其他语言 589824KB

样例输入

1
样例输出

A
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


class Main {
    // 并发问题
    // 输出: 长度为n，奇数位为A，偶数位交替为B和C
    // 使用原子类
    //
    int n;
    AtomicInteger atomicInteger = new AtomicInteger(1);
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public Main(int n) {
        // 初始为1
        this.n = n;
    }      // 构造函数,n为中奖用户数

    public void bonus(PrizePool prizePool) throws InterruptedException {
        if (atomicInteger.getAndIncrement() % 2 != 0) {
            prizePool.send("A");
        }
    }  // 仅能打印A，表示发放积分

    public void coupon(PrizePool prizePool) throws InterruptedException {
        if (atomicInteger.getAndIncrement() % 2 == 0 && !atomicBoolean.getAndSet(true)) {
            prizePool.send("B");
        }
    }  // 仅能打印B，表示发放优惠券

    public void contribution(PrizePool prizePool) throws InterruptedException {
        if (atomicInteger.getAndIncrement() % 2 == 0 && atomicBoolean.getAndSet(false)) {
            prizePool.send("C");
        }
    }  // 仅能打印C，表示发放贡献值

    //校验代码，无需考生实现
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        String number = scanner.nextLine();
        final PrizePool prizePool = new PrizePool();
        final Main reviewEncourage = new Main(Integer.valueOf(number));
        Thread bonusThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reviewEncourage.bonus(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread couponThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reviewEncourage.coupon(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread contributionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reviewEncourage.contribution(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bonusThread.start();
        couponThread.start();
        contributionThread.start();
    }

    //    PrizePool类仅有一个send方法，实现如下：
    public static class PrizePool {

        void send(String input) {
            System.out.print(input);
        }

    }
}
