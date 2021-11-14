package leetcode.editor.concurrent._1117_H2O;

import java.util.concurrent.atomic.AtomicInteger;

class H2O {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (atomicInteger) {
            while (true) {
                if (atomicInteger.get() < 2) {
                    atomicInteger.incrementAndGet();
                    releaseHydrogen.run();
                    atomicInteger.notifyAll();
                    break;
                } else {
                    atomicInteger.wait();
                }
            }
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (atomicInteger) {
            while (atomicInteger.get() < 2){
                atomicInteger.wait();
            }
            releaseOxygen.run();
            atomicInteger.set(0);
            atomicInteger.notifyAll();
        }
    }
}