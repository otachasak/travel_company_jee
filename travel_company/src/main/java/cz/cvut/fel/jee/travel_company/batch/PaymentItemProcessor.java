package cz.cvut.fel.jee.travel_company.batch;

import java.math.BigDecimal;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.ReservationState;

@Named
public class PaymentItemProcessor implements ItemProcessor {

	@Inject
	private ReservationDao reservationDao;
	
	@Override
	public Object processItem(Object inputPayment) throws Exception {
		PaymentIncomeRecord payment = (PaymentIncomeRecord) inputPayment;
		try{
			Reservation reservation = this.reservationDao.findReservation(payment.getId());
			if(reservation.getState() == ReservationState.NEW){
				BigDecimal amount = reservation.getVacation().getPrice().multiply(new BigDecimal(reservation.getPlaces()));
				if(amount.compareTo(payment.getAmount()) == 0){
					payment.setPaid(true);
				}
			}
		} catch(EntityNotFoundException e){
		}
		return payment;
	}

}
