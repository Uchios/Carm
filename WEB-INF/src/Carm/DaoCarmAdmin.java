package Carm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.omg.CORBA.UserException;

public class DaoCarmAdmin {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql:carm_admin";
	private static final String USER = "takuma";
	private static final String PASSWORD = "password";

	private Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}

	// ユーザー名とパスワードの組み合わせで検索し、そのユーザーが1名のみ存在すればtrueを返す
	public boolean getUsersCntbyNamePass(String userName, String password) {
		boolean result = false;
		int count = 0;
		String sql = "SELECT count(*) FROM users "
				+ "WHERE user_name = ? AND password = ?";
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, userName);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 1) {
				result = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ユーザー名とパスワードからユーザー情報を取得する
	public EntityUser getUserEntitybyNamePass(String userName, String password) {
		EntityUser eu = new EntityUser();
		String sql = "SELECT * FROM users "
				+ "WHERE user_name = ? AND password = ?";
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, userName);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				eu.setUserId(rs.getInt("user_id"));
				eu.setUserName(rs.getString("user_name"));
				eu.setPassword(rs.getString("password"));
				eu.setAdminFlg(rs.getInt("admin_flg"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return eu;
	}

	// ログイン履歴を記録する
	public Integer setLoginHistory(int userId) {
		String sql = "INSERT INTO login_history (user_id,login_date,login_time) "
				+ "VALUES (?,current_date,current_time)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, userId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 最新のログインレコードを取得する
	public EntityLoginHistory getLoginHistoryMaxRecord(int userId) {
		String sql = "SELECT * FROM login_history WHERE history_id = "
				+ "(SELECT MAX(history_id) FROM login_history WHERE user_id = ?)";
		EntityLoginHistory elh = new EntityLoginHistory();

		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				elh.setHistoryId(rs.getInt("history_id"));
				elh.setUserId(rs.getInt("user_id"));
				elh.setLoginDate(rs.getDate("login_date"));
				elh.setLoginTime(rs.getString("login_time"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return elh;
	}

	// 最新のユーザレコードを取得する
	public EntityUser getUserMaxRecord() {
		String sql = "SELECT * FROM users WHERE user_id = "
				+ "(SELECT MAX(user_id) FROM users)";
		EntityUser eu = new EntityUser();
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				eu.setUserId(rs.getInt("user_id"));
				eu.setUserName(rs.getString("user_name"));
				eu.setPassword(rs.getString("password"));
				eu.setAdminFlg(rs.getInt("admin_flg"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return eu;
	}

	// 新規ユーザを追加する
	public int addUser(String userName, String password, int adminFlg) {
		String sql = "INSERT INTO users (user_name,password,admin_flg)"
				+ "VALUES(?,?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, userName);
			pst.setString(2, password);
			pst.setInt(3, adminFlg);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 新規ユーザ用DBを作成する
	public boolean crtUserDB(int userId) {
		boolean result = false;
		String sql = "CREATE database carm" + userId + " owner takuma";
		try (Connection con = getConnection();
				Statement pst = con.createStatement();) {
			pst.executeUpdate(sql);
			result = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
