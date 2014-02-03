package cz.cvut.fel.jee.travel_company.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.ReservationState;

@Named
public class PaymentItemWriter extends AbstractItemWriter {
	
	@Inject
	private ReservationDao reservationDao;

	@Override
	public void writeItems(List<Object> list) throws Exception {
		for(Object o : list){
			PaymentIncomeRecord payment = (PaymentIncomeRecord) o;
			try{
				Reservation reservation = this.reservationDao.findReservation(payment.getId());
				if(payment.isPaid()){
						reservation.setState(ReservationState.PAID);
						this.reservationDao.updateReservation(reservation);
				}
			} catch(EntityNotFoundException e){
				continue;
			}
		}
	}

}
