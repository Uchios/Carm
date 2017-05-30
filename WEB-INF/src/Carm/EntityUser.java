package Carm;

//ユーザー情報を格納するエンティティ
public class EntityUser {
	
	private int userId;
	private String userName;
	private String password;
	private int adminFlg;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminFlg() {
		return adminFlg;
	}
	public void setAdminFlg(int admin_flg) {
		this.adminFlg = admin_flg;
	}
}
