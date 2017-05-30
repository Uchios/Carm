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

@WebServlet("/SvRFR020")
public class SvRFR020 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession(false);
			int userId = (Integer) session.getAttribute("user_id");

			DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
			Integer useId = Integer.parseInt(req.getParameter("use_id"));
			String year = req.getParameter("year");
			String month = req.getParameter("month");
			String day = req.getParameter("day");
			Integer payment = null;
			Integer cardId = Integer.parseInt(req.getParameter("card_id"));
			String summary = req.getParameter("summary");
			Date useDate = null;
			String alert = null;

			try {
				useDate = Date.valueOf(year + "-" + month + "-" + day);
			} catch (IllegalArgumentException iae) {
				alert = "日付を正しく入力してください";
			}

			try {
				payment = Integer.parseInt(req.getParameter("payment"));
			} catch (IllegalArgumentException iae) {
				alert = "支払い金額を正しく入力してください";
			}

			if (alert != null) {
				req.setAttribute("alert", alert);
				RequestDispatcher rd = req.getRequestDispatcher("/RFR020.jsp");
				rd.forward(req, resp);
			}

			if (alert == null) {
				Date paymentDate = new GpGetPaymentInfo().getPaymentDate(
						useDate, cardId, dcp);
				int result = dcp.updUses(useId, useDate, payment, cardId,
						paymentDate, summary);

				if (result == 1) {
					dcp.delTagsByUseId(useId);
					GpTagModule gtm = new GpTagModule();
					List<String> tags = gtm.getTag(summary, '#');
					for (int i = 0; i < tags.size(); i++) {
						dcp.setTags(useId, tags.get(i));
					}
				}

				req.setAttribute("result", result);
				req.setAttribute("type", "update");
				RequestDispatcher rd = req.getRequestDispatcher("/CMM030.jsp");
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
