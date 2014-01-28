package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: vlada
 * Date: 28/01/14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class VacationDaoImplTest {

    @Inject
    VacationDao vacationDao;


    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
                ;//.addPackages(true, VacationDaoImpl.class.getPackage()).addClass(VacationDaoImpl.class);
    }

    @Test
    public void testFindAllVacations() throws Exception {

    }
}
