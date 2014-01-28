package cz.cvut.fel.jee.travel_company.dao.impl.base;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Base class for cz.cvut.fel.jee.travel_company.dao implementations
 */
public class BaseDaoImpl {

    @Inject
    @TravelCompanyEntityManager
    protected EntityManager em;
}
