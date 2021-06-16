package leetcode.editor._1116_ZeroEvenOdd;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private CyclicBarrier cb1 = new CyclicBarrier(3);
    private CyclicBarrier cb2 = new CyclicBarrier(2);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            printNumber.accept(0);
            try {
                cb1.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            try {
                cb2.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                cb1.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            if (i % 2 == 0) {
                printNumber.accept(i);
                try {
                    cb2.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                cb1.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            if (i % 2 == 1) {
                printNumber.accept(i);
                try {
                    cb2.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}