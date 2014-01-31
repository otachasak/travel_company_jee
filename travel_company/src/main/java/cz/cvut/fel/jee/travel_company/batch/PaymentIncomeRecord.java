package cz.cvut.fel.jee.travel_company.batch;

import java.math.BigDecimal;

public class PaymentIncomeRecord {
	
	private Long id;
	private BigDecimal amount;
	
	public PaymentIncomeRecord(Long id, BigDecimal amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
