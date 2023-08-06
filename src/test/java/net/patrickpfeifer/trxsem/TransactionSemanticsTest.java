package net.patrickpfeifer.trxsem;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import net.patrickpfeifer.trxsem.util.TrxStatusUtil;
import net.patrickpfeifer.trxsem.util.TrxSyncMonitor;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static jakarta.transaction.Status.STATUS_COMMITTED;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TransactionSemanticsTest {

    @EJB
    private TestBean testBean;

    @Test
    void testRuntimeExceptionEffects() {

        Logger.getLogger("").setLevel(Level.OFF);

        try (EJBContainer ejbContainer = EJBContainer.createEJBContainer()) {

            ejbContainer.getContext().bind("inject", this);

            testBean.runTest();
        }
        catch (Exception e) {
            System.out.println("TransactionSemanticsTest: caught exception: " + e);
        }

//        assertTrue(TrxSyncMonitor.INSTANCE.isBeforeCompletionCalled(), "beforeCompletion() was never called");

        if (TrxSyncMonitor.INSTANCE.getAfterCompletionStatus() != STATUS_COMMITTED) {
            fail("transaction status was %s after completion.".formatted(TrxStatusUtil.getStatusName(TrxSyncMonitor.INSTANCE.getAfterCompletionStatus())));
        }
    }
}
