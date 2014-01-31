package cz.cvut.fel.jee.travel_company.services;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author vlada
 * @version: 31/01/14
 */
public abstract class BasicService implements Serializable {
    protected Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
}
