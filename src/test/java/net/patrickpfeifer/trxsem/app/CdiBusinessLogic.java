package net.patrickpfeifer.trxsem.app;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;

@Dependent
public class CdiBusinessLogic {

//    @Transactional
    public void doMoreCdi() {
        // kein Rollback, so lange Methode nicht @Transactional
        throw new RuntimeException("edge case");
    }
}
