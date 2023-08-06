package net.patrickpfeifer.trxsem.app;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import net.patrickpfeifer.trxsem.framework.PersistenceManagerBean;

@Stateless
public class FacadeBean {

    @Inject
    private CdiBusinessLogic cdiBean;

    @Inject
    private EjbBusinessLogic ejbBean;

    @EJB
    private PersistenceManagerBean pmBean;

    public void doSome() {

        try {
            cdiBean.doMoreCdi();
        }
        catch (Exception e) {
            System.out.println("FacadeBean: caught exception from cdiBean: " + e);
        }

        try {
            ejbBean.doMoreEjb();
        }
        catch (Exception e) {
            System.out.println("FacadeBean: caught exception from ejbBean: " + e);
        }

        ejbBean.safelyDoMoreTransactionalEjb();

        try {
            pmBean.saveSome();
        }
        catch (Exception e) {
            System.out.println("FacadeBean: caught exception from pmBean: " + e);
        }
    }
}
