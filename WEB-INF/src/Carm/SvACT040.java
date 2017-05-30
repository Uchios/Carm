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

@WebServlet("/SvACT040")
public class SvACT040 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession(false);
			int userId = (Integer) session.getAttribute("user_id");
			String alert = null;

			DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
			String sqlType = req.getParameter("sql_type");
			String accountName = req.getParameter("account_name");
			Integer invalidFlg = Integer.parseInt(req.getParameter("invalid_flg"));
			Integer regHolidayFlg = Integer.parseInt(req.getParameter("reg_holiday_flg"));
			int result = -1;
			RequestDispatcher rd = null;

			if (accountName == "" || accountName == " " || accountName == "　") {
				alert = "口座名を入力してください";
			}

			if (sqlType == "insert" && dcp.getCountByAccountName(accountName) > 0) {
				alert = "その口座名は既に使われています";
			}

			if (alert != null) {
				req.setAttribute("alert", alert);
				switch (sqlType) {
				case "insert":
					rd = req.getRequestDispatcher("/ACT040.jsp");
					break;
				case "update":
					int accountId = Integer.parseInt(req.getParameter("account_id"));
					req.setAttribute("account_id", accountId);
					rd = req.getRequestDispatcher("/ACT020.jsp");
					break;
				}
				rd.forward(req, resp);
			}

			if (alert == null) {
				if (sqlType.equals("insert")) {
					result = dcp.setAccount(accountName, invalidFlg, regHolidayFlg);
				} else if (sqlType.equals("update")) {
					int accountId = Integer.parseInt(req.getParameter("account_id"));
					result = dcp.updAccount(accountName, invalidFlg, regHolidayFlg, accountId);
				}

				req.setAttribute("result", result);
				req.setAttribute("type", "insert");
				rd = req.getRequestDispatcher("/CMM030.jsp");
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