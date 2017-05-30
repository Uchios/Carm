package Carm;

import java.sql.Date;

//支払いの集計情報を格納するエンティティ
public class EntitySumPayment {

	private int sumPayment;
	private Date paymentDate;
	private int accountId;
	
	public int getSumPayment() {
		return sumPayment;
	}
	public void setSumPayment(int sumPayment) {
		this.sumPayment = sumPayment;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int account_id) {
		this.accountId = account_id;
	}

	
}
