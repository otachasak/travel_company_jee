package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;
import cz.cvut.fel.jee.travel_company.dao.ReservationDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;
import java.util.List;
import java.util.logging.Level;

/**
 * @author vlada
 * @version: 31/01/14
 */
@Named
@Stateless
public class ReservationService extends BasicService {

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private VacationDao vacationDao;

    @Inject
    private CustomerDao customerDao;

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationDao.findAllReservations();
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }

    public List<ReservationDTO> getCustomersReservations(long customerId) {
        List<Reservation> reservations =  reservationDao.findAllReservationsForCustomer(customerId);
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }

    public List<ReservationDTO> getReservationsOfVacation(long vacationId) {
        List<Reservation> reservations =  reservationDao.findAllReservationsOfVacation(vacationId);
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }

    public List<ReservationDTO> getReservationsOfVacationForCustomer(long vacationId, long customerId) {
        List<Reservation> reservations =  reservationDao.findAllReservationsOfVacationForCustomer(vacationId, customerId);
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }

    public void createReservation(Long customerId, Long vacationId, Integer places) {
        try {
            Customer customer = customerDao.findCustomer(customerId);
            Vacation vacation = vacationDao.findVacation(vacationId);

            if(vacation.totalReservations() < (vacation.getPlaces() + places)) {
                Reservation r = new Reservation();
                r.setCustomer(customer);
                r.setVacation(vacation);
                r.setPlaces(places);
                reservationDao.addReservation(r);
                vacation.getReservations().add(r);
                customer.getReservations().add(r);
            }
        } catch(Exception e) {
            logger.log(Level.WARNING, "Unable to create reservation.", e);
        }
    }

    public void deleteReservation(Long reservationId) {
        try {
            reservationDao.deleteReservation(reservationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete reservation.", e);
        }
    }
}
