package leetcode.editor._1195_FizzBuzz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private volatile int m = 1;
    private volatile int flag = 0;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (m <= n) {
            while (flag != 1 && m <= n) {
                Thread.yield();
            }
            if (m > n) {
                return;
            }
            if (m % 3 == 0) {
                printFizz.run();
                m++;
                flag = 0;
            } else {
                flag = 2;
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (m <= n) {
            while (flag != 2 && m <= n) {
                Thread.yield();
            }
            if (m > n) {
                return;
            }
            if (m % 5 == 0) {
                printBuzz.run();
                m++;
                flag = 0;
            } else {
                flag = 3;
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (m <= n) {
            while (flag != 0 && m <= n) {
                Thread.yield();
            }
            if (m > n) {
                return;
            }
            if (m % 3 == 0 && m % 5 == 0) {
                printFizzBuzz.run();
                m++;
                flag = 0;
            } else {
                flag = 1;
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (m <= n) {
            while (flag != 3 && m <= n) {
                Thread.yield();
            }
            if (m > n) {
                return;
            }
            printNumber.accept(m);
            m++;
            flag = 0;
        }
    }
}