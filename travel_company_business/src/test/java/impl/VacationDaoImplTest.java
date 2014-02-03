package impl;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.DestinationDaoImpl;
import cz.cvut.fel.jee.travel_company.dao.impl.VacationDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.transaction.UserTransaction;
import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.fail;

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

    @Inject
    DestinationDao destinationDao;

    @Inject
    UserTransaction utx;

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
                .addPackages(true, VacationDaoImpl.class.getPackage())
                .addPackages(true, VacationDao.class.getPackage())
                .addPackages(true, DestinationDaoImpl.class.getPackage())
                .addPackages(true, DestinationDao.class.getPackage())

                .addPackages(true, VacationDTO.class.getPackage())
                .addPackages(true, Vacation.class.getPackage())
                .addPackages(true, Destination.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
;
    }

    @Test
    public void testFindAllVacations() throws Exception {
        Destination d = new Destination();

        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        Date d1 = new Date(c.getTime().getTime());

        String name =" XXX " + System.currentTimeMillis();
        d.setName(name);
        destinationDao.addDestination(d);

        Vacation v = new Vacation();
        v.setDestination(d);
        v.setStartDate(d1);
        v.setEndDate(new Date(20));
        vacationDao.addVacation(v);

        List<Vacation> lst = vacationDao.findAllVacations();
        assertTrue(lst.size() >= 1);

        Vacation res = null;
        for(Vacation vacation : lst) {
            if(vacation.getDestination().getName().equals(name)) {
                res = vacation;
                break;
            }
        }

        assertTrue(res != null);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(new java.util.Date(res.getStartDate().getTime()));


        assertEquals(c.get(Calendar.DATE), c2.get(Calendar.DATE));
        assertEquals(c.get(Calendar.MONTH), c2.get(Calendar.MONTH));
    }

    @Test
    public void testVacationModification() throws Exception {
        Destination d = new Destination();

        d.setName("XXX");
        destinationDao.addDestination(d);

        Vacation v = new Vacation();
        v.setDestination(d);
        v.setStartDate(new Date(0));
        v.setEndDate(new Date(20));
        vacationDao.addVacation(v);

        assertNotNull(d.getId());
        assertNotNull(v.getId());

        d.setName("YYY");
        destinationDao.updateDatination(d);

        Vacation v2 = vacationDao.findVacation(v.getId());
        assertEquals("YYY", v2.getDestination().getName());
    }


    @Test(expected = EntityNotFoundException.class)
    public void testDelete() throws Exception {
        Destination d = new Destination();

        d.setName("XXX");
        destinationDao.addDestination(d);

        Vacation v = new Vacation();
        v.setDestination(d);
        v.setStartDate(new Date(0));
        v.setEndDate(new Date(20));
        vacationDao.addVacation(v);

        assertNotNull(d.getId());
        assertNotNull(v.getId());

        Vacation v2 = vacationDao.findVacation(v.getId());
        assertNotNull(v2);

        vacationDao.deleteVacation(v.getId());

        vacationDao.findVacation(v.getId());

        fail();
    }

    @Before
    public void setUp() throws Exception {
        utx.begin();
    }

    @After
    public void cleanUp() throws Exception {
        utx.rollback();
    }
}
