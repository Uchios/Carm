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

@WebServlet("/SvCAL010")
public class SvCAL010 extends HttpServlet {

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
			boolean blankFlg = false;
			String alert = null;

			if(req.getParameter("search_flg") == null){
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
				// startDateを現在の日付に設定
				java.util.Date utilStartDate = new java.util.Date();
				startDate = new Date(utilStartDate.getTime());
				// endDateを現在から3か月後に設定
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.MONTH, 3);
				endDate = new java.sql.Date(cal.getTimeInMillis());
			}

			// 支払い期間を指定して支払情報を取得
			List<EntitySumPayment> espList = dcp.getSumPaymentByPaymentDate(
					startDate, endDate);

			req.setAttribute("esp_list", espList);
			req.setAttribute("start_date", startDate);
			req.setAttribute("end_date", endDate);
			req.setAttribute("alert", alert);

			RequestDispatcher rd = req.getRequestDispatcher("/CAL010.jsp");
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
