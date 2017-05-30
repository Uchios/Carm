package Carm;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SvCMM020")
public class SvCMM020 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");

			DaoCarmAdmin dca = new DaoCarmAdmin();
			String userName = req.getParameter("user_name");
			String password = req.getParameter("password");
			String alert = null;
			String style = null;

			if (userName == "" || userName == " " || userName == "　") {
				alert = "ユーザ名を入力してください";
				style = "alert";
			}else if (password == "" || password == " " || password == "　") {
				alert = "パスワードを入力してください";
				style = "alert";
			}else if (dca.getUsersCntbyNamePass(userName, password)) {
				alert = "そのユーザ名は使えません";
				style = "alert";
			}

			if (alert == null) {
				String crtTablesResult = "";
				int historyCnt = -1;
				// 管理者DBのユーザテーブルに新規ユーザ追加
				if (dca.addUser(userName, password, 0) == 1) {
					EntityUser eu = dca.getUserMaxRecord();
					int userId = eu.getUserId();
					// DBとテーブルの新規作成
					if (dca.crtUserDB(userId)) {
						DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
						crtTablesResult += dcp.createTables();
						// ユーザ作成時刻で最初のログイン履歴を登録
						historyCnt = dca.setLoginHistory(userId);
					}
				}

				// 全テーブルが正常に作成され、ログイン履歴の登録が完了している場合
				if (crtTablesResult.equals("crtACURT") && historyCnt == 1) {
					alert = "新規ユーザを作成しました。ログインして利用できます。";
					style = "complete";
				}

				if (alert == null) {
					alert = "エラー：ユーザの作成に失敗しました。";
					style = "alert";
				}

			}

			req.setAttribute("alert", alert);
			req.setAttribute("style", style);
			RequestDispatcher rd = req.getRequestDispatcher("/CMM020.jsp");
			rd.forward(req, resp);

		} catch (Exception e) { // 例外発生時、エラー画面に遷移
			e.printStackTrace();
			req.setAttribute("message", "systemError");
			req.setAttribute("style", "alert");
			RequestDispatcher rd = req.getRequestDispatcher("/CMM010.jsp");
			rd.forward(req, resp);
		}
	}
}