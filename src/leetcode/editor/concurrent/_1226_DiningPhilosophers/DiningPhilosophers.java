package leetcode.editor.concurrent._1226_DiningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {
    Lock lock = new ReentrantLock();
    volatile boolean[] arr;

    public DiningPhilosophers() {
        arr = new boolean[5];
    }

    // call the run() method of any runnable to execute it12s code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 使用信号量
        // 避免死锁(相互等待,必须在获取前判断,若无法全部获取则释放
        // 一共五把叉子
        int next = (philosopher + 1) % 5;
        while (true) {
            lock.lock();
            try {
                if (!arr[philosopher]) {
                    arr[philosopher] = true;
                } else {
                    continue;
                }
            } finally {
                lock.unlock();
            }
            pickLeftFork.run();
            boolean shouldGiveUp = false;
            lock.lock();
            try {
                if (!arr[next]) {
                    arr[next] = true;
                } else {
                    shouldGiveUp = true;
                }
            } finally {
                lock.unlock();
            }
            if (shouldGiveUp) {
                putLeftFork.run();
                arr[philosopher] = false;
                continue;
            }
            break;
        }
        pickRightFork.run();
        eat.run();
        putRightFork.run();
        arr[next] = false;
        putRightFork.run();
        arr[philosopher] = false;
    }
}