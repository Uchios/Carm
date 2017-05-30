package Carm;

import java.sql.Date;

public class EntityCard {
	
	private int cardId;
	private String cardName;
	private int cutoffDay ;
	private int paymentDay;
	private int paymentMonth;
	private int accountId;
	private int paymentLimit;
	private Date timeLimit;
	private int invalidFlg;
	
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public int getCutoffDay() {
		return cutoffDay;
	}
	public void setCutoffDay(int cutoffDay) {
		this.cutoffDay = cutoffDay;
	}
	public int getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}
	public int getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(int paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getPaymentLimit() {
		return paymentLimit;
	}
	public void setPaymentLimit(int paymentLimit) {
		this.paymentLimit = paymentLimit;
	}
	public Date getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getInvalidFlg() {
		return invalidFlg;
	}
	public void setInvalidFlg(int invalidFlg) {
		this.invalidFlg = invalidFlg;
	}
	
}
