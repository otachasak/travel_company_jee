package cz.cvut.fel.jee.travel_company.dao.impl;

import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;


public class ReservationDaoImpl extends BaseDaoImpl implements ReservationDao {

	@Override
    @RolesAllowed({"root", "customer"})
    public List<Reservation> findAllReservations() {
		List<Reservation> reservations = this.em.createNamedQuery("findAllReservations", Reservation.class).getResultList();
		return reservations;
	}

    @RolesAllowed({"root", "customer"})
    public List<Reservation> findAllReservationsForCustomer(long customerId) {
        List<Reservation> reservations = em.createQuery("select res from Reservation res " +
                "where res.customer.id = :customerId")
                .setParameter("customerId", customerId)
                .getResultList();
        return reservations;
    }

    @Override
    @RolesAllowed({"root", "customer"})
    public List<Reservation> findAllReservationsOfVacation(long vacationId) {
        List<Reservation> reservations = em.createQuery("select res from Reservation res " +
                "where res.vacation.id = :vacationId")
                .setParameter("vacationId", vacationId)
                .getResultList();
        return reservations;
    }

    @RolesAllowed({"root", "customer"})
    public List<Reservation> findAllReservationsOfVacationForCustomer(long vacationId, long customerId) {
        List<Reservation> reservations = em.createQuery("select res from Reservation res " +
                "where res.vacation.id = :vacationId " +
                "and res.customer.id = :customerId")
                .setParameter("vacationId", vacationId)
                .setParameter("customerId", customerId)
                .getResultList();
        return reservations;
    }


    @Override
    @RolesAllowed({"root", "customer"})
	public void addReservation(Reservation reservation) {
		this.em.persist(reservation);
	}

	@Override
    @RolesAllowed({"root", "customer"})
	public Reservation findReservation(Long id)
			throws EntityNotFoundException {
		Reservation reservation = this.findDbReservation(id);
		return reservation;
	}

	@Override
    @RolesAllowed({"root", "customer"})
	public void updateReservation(Reservation reservation)
			throws EntityNotFoundException {
		Reservation dbReservation = this.findDbReservation(reservation.getId());
		dbReservation.setPlaces(reservation.getPlaces());
		dbReservation.setState(reservation.getState());
		this.em.persist(dbReservation);
	}

	@Override
    @RolesAllowed({"root", "customer"})
	public void deleteReservation(Long id) throws EntityNotFoundException {
		Reservation dbReservation = this.findDbReservation(id);
		this.em.remove(dbReservation);
	}
	
	/**
	 * Find reservation with given id otherwise throws exception
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	private Reservation findDbReservation(Long id) throws EntityNotFoundException{
		Reservation dbReservation = this.em.find(Reservation.class, id);
		if(dbReservation == null){
			throw new EntityNotFoundException(Reservation.class.getName(), id);
		}
		return dbReservation;
	}

}
