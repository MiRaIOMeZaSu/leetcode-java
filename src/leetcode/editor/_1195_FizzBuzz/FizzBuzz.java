package leetcode.editor._1195_FizzBuzz;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private volatile int num;
    Lock lock;
    private volatile boolean mod3eq0 = false;
    private volatile boolean mod5eq0 = false;
    private static AtomicInteger step = new AtomicInteger(0);

    public FizzBuzz(int n) {
        this.n = n;
        this.num = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        if(num % 3 == 0){
            printFizz.run();
            mod3eq0 = true;
            step.incrementAndGet();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        if(num % 5 == 0){
            printBuzz.run();
            mod5eq0 = true;
            step.incrementAndGet();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(step.get()!=2){
        }
        if(mod3eq0&&mod5eq0){
            printFizzBuzz.run();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(step.get()!=2){
        }
        if(!mod3eq0&&!mod5eq0){
            printNumber.accept(num);
        }
    }
}