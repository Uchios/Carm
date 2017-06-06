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

@WebServlet("/SvTAG010")
public class SvTAG010 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession(false);
			int userId = (Integer) session.getAttribute("user_id");

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
				// startDateを現在から12か月前に設定
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.MONTH, -12);
				startDate = new java.sql.Date(cal.getTimeInMillis());
				// 全てのカードを検索対象にする
				cardId = -1;
			}

			// 期間とカードIDを指定して円グラフ用データを取得
			List<EntityAnalyticalData> eadList = dcp
					.getAnalyticalDateForPieChart(cardId, startDate, endDate);
			List<EntityAnalyticalData> eadList2 = dcp
					.getAnalyticalDateForPieChart2(cardId, startDate, endDate);

			List<EntityCard> ecList = dcp.getCardEntity();
			EntityCard ec = dcp.getCardEntityByCardId(cardId);
			String cardName = null;
			if (cardId == -1) {
				cardName = "全てのカード";
			} else {
				cardName = ec.getCardName();
			}

			req.setAttribute("ead_list", eadList);
			req.setAttribute("ead_list2",eadList2);
			req.setAttribute("ec_list", ecList);
			req.setAttribute("card_name", cardName);
			req.setAttribute("end_date", endDate);
			req.setAttribute("start_date", startDate);
			req.setAttribute("alert", alert);

			RequestDispatcher rd = req.getRequestDispatcher("/TAG011.jsp");
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
