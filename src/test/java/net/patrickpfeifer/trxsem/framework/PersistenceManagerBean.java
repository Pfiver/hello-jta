package net.patrickpfeifer.trxsem.framework;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.transaction.Status;
import jakarta.transaction.Synchronization;
import jakarta.transaction.TransactionSynchronizationRegistry;

@Stateless
public class PersistenceManagerBean {

    @Resource
    private TransactionSynchronizationRegistry trxSyncRegistry;

    public void saveSome() {

        trxSyncRegistry.registerInterposedSynchronization(new Synchronization() {

            @Override
            public void beforeCompletion() {
                System.out.println("saving data");

                // Wird hier eine RTE geworfen, kann ein Rollback im Applications-Code _nicht_ verhindert werden.
//                throw new RuntimeException("couldn't save");
            }

            @Override
            public void afterCompletion(int status) {
                if (status == Status.STATUS_COMMITTED) {
                    System.out.println("PersistenceManagerBean: data saved and transaction committed");
                }
            }
        });

        // würde normalen Rollback verursachen
//        throw new RuntimeException("missing data");
    }
}
