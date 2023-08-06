package net.patrickpfeifer.trxsem.app;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;

import static jakarta.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
public class EjbBusinessLogic {

    @TransactionAttribute(NOT_SUPPORTED)
    public void doMoreEjb() {
        // kein Rollback, weil Transaktion suspended wird
        // wird aber geloggt -> zum Testen, Log Level anpassen in TransactionSemanticsTest
        throw new RuntimeException("another edge case");
    }

    public void safelyDoMoreTransactionalEjb() {

        try {
            throw new RuntimeException("programming error");
        }
        catch (Exception e) {
            System.out.println("EjbBusinessLogic: caught exception: " + e);
        }
    }
}
