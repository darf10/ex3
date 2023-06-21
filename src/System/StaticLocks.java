package System;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class StaticLocks extends ReentrantLock {
    private static AtomicInteger locksCount = new AtomicInteger(0);
    private static final int maxCount = 2;

    private StaticLocks() {
        locksCount.incrementAndGet();
    }
    public static StaticLocks getInstance(){
        if(maxCount - locksCount.addAndGet(0) > 0)
            return new StaticLocks();
        return null;
    }

    public void unlock() {
        super.unlock();
        locksCount.decrementAndGet();
    }

    public AtomicInteger getLockCount() {
        return locksCount;
    }
}

