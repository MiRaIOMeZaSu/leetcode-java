package leetcode.editor._1115_FooBar;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    Semaphore semaphore1,semaphore2;
    public FooBar(int n) {
        this.n = n;
        semaphore1 = new Semaphore(0);
        semaphore2 = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore2.release();
            semaphore1.acquire();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore1.release();
        }
    }
}