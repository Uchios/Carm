package Carm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SvCMM010")
public class SvCMM010 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			RequestDispatcher rd = null;
			req.setCharacterEncoding("UTF-8");// 文字コード変換（重要！）
			String userName = req.getParameter("user_name");
			String password = req.getParameter("password");
			DaoCarmAdmin dca = new DaoCarmAdmin();

			if (dca.getUsersCntbyNamePass(userName, password)) {
				// ユーザ名とパスワードの組み合わせが存在した場合
				// ユーザー情報取得
				EntityUser eu = dca.getUserEntitybyNamePass(userName, password);
				int userId = eu.getUserId();
				// セッションにユーザー情報を格納
				HttpSession session = req.getSession(true);
				session.setAttribute("user_id", userId);
				session.setAttribute("user_name", eu.getUserName());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				session.setAttribute("login_time", sdf.format(new Date()));

				// 本日１回目のログインであれば、定期支払処理を行う
				DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
				GpRegCalculator grc = new GpRegCalculator(dca, dcp, userId);
				if (grc.checkNewLogin()) {
					req.setAttribute("upd_cnt", grc.executeReg());
				}

				// ログイン履歴を登録
				int result = dca.setLoginHistory(eu.getUserId());
				// ログイン履歴の記入に失敗した場合、コンソールにエラーメッセージ出力
				if (result != 1) {
					System.out.println("ログイン履歴登録に失敗");
				}
				
				rd = req.getRequestDispatcher("/CMM030.jsp");
				rd.forward(req, resp);

			} else {
				// パスワード不一致の場合
				// エラーフラグをrequestに格納しログイン画面に遷移
				rd = req.getRequestDispatcher("/CMM010.jsp");
				req.setAttribute("message", "error");
				req.setAttribute("style", "alert");
				rd.forward(req, resp);
			}

		} catch (Exception e) { // 例外発生時、エラー画面に遷移
			e.printStackTrace();
			req.setAttribute("message", "systemError");
			req.setAttribute("style", "alert");
			RequestDispatcher rd = req.getRequestDispatcher("/CMM010.jsp");
			rd.forward(req, resp);
		}
	}
}
