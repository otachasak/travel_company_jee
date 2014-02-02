package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.*;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;
import cz.cvut.fel.jee.travel_company.dao.ReservationDao;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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

    @Resource
    SessionContext ejbContext;
    @Inject
    private ReservationDao reservationDao;
    @Inject
    private VacationDao vacationDao;
    @Inject
    private CustomerDao customerDao;

    @RolesAllowed({"root", "customer"})
    public List<ReservationDTO> getAllReservations() {
        if (ejbContext.isCallerInRole("root")) {
            List<Reservation> reservations = reservationDao.findAllReservations();
            return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
        } else {
            try {
                Customer c = customerDao.findCustomerByName(ejbContext.getCallerPrincipal().getName());
                getCustomersReservations(c.getId());
            } catch (Exception e) {
                logger.log(Level.WARNING, "Unable to find reservations.", e);
            }
        }
        return null;
    }

    @RolesAllowed({"root", "customer"})
    public List<ReservationDTO> getCustomersReservations(long customerId) {
        if (ejbContext.isCallerInRole("root")) {
            List<Reservation> reservations = reservationDao.findAllReservationsForCustomer(customerId);
            return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
        } else {
            try {
                Customer c = customerDao.findCustomerByName(ejbContext.getCallerPrincipal().getName());
                if (c.getId() != customerId) {
                    throw new SecurityException(c.getName() + " is not allowed to see reservations of " + customerId);
                }
                List<Reservation> reservations = reservationDao.findAllReservationsForCustomer(customerId);
                return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
            } catch (EntityNotFoundException e) {
                logger.log(Level.WARNING, "Unable to find reservations.", e);
            }
        }
        return null;
    }

    @RolesAllowed({"root", "customer"})
    public List<ReservationDTO> getReservationsOfVacation(long vacationId) {
        if (ejbContext.isCallerInRole("root")) {
            List<Reservation> reservations = reservationDao.findAllReservationsOfVacation(vacationId);
            return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
        } else {
            try {
                Customer c = customerDao.findCustomerByName(ejbContext.getCallerPrincipal().getName());
                return getReservationsOfVacationForCustomer(vacationId, c.getId());
            } catch (EntityNotFoundException e) {
                logger.log(Level.WARNING, "Unable to find reservations.", e);
            }
        }
        return null;
    }

    @RolesAllowed({"root", "customer"})
    public List<ReservationDTO> getReservationsOfVacationForCustomer(long vacationId, long customerId) {
        if (!ejbContext.isCallerInRole("root")) {
            try {
                Customer c = customerDao.findCustomerByName(ejbContext.getCallerPrincipal().getName());
                if (c.getId() != customerId) {
                    throw new SecurityException(c.getName() + " is not allowed to see reservations of " + customerId);
                }
            } catch (EntityNotFoundException e) {
                logger.log(Level.WARNING, "Unable to find reservations.", e);
            }
        }
        List<Reservation> reservations = reservationDao.findAllReservationsOfVacationForCustomer(vacationId, customerId);
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }

    @RolesAllowed({"root", "customer"})
    public boolean createReservation(Long customerId, Long vacationId, Integer places) {
        try {
            Customer customer = customerDao.findCustomer(customerId);
            Vacation vacation = vacationDao.findVacation(vacationId);

            if (customer.getId() != customerId) {
                throw new SecurityException(customer.getName() + " is not allowed to make reservations for " + customerId);
            }

            if ((vacation.totalReservations() + places) < vacation.getPlaces()) {
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to create reservation.", e);
        }
        return false;
    }

    @RolesAllowed({"root", "customer"})
    public void deleteReservation(Long reservationId) {
        try {
            if (!ejbContext.isCallerInRole("root")) {
                Customer c = customerDao.findCustomerByName(ejbContext.getCallerPrincipal().getName());
                Reservation r = reservationDao.findReservation(reservationId);
                if (c.getId() != r.getCustomer().getId()) {
                    throw new SecurityException(c.getName() + " is not allowed to delete reservations of " + reservationId);
                }
            }
            reservationDao.deleteReservation(reservationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete reservation.", e);
        }
    }

    @RolesAllowed({"root"})
    public void markAsPayed(long reservationId) {
        try {
            Reservation reservation = reservationDao.findReservation(reservationId);
            reservation.setState(ReservationState.PAID);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to mark reservation as payed.", e);
        }
    }

    @RolesAllowed({"root"})
    public void markAsNew(long reservationId) {
        try {
            Reservation reservation = reservationDao.findReservation(reservationId);
            reservation.setState(ReservationState.NEW);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to mark reservation as new.", e);
        }
    }
}
