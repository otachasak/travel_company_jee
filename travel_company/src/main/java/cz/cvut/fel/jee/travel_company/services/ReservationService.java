package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;
import cz.cvut.fel.jee.travel_company.dao.ReservationDao;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vlada
 * @version: 31/01/14
 */
public class ReservationService extends BasicService {

    @Inject
    private ReservationDao reservationDao;

    public List<ReservationDTO> getCustomersReservations(long customerId) {
        List<Reservation> reservations =  reservationDao.findAllReservationsForCustomer(customerId);
        return originalToDTos(Reservation.class, ReservationDTO.class, reservations);
    }
}
