package leetcode.editor.concurrent._1195_FizzBuzz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

class FizzBuzz {
    private final int n;
    Object lock = new Object();
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        try {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await();
                }
                if(cyclicBarrier.isBroken()){
                    cyclicBarrier.reset();
                }
            }
        } catch (BrokenBarrierException e) {
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    printBuzz.run();
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await();
                }
                if(cyclicBarrier.isBroken()){
                    cyclicBarrier.reset();
                }
            }
        } catch (BrokenBarrierException e) {
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    printFizzBuzz.run();
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await();
                }
                if(cyclicBarrier.isBroken()){
                    cyclicBarrier.reset();
                }
            }
        } catch (BrokenBarrierException e) {
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await();
                }
                if(cyclicBarrier.isBroken()){
                    cyclicBarrier.reset();
                }
            }
        } catch (BrokenBarrierException e) {
        }
    }
}