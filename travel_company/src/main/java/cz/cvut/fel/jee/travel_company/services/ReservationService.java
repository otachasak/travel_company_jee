package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.*;
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

    public boolean createReservation(Long customerId, Long vacationId, Integer places) {
        try {
            Customer customer = customerDao.findCustomer(customerId);
            Vacation vacation = vacationDao.findVacation(vacationId);

            if((vacation.totalReservations()+ places) < vacation.getPlaces()) {
                Reservation r = new Reservation();
                r.setCustomer(customer);
                r.setVacation(vacation);
                r.setPlaces(places);
                r.setState(ReservationState.NEW);
                reservationDao.addReservation(r);
                vacation.getReservations().add(r);
                customer.getReservations().add(r);
                vacationDao.updateVacation(vacation);
                customerDao.updateCustomer(customer);
                return true;
            }
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to create reservation.", e);
        }
        return false;
    }

    public void deleteReservation(Long reservationId) {
        try {
            reservationDao.deleteReservation(reservationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete reservation.", e);
        }
    }


    public void markAsPayed(long reservationId) {
        try {
            Reservation reservation = reservationDao.findReservation(reservationId);
            reservation.setState(ReservationState.PAID);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to mark reservation as payed.", e);
        }
    }

    public void markAsNew(long reservationId) {
        try {
            Reservation reservation = reservationDao.findReservation(reservationId);
            reservation.setState(ReservationState.NEW);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to mark reservation as new.", e);
        }
    }

}
