package Carm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoCarmPersonal {

	private static final String DRIVER = "org.postgresql.Driver";
	private String URL = null;
	private static final String USER = "takuma";
	private static final String PASSWORD = "password";

	public DaoCarmPersonal(int userId) { // コンストラクタの引数でDBを指定する。
		URL = "jdbc:postgresql:carm" + userId;
	};

	private Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}

	// 口座一覧を取得する
	public List<EntityAccount> getAccountEntity() {
		List<EntityAccount> eaList = new ArrayList<EntityAccount>();
		String sql = "SELECT * FROM accounts ORDER BY account_id";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EntityAccount ea = new EntityAccount();
				ea.setAccountId(rs.getInt("account_id"));
				ea.setAccountName(rs.getString("account_name"));
				ea.setRegHolidayFlg(rs.getInt("reg_holiday_flg"));
				ea.setInvalidFlg(rs.getInt("invalid_flg"));
				eaList.add(ea);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return eaList;
	}

	// 口座IDから絞り込み口座IDを取得する
	public EntityAccount getAccountEntityByAccountId(int accountId) {
		EntityAccount ea = new EntityAccount();
		String sql = "SELECT * FROM accounts WHERE account_id = ?";
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				ea.setAccountId(rs.getInt("account_id"));
				ea.setAccountName(rs.getString("account_name"));
				ea.setRegHolidayFlg(rs.getInt("reg_holiday_flg"));
				ea.setInvalidFlg(rs.getInt("invalid_flg"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ea;
	}

	// 指定した口座名がテーブルに何件存在するかを取得する
	public int getCountByAccountName(String accountName) {
		String sql = "SELECT count(*) FROM accounts WHERE account_name = ?";
		int count = 1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, accountName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 銀行口座を登録する
	public int setAccount(String accountName, int invalidFlg, int regHolidayFlg) {
		String sql = "INSERT INTO accounts (account_name,invalid_flg,reg_holiday_flg) "
				+ "VALUES(?,?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, accountName);
			pst.setInt(2, invalidFlg);
			pst.setInt(3, regHolidayFlg);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 銀行口座を更新する
	public int updAccount(String accountName, int invalidFlg,
			int regHolidayFlg, int accountId) {
		String sql = "UPDATE accounts SET account_name = ?, invalid_flg = ?, reg_holiday_flg = ? "
				+ "WHERE account_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, accountName);
			pst.setInt(2, invalidFlg);
			pst.setInt(3, regHolidayFlg);
			pst.setInt(4, accountId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 銀行口座を削除する
	public int delAccounts(int accountId) {
		String sql = "DELETE FROM accounts WHERE account_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, accountId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// カード一覧を取得する
	public List<EntityCard> getCardEntity() {
		List<EntityCard> ecList = new ArrayList<EntityCard>();
		String sql = "SELECT * FROM cards ORDER BY card_id";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EntityCard ec = new EntityCard();
				ec.setCardId(rs.getInt("card_id"));
				ec.setCardName(rs.getString("card_name"));
				ec.setCutoffDay(rs.getInt("cutoff_day"));
				ec.setPaymentDay(rs.getInt("payment_day"));
				ec.setPaymentMonth(rs.getInt("payment_month"));
				ec.setAccountId(rs.getInt("account_id"));
				ec.setPaymentLimit(rs.getInt("payment_limit"));
				ec.setTimeLimit(rs.getDate("time_limit"));
				ec.setInvalidFlg(rs.getInt("invalid_flg"));
				ecList.add(ec);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ecList;
	}

	// カードIDで絞り込みカード情報を取得する
	public EntityCard getCardEntityByCardId(int cardId) {
		EntityCard ec = new EntityCard();
		String sql = "SELECT * FROM cards WHERE card_id = ?";
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, cardId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				ec.setCardId(rs.getInt("card_id"));
				ec.setCardName(rs.getString("card_name"));
				ec.setCutoffDay(rs.getInt("cutoff_day"));
				ec.setPaymentDay(rs.getInt("payment_day"));
				ec.setPaymentMonth(rs.getInt("payment_month"));
				ec.setAccountId(rs.getInt("account_id"));
				ec.setPaymentLimit(rs.getInt("payment_limit"));
				ec.setTimeLimit(rs.getDate("time_limit"));
				ec.setInvalidFlg(rs.getInt("invalid_flg"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ec;
	}

	// 指定したカード名がテーブルに何件存在するかを取得する
	public int getCountByCardName(String cardName) {
		String sql = "SELECT count(*) FROM cards WHERE card_name = ?";
		int count = 1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, cardName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// カードを登録する
	public int setCard(String cardName, int cutoffDay, int paymentDay,
			int paymentMonth, int accountId, int paymentLimit, Date timeLimit,
			int invalidFlg) {
		String sql = "INSERT INTO cards "
				+ "(card_name,cutoff_day,payment_day,payment_month,"
				+ "account_id,payment_limit,time_limit,invalid_flg) VALUES(?,?,?,?,?,?,?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, cardName);
			pst.setInt(2, cutoffDay);
			pst.setInt(3, paymentDay);
			pst.setInt(4, paymentMonth);
			pst.setInt(5, accountId);
			pst.setInt(6, paymentLimit);
			pst.setDate(7, timeLimit);
			pst.setInt(8, invalidFlg);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// カードを更新する
	public int updCard(int cardId, String cardName, int cutoffDay,
			int paymentDay, int paymentMonth, int accountId, int paymentLimit,
			Date timeLimit, int invalidFlg) {
		String sql = "UPDATE cards SET "
				+ "card_name = ?,cutoff_day = ?,payment_day = ?,payment_month = ?, "
				+ "account_id = ?,payment_limit = ?,time_limit = ?,invalid_flg = ? "
				+ "WHERE card_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, cardName);
			pst.setInt(2, cutoffDay);
			pst.setInt(3, paymentDay);
			pst.setInt(4, paymentMonth);
			pst.setInt(5, accountId);
			pst.setInt(6, paymentLimit);
			pst.setDate(7, timeLimit);
			pst.setInt(8, invalidFlg);
			pst.setInt(9, cardId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// カードを削除する
	public int delCards(int cardId) {
		String sql = "DELETE FROM cards WHERE card_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cardId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 利用明細一覧を取得する。
	public List<EntityUse> getUseEntity() {
		List<EntityUse> euList = new ArrayList<EntityUse>();
		String sql = "SELECT * FROM uses ORDER BY use_id";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EntityUse eu = new EntityUse();
				eu.setUseId(rs.getInt("use_id"));
				eu.setUseDate(rs.getDate("use_date"));
				eu.setPayment(rs.getInt("payment"));
				eu.setCardId(rs.getInt("card_id"));
				eu.setSummary(rs.getString("summary"));
				eu.setPaymentDate(rs.getDate("payment_date"));
				euList.add(eu);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return euList;
	}

	// 利用明細IDから利用明細を取得する
	public EntityUse getUseEntityByUseId(int useId) {
		String sql = "SELECT * FROM uses WHERE use_id = ?";
		EntityUse eu = new EntityUse();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, useId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				eu.setUseId(rs.getInt("use_id"));
				eu.setUseDate(rs.getDate("use_date"));
				eu.setPayment(rs.getInt("payment"));
				eu.setCardId(rs.getInt("card_id"));
				eu.setSummary(rs.getString("summary"));
				eu.setPaymentDate(rs.getDate("payment_date"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return eu;
	}

	// 期間とカードIDを指定すると利用明細を取得する
	public List<EntityUse> getUseEntityByCardIdPeriod(Integer cardId,
			Date startDate, Date endDate) {
		List<EntityUse> UDList = new ArrayList<EntityUse>();
		// カードIDに-1を指定した場合は全てのカードを取得対象とする
		String addSql = "";
		if (cardId != -1) {
			addSql = "AND uses.card_id = ? ";
		}
		String sql = "SELECT use_id,use_date,payment,uses.card_id,summary,payment_date "
				+ "FROM uses INNER JOIN cards "
				+ "ON uses.card_id = cards.card_id "
				+ "WHERE use_date >= ? AND use_date <= ? "
				+ addSql
				+ "ORDER BY use_date ASC";
		EntityUse eu = null;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			if (cardId != -1) {
				pst.setInt(3, cardId);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				eu = new EntityUse();
				eu.setUseId(rs.getInt("use_id"));
				eu.setUseDate(rs.getDate("use_date"));
				eu.setPayment(rs.getInt("payment"));
				eu.setCardId(rs.getInt("card_id"));
				eu.setSummary(rs.getString("summary"));
				eu.setPaymentDate(rs.getDate("payment_date"));
				UDList.add(eu);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return UDList;

	}

	// 期間とカードIDで指定して取得した利用明細の支払い合計を取得する。
	public int getSumPaymentByCardIdPeriod(Integer cardId, Date startDate,
			Date endDate) {
		// カードIDに-1を指定した場合は全てのカードを集計対象とする。
		String addSql = "";
		if (cardId != -1) {
			addSql = "AND uses.card_id = ? ";
		}
		String sql = "SELECT sum(payment) FROM uses INNER JOIN cards "
				+ "ON uses.card_id = cards.card_id "
				+ "WHERE use_date >= ? AND use_date <= ? " + addSql;
		int sumPayment = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			if (cardId != -1) {
				pst.setInt(3, cardId);
			}
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				sumPayment = rs.getInt("sum");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return sumPayment;

	}

	// 利用明細から支払日と銀行口座でグルーピングして支払日を集計する
	public List<EntitySumPayment> getSumPaymentByPaymentDate(Date startDate,
			Date endDate) {
		List<EntitySumPayment> espList = new ArrayList<EntitySumPayment>();
		String sql = "SELECT payment_date,account_id,SUM(payment) from uses "
				+ "INNER JOIN cards ON uses.card_id = cards.card_id "
				+ "WHERE payment_date >= ? AND payment_date <= ? "
				+ "GROUP BY payment_date,account_id ORDER BY payment_date,account_id";

		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				EntitySumPayment esp = new EntitySumPayment();
				esp.setSumPayment(rs.getInt("sum"));
				esp.setPaymentDate(rs.getDate("payment_date"));
				esp.setAccountId(rs.getInt("account_id"));
				espList.add(esp);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return espList;
	}

	// 　最新の利用明細IDを取得する
	public int getUseMaxId() {
		int latestUseId = -1;
		String sql = "SELECT MAX(use_id) FROM uses";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				latestUseId = rs.getInt("max");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return latestUseId;
	}

	// 利用明細を登録する
	public int setUses(Date useDate, int payment, int cardId, String summary,
			Date paymentDate) {
		String sql = "INSERT INTO uses (use_date,payment,card_id,summary,payment_date) "
				+ "VALUES(?,?,?,?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setDate(1, useDate);
			pst.setInt(2, payment);
			pst.setInt(3, cardId);
			pst.setString(4, summary);
			pst.setDate(5, paymentDate);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 利用明細を更新する
	public int updUses(int useId, Date useDate, int payment, int cardId,
			Date paymentDate, String summary) {
		String sql = "UPDATE uses SET "
				+ "use_date = ? , payment = ? , card_id = ? , summary = ? , payment_date = ? "
				+ "WHERE use_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setDate(1, useDate);
			pst.setInt(2, payment);
			pst.setInt(3, cardId);
			pst.setString(4, summary);
			pst.setDate(5, paymentDate);
			pst.setInt(6, useId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 利用明細を削除する
	public int delUses(int useId) {
		String sql = "DELETE FROM uses WHERE use_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, useId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 定期支払一覧を取得する
	public List<EntityRegPayment> getRegPaymentEntity() {
		List<EntityRegPayment> erpList = new ArrayList<EntityRegPayment>();
		String sql = "SELECT * FROM reg_payments ORDER BY reg_id";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EntityRegPayment erp = new EntityRegPayment();
				erp.setRegId(rs.getInt("reg_id"));
				erp.setCycleUnit(rs.getInt("cycle_unit"));
				erp.setRegMonth(rs.getInt("reg_month"));
				erp.setRegDay(rs.getInt("reg_day"));
				erp.setRegPayment(rs.getInt("reg_payment"));
				erp.setCardId(rs.getInt("card_id"));
				erp.setSummary(rs.getString("summary"));
				erp.setInvalidFlg(rs.getInt("invalid_flg"));
				erpList.add(erp);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return erpList;
	}

	// 定期支払情報を定期支払IDで指定して取得する
	public EntityRegPayment getRegPaymentByRegId(int regId) {
		EntityRegPayment erp = new EntityRegPayment();
		String sql = "SELECT * FROM reg_payments WHERE reg_id = ?";
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, regId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				erp.setRegId(rs.getInt("reg_id"));
				erp.setCycleUnit(rs.getInt("cycle_unit"));
				erp.setRegMonth(rs.getInt("reg_month"));
				erp.setRegDay(rs.getInt("reg_day"));
				erp.setRegPayment(rs.getInt("reg_payment"));
				erp.setCardId(rs.getInt("card_id"));
				erp.setSummary(rs.getString("summary"));
				erp.setInvalidFlg(rs.getInt("invalid_flg"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return erp;
	}

	// 定期支払を追加する
	public int setRegPayment(int cycleUnit, int regMonth, int regDay,
			int regPayment, int cardId, String summary, int invalidFlg) {
		String sql = "INSERT INTO reg_payments (cycle_unit, reg_month, reg_day, "
				+ "reg_payment, card_id, summary, invalid_flg)"
				+ "VALUES(?,?,?,?,?,?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cycleUnit);
			pst.setInt(2, regMonth);
			pst.setInt(3, regDay);
			pst.setInt(4, regPayment);
			pst.setInt(5, cardId);
			pst.setString(6, summary);
			pst.setInt(7, invalidFlg);
			result = pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 定期支払を更新する
	public int updRegPayment(int cycleUnit, int regMonth, int regDay,
			int regPayment, int cardId, String summary, int invalidFlg,
			int regId) {
		String sql = "UPDATE reg_payments SET cycle_unit = ?, reg_month = ?, reg_day = ?, "
				+ "reg_payment = ?, card_id = ?, summary = ?, invalid_flg = ?"
				+ "WHERE reg_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cycleUnit);
			pst.setInt(2, regMonth);
			pst.setInt(3, regDay);
			pst.setInt(4, regPayment);
			pst.setInt(5, cardId);
			pst.setString(6, summary);
			pst.setInt(7, invalidFlg);
			pst.setInt(8, regId);
			result = pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// タグを登録する
	public int setTags(int useId, String tagName) {
		String sql = "INSERT INTO tags (use_id,tag_name) VALUES(?,?)";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, useId);
			pst.setString(2, tagName);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 利用明細IDを指定してタグを削除する
	public int delTagsByUseId(int useId) {
		String sql = "DELETE FROM tags WHERE use_id = ?";
		int result = -1;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, useId);
			result = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// タグ毎に利用額を集計し降順に並べる（円グラフ生成用）
	public List<EntityAnalyticalData> getAnalyticalDateForPieChart(int cardId, Date startDate,
			Date endDate) {
		List<EntityAnalyticalData> eadList = new ArrayList<EntityAnalyticalData>();
		String addSql = "";
		if (cardId != -1) {
			addSql = "AND uses.card_id = ? ";
		}
		String sql = "SELECT tag_name,sum(payment) FROM tags "
				+ "LEFT OUTER JOIN uses ON tags.use_id = uses.use_id "
				+ "WHERE use_date >= ? AND use_date <= ? " + addSql
				+ "GROUP BY tag_name ORDER BY SUM(payment) DESC";

		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			if (cardId != -1) {
				pst.setInt(3, cardId);
			}
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				EntityAnalyticalData ead = new EntityAnalyticalData();
				ead.setTagName(rs.getString("tag_name"));
				ead.setPayment(rs.getInt("sum"));
				eadList.add(ead);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return eadList;
	}

	// 新規ユーザ作成時の処理を行う
	public String createTables() {
		String result = "crt";
		// 口座テーブル作成SQL
		String crtAccounts = "CREATE TABLE accounts("
				+ "account_id serial NOT NULL PRIMARY KEY,"
				+ "account_name varchar(50) NOT NULL UNIQUE,"
				+ "reg_holiday_flg integer NOT NULL,"
				+ "invalid_flg integer NOT NULL)";
		// カードテーブル作成SQL
		String crtCards = "CREATE TABLE cards("
				+ "card_id serial NOT NULL PRIMARY KEY,"
				+ "card_name varchar(50) NOT NULL UNIQUE,"
				+ "cutoff_day integer  NOT NULL,"
				+ "payment_day integer NOT NULL,"
				+ "payment_month integer NOT NULL,"
				+ "account_id integer NOT NULL REFERENCES accounts(account_id) ,"
				+ "payment_limit integer," + "time_limit date,"
				+ "invalid_flg integer NOT NULL)";
		// 利用明細テーブル作成SQL
		String crtUses = "CREATE TABLE uses("
				+ "use_id serial NOT NULL PRIMARY KEY,"
				+ "use_date date NOT NULL," + "payment integer NOT NULL,"
				+ "card_id integer NOT NULL REFERENCES cards(card_id), "
				+ "summary varchar(150)," + "payment_date date)";
		// 定期支払テーブル作成SQL
		String crtRegPayments = "CREATE TABLE reg_payments("
				+ "reg_id serial NOT NULL PRIMARY KEY,"
				+ "cycle_unit integer NOT NULL," + "reg_month integer,"
				+ "reg_day integer NOT NULL," + "reg_payment integer NOT NULL,"
				+ "card_id integer NOT NULL REFERENCES cards(card_id) ,"
				+ "summary varchar(50)," + "invalid_flg integer NOT NULL)";
		// タグテーブル作成SQL
		String crtTags = "CREATE TABLE tags(" + "tag_id serial PRIMARY KEY,"
				+ "use_id integer NOT NULL REFERENCES uses(use_id),"
				+ "tag_name varchar(20) NOT NULL)";
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate(crtAccounts);
			result += "A";
			stmt.executeUpdate(crtCards);
			result += "C";
			stmt.executeUpdate(crtUses);
			result += "U";
			stmt.executeUpdate(crtRegPayments);
			result += "R";
			stmt.executeUpdate(crtTags);
			result += "T";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
