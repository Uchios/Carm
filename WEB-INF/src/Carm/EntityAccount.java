package Carm;

public class EntityAccount {
	
	private int accountId;
	private String accountName;
	private int regHolidayFlg;
	private int invalidFlg;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getRegHolidayFlg() {
		return regHolidayFlg;
	}
	public void setRegHolidayFlg(int regHolidayFlg) {
		this.regHolidayFlg = regHolidayFlg;
	}
	public int getInvalidFlg() {
		return invalidFlg;
	}
	public void setInvalidFlg(int invalidFlg) {
		this.invalidFlg = invalidFlg;
	}
	
}
