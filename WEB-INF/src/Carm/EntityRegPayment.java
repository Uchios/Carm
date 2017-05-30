package Carm;

public class EntityRegPayment {
	private int regId;
	private int cycleUnit;
	private int regMonth;
	private int regDay;
	private int regPayment;
	private int cardId;
	private String summary;
	private int invalidFlg;
	
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public int getCycleUnit() {
		return cycleUnit;
	}
	public void setCycleUnit(int cycleUnit) {
		this.cycleUnit = cycleUnit;
	}
	public int getRegMonth() {
		return regMonth;
	}
	public void setRegMonth(int regMonth) {
		this.regMonth = regMonth;
	}
	public int getRegDay() {
		return regDay;
	}
	public void setRegDay(int regDay) {
		this.regDay = regDay;
	}
	public int getRegPayment() {
		return regPayment;
	}
	public void setRegPayment(int regPayment) {
		this.regPayment = regPayment;
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
	public int getInvalidFlg() {
		return invalidFlg;
	}
	public void setInvalidFlg(int invalidFlg) {
		this.invalidFlg = invalidFlg;
	}
	
}
