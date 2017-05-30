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

@WebServlet("/SvRFR010")
public class SvRFR010 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession(false);
			int userId = (Integer)session.getAttribute("user_id");
			
			DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
			String endYear = req.getParameter("end_year");
			String endMonth = req.getParameter("end_month");
			String endDay = req.getParameter("end_day");
			String startYear = req.getParameter("start_year");
			String startMonth = req.getParameter("start_month");
			String startDay = req.getParameter("start_day");
			Date endDate = null;
			Date startDate = null;
			Integer cardId = null;
			boolean blankFlg = false;
			String alert = null;

			try {
				cardId = Integer.parseInt(req.getParameter("card_id"));
			} catch (IllegalArgumentException iae) {
				blankFlg = true;
			}

			if (!blankFlg) {
				try {
					endDate = Date.valueOf(endYear + "-" + endMonth + "-"
							+ endDay);
					startDate = Date.valueOf(startYear + "-" + startMonth + "-"
							+ startDay);
				} catch (IllegalArgumentException iae) {
					alert = "日付を正しく入力してください";
					blankFlg = true;
				}
			}

			if (blankFlg) {
				// フォームから情報が受け渡されていない場合
				// endDateを現在の日付に設定
				java.util.Date utilEndDate = new java.util.Date();
				endDate = new Date(utilEndDate.getTime());
				// startDateを現在から2か月前に設定
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.MONTH, -2);
				startDate = new java.sql.Date(cal.getTimeInMillis());
				// 全てのカードを検索対象にする
				cardId = -1;
			}

			// 期間とカードIDを指定して利用明細一覧を取得
			List<EntityUse> euList = dcp.getUseEntityByCardIdPeriod(cardId,
					startDate, endDate);
			// 利用明細の合計支払い金額を取得
			int sumPayment = dcp.getSumPaymentByCardIdPeriod(cardId, startDate,
					endDate);
			List<EntityCard> ecList = dcp.getCardEntity();

			req.setAttribute("eu_list", euList);
			req.setAttribute("ec_list", ecList);
			req.setAttribute("sum_payment", sumPayment);
			req.setAttribute("end_date", endDate);
			req.setAttribute("start_date", startDate);
			req.setAttribute("alert", alert);
			
			RequestDispatcher rd = req.getRequestDispatcher("/RFR010.jsp");
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
