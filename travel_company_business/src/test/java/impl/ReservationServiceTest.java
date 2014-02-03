package impl;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.CustomerDaoImpl;
import cz.cvut.fel.jee.travel_company.dao.impl.DestinationDaoImpl;
import cz.cvut.fel.jee.travel_company.dao.impl.VacationDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.*;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.ReservationService;
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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author vlada
 * @version: 01/02/14
 */
@RunWith(Arquillian.class)
public class ReservationServiceTest {

    private Destination destination;
    private Vacation vacation;
    private Customer customer;
    @Inject
    private VacationDao vacationDao;
    @Inject
    private CustomerDao customerDao;
    @Inject
    private DestinationDao destinationDao;
    @Inject
    private ReservationService reservationService;
    @Inject
    UserTransaction utx;
    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
                .addPackages(true, ReservationService.class.getPackage())
                .addPackages(true, VacationDaoImpl.class.getPackage())
                .addPackages(true, VacationDao.class.getPackage())
                .addPackages(true, DestinationDaoImpl.class.getPackage())
                .addPackages(true, DestinationDao.class.getPackage())
                .addPackages(true, CustomerDaoImpl.class.getPackage())
                .addPackages(true, CustomerDao.class.getPackage())

                .addPackages(true, VacationDTO.class.getPackage())
                .addPackages(true, Vacation.class.getPackage())
                .addPackages(true, Customer.class.getPackage())
                .addPackages(true, Destination.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                ;
    }


    @Test
    public void testMakeReservation() throws Exception {
        vacation = vacationDao.findVacation(vacation.getId());
        customer = customerDao.findCustomer(customer.getId());

        boolean reserved = reservationService.createReservation(customer.getId(), vacation.getId(), 2);
        assertTrue(reserved);
        assertTrue(vacation.getReservations().size() == 1);

        assertEquals(2, vacation.totalReservations());
        Reservation reservation = vacation.getReservations().iterator().next();
        assertEquals(customer, reservation.getCustomer());
        assertEquals(vacation, reservation.getVacation());
        assertEquals(Integer.valueOf(2), reservation.getPlaces());
    }

    @Test
    public void testMakeImpossibleReservation() throws Exception {
        vacation = vacationDao.findVacation(vacation.getId());
        customer = customerDao.findCustomer(customer.getId());

        assertEquals(0, vacation.totalReservations());
        assertEquals(Integer.valueOf(5), vacation.getPlaces());

        boolean reserved = reservationService.createReservation(customer.getId(), vacation.getId(), 20);
        assertFalse(reserved);
    }

    @Before
    public void initData() throws Exception {
        utx.begin();
        destination = new Destination();
        destination.setName("Prague");
        destinationDao.addDestination(destination);

        customer = new Customer();
        customer.setName("name");
        customer.setEmail("email");
        customerDao.addCustomer(customer);

        vacation = new Vacation();
        vacation.setPlaces(5);
        vacation.setStartDate(new Date(1000));
        vacation.setEndDate(new Date(2000));
        vacation.setPrice(BigDecimal.TEN);
        vacation.setDestination(destination);
        vacationDao.addVacation(vacation);

        utx.commit();
        utx.begin();
    }

    @After
    public void cleanUp() throws Exception {
        utx.rollback();
    }
}
