package cz.cvut.fel.jee.travel_company.batch;

import java.math.BigDecimal;

public class PaymentIncomeRecord {
	
	private Long id;
	private BigDecimal amount;
	private boolean paid;
	
	public PaymentIncomeRecord(Long id, BigDecimal amount) {
		super();
		this.id = id;
		this.amount = amount;
		this.paid = false;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
