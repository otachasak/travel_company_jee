package cz.cvut.fel.jee.travel_company.dao.impl.base;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Procured for Entity Manager
 * Note: This is really ugly, but I just want to try CDI Qualified Injection :-)
 */
public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "TravelCompanyPU")
    @TravelCompanyEntityManager
    private EntityManager em;
}
