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

@WebServlet("/SvCRD040")
public class SvCRD040 extends HttpServlet {

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
			String cardName = req.getParameter("card_name");
			Integer cutoffDay = Integer
					.parseInt(req.getParameter("cutoff_day"));
			Integer paymentMonth = Integer.parseInt(req
					.getParameter("payment_month"));
			Integer paymentDay = Integer.parseInt(req
					.getParameter("payment_day"));
			Integer accountId = Integer
					.parseInt(req.getParameter("account_id"));
			Integer paymentLimit = null;
			Date timeLimit = null;
			Integer invalidFlg = Integer.parseInt(req
					.getParameter("invalid_flg"));
			int result = -1;
			RequestDispatcher rd = null;

			try {
				paymentLimit = Integer.parseInt(req
						.getParameter("payment_limit"));
			} catch (IllegalArgumentException iae) {
				alert = "限度額を正しく入力してください";
			}

			try {
				timeLimit = Date.valueOf(req.getParameter("time_limit"));
			} catch (IllegalArgumentException iae) {
				alert = "有効期限を正しく入力してください";
			}

			if (cardName == "" || cardName == " " || cardName == "　") {
				alert = "カード名を入力してください";
			}

			if (sqlType == "insert" && dcp.getCountByCardName(cardName) > 0) {
				alert = "そのカード名は既に使われています";
			}

			if (alert != null) {
				req.setAttribute("alert", alert);

				switch (sqlType) {
				case "insert":
					rd = req.getRequestDispatcher("/CRD040.jsp");
					break;
				case "update":
					int cardId = Integer.parseInt(req.getParameter("card_id"));
					req.setAttribute("card_id", cardId);
					rd = req.getRequestDispatcher("/CRD020.jsp");
					break;
				}
				rd.forward(req, resp);
			}

			if (alert == null) {
				switch (sqlType) {
				case "insert":
					result = dcp.setCard(cardName, cutoffDay, paymentDay,
							paymentMonth, accountId, paymentLimit, timeLimit,
							invalidFlg);
					break;
				case "update":
					int cardId = Integer.parseInt(req.getParameter("card_id"));
					result = dcp.updCard(cardId, cardName, cutoffDay,
							paymentDay, paymentMonth, accountId, paymentLimit,
							timeLimit, invalidFlg);
					break;
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