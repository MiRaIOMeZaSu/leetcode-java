package leetcode.editor._1117_H2O;

import java.util.concurrent.atomic.AtomicInteger;

class H2O {
    //    private Object lock;
    AtomicInteger H = new AtomicInteger(0);
    AtomicInteger O = new AtomicInteger(0);
    AtomicInteger outPutH = new AtomicInteger(0);
    AtomicInteger outPutO = new AtomicInteger(0);

    public H2O() {
        // 实际上会保证HO的数量一直不满足,一旦满足会立刻转换成满足
        // 需要的是在满足时能够通知相应的释放
        // 但要求释放的熟练有限
        // 决定主从会使整个过程简单很多!
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (this) {
            H.incrementAndGet();
            this.notifyAll();
            while ((H.get() + outPutH.get() < 2 || O.get() < 1) &&
                    outPutH.get() >= 2 || outPutO.get() != 0) {
                this.wait();
            }
            outPutH.incrementAndGet();
            releaseHydrogen.run();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (this) {
            O.incrementAndGet();
            this.notifyAll();
            while ((H.get() + outPutH.get() < 2 || O.get() < 1) &&
                    outPutH.decrementAndGet() != 2 || outPutO.getAndIncrement() != 0){
                outPutH.incrementAndGet();
                this.wait();
            }
            O.decrementAndGet();
            H.decrementAndGet();
            H.decrementAndGet();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            outPutH.set(0);
            outPutO.set(0);
        }
    }
}