package Carm;

import java.sql.Date;

public class EntityUse {

	private int useId;
	private Date useDate;
	private int payment;
	private int cardId;
	private String summary;
	private Date paymentDate;

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
