package Carm;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SvBKK010")
public class SvBKK010 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession(false);
			Integer userId = (Integer) session.getAttribute("user_id");
			String alert = null;

			DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
			String year = req.getParameter("year");
			String month = req.getParameter("month");
			String day = req.getParameter("day");
			String summary = req.getParameter("summary");
			Integer cardId = null;
			Integer payment = null;
			Date useDate = null;

			try {
				cardId = Integer.parseInt(req.getParameter("card_id"));
			} catch (IllegalArgumentException iae) {
				alert = "カードを選択してください";
			}

			try {
				payment = Integer.parseInt(req.getParameter("payment"));
			} catch (IllegalArgumentException iae) {
				alert = "支払い金額を正しく入力してください";
			}

			try {
				useDate = Date.valueOf(year + "-" + month + "-" + day);
			} catch (IllegalArgumentException iae) {
				alert = "日付を正しく入力してください";
			}

			if (alert != null) {
				req.setAttribute("type", "error");
				req.setAttribute("alert", alert);
				RequestDispatcher rd = req.getRequestDispatcher("/CMM030.jsp");
				rd.forward(req, resp);
			} else {

				Date paymentDate = new GpGetPaymentInfo().getPaymentDate(
						useDate, cardId, dcp);
				int result = dcp.setUses(useDate, payment, cardId, summary,
						paymentDate);

				// 摘要からタグを抽出する
				if (result == 1) {
					GpTagModule gtm = new GpTagModule();
					List<String> tags = gtm.getTag(summary, '#');
					for (int i = 0; i < tags.size(); i++) {
						dcp.setTags(dcp.getUseMaxId(), tags.get(i));
					}
				}

				req.setAttribute("result", result);
				req.setAttribute("type", "insert");
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
