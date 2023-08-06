package net.patrickpfeifer.trxsem.util;

import jakarta.transaction.Synchronization;

public class TrxSyncMonitor implements Synchronization {

    public static final TrxSyncMonitor INSTANCE = new TrxSyncMonitor();

    private boolean beforeCompletionCalled;
    private Integer afterCompletionStatus;

    @Override
    public void beforeCompletion() {
        synchronized (this) {
            if (beforeCompletionCalled) {
                throw new IllegalStateException("beforeCompletion() already called");
            }
            beforeCompletionCalled = true;
        }
    }

    @Override
    public void afterCompletion(int status) {
        if (afterCompletionStatus != null) {
            throw new IllegalStateException("afterCompletion() already called");
        }
        afterCompletionStatus = status;
    }

    public boolean isBeforeCompletionCalled() {
        return beforeCompletionCalled;
    }

    public Integer getAfterCompletionStatus() {
        return afterCompletionStatus;
    }
}
