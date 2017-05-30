package Carm;

import java.sql.Date;

public class EntityLoginHistory {
	
	private int historyId;
	private int userId;
	private Date loginDate;
	private String loginTime;
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	
	
	
}
