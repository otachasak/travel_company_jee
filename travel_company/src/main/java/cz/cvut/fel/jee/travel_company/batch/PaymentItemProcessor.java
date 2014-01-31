package cz.cvut.fel.jee.travel_company.batch;

import javax.batch.api.chunk.ItemProcessor;

public class PaymentItemProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object inputPayment) throws Exception {
		return inputPayment;
	}

}
