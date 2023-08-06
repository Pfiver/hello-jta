package net.patrickpfeifer.trxsem;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.TransactionSynchronizationRegistry;
import net.patrickpfeifer.trxsem.app.FacadeBean;
import net.patrickpfeifer.trxsem.util.TrxSyncMonitor;

@Stateless
public class TestBean {

    @EJB
    private FacadeBean facadeBean;

    @Resource
    private TransactionManager trxMgr;

    @Resource
    private TransactionSynchronizationRegistry trxSyncRegistry;

    public void runTest() throws Exception {

        System.out.println("tx key: " + trxSyncRegistry.getTransactionKey());

        // beides äquivalent
        trxMgr.getTransaction().registerSynchronization(TrxSyncMonitor.INSTANCE);
//        trxSyncRegistry.registerInterposedSynchronization(TrxSyncMonitor.INSTANCE);

        try {
            facadeBean.doSome();
        }
        catch (Exception e) {
            System.out.println("TestBean: caught exception: " + e);
        }
    }
}
