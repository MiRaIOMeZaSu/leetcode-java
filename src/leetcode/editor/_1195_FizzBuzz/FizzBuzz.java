package leetcode.editor._1195_FizzBuzz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int pivot;
    private volatile int num;
    Lock lock;
    private volatile boolean mod3eq0 = false;
    private volatile boolean mod5eq0 = false;
    private Condition condition1, condition2, condition3, condition4, condition5;
    // private static AtomicInteger step = new AtomicInteger(1);
    private CyclicBarrier cb1 = new CyclicBarrier(2);

    public FizzBuzz(int n) {
        // 应该使用生产者-消费者模式解决
        // 生产者应该等待消费者,消费者也需要等待生产者!
        // 应该给4个函数设定顺序?
        // fizz与buzz应该相互等待
        this.n = n;
        this.num = n;
        this.pivot = n + 1;
        lock = new ReentrantLock();
        condition1 = lock.newCondition();
        condition2 = lock.newCondition();
        condition3 = lock.newCondition();
        condition4 = lock.newCondition();
        condition5 = lock.newCondition();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int i = 1;
        while (i < pivot) {
            if (i % 3 == 0) {
                mod3eq0 = true;
            }
            condition4.signal();
            lock.lock();
            try {
                condition3.await();
            } finally {
                lock.unlock();
            }
            // 此时两个均满足,
            if (mod3eq0 && !mod5eq0) {
                printFizz.run();
            }
            try {
                cb1.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            i++;
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) {
        int i = 1;
        while (i < pivot) {
            if (i % 5 == 0) {
                mod5eq0 = true;
            }
            condition5.signal();
            lock.lock();
            try {
                condition3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            try {
                cb1.await();
                if (mod3eq0 && mod5eq0) {
                    printBuzz.run();
                }
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz){
        int i = 1;
        while (i < pivot) {
            lock.lock();
            try {
                condition4.await();
                condition5.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            if (mod3eq0 && mod5eq0) {
                printFizzBuzz.run();
            } else {
                num = i;
                condition1.signal();
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition3.signal();
            i++;
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (num < pivot) {
            lock.lock();
            try {
                condition1.await();
            } finally {
                lock.unlock();
            }
            if (!mod3eq0 && !mod5eq0) {
                printNumber.accept(num);
            }
            condition2.signal();
        }
    }
}