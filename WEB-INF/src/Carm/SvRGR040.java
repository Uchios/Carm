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

@WebServlet("/SvRGR040")
public class SvRGR040 extends HttpServlet {

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
			Integer cycleUnit = Integer
					.parseInt(req.getParameter("cycle_unit"));
			Integer regMonth = Integer.parseInt(req.getParameter("reg_month"));
			Integer regDay = Integer.parseInt(req.getParameter("reg_day"));
			Integer regPayment = null;
			Integer cardId = Integer.parseInt(req.getParameter("card_id"));
			String summary = req.getParameter("summary");
			int invalidFlg = Integer.parseInt(req.getParameter("invalid_flg"));

			int result = -1;
			RequestDispatcher rd = null;
			
			if (cycleUnit == 1 && regMonth == 0) {
				alert = "支払い周期が年1回の場合は、支払日の月を入力して下さい";
			}

			if (cycleUnit == 0 && regMonth != 0) {
				regMonth = 0;
			}
			
			try{
				regPayment = Integer.parseInt(req.getParameter("reg_payment"));
			}catch(NumberFormatException e){
				alert = "支払い金額を正しく入力してください";
			}
			
			if (alert != null) {
				req.setAttribute("alert", alert);

				switch (sqlType) {
				case "insert":
					rd = req.getRequestDispatcher("/RGR040.jsp");
					break;
				case "update":
					int regId = Integer.parseInt(req.getParameter("reg_id"));
					req.setAttribute("reg_id", regId);
					rd = req.getRequestDispatcher("/RGR020.jsp");
					break;
				}
				rd.forward(req, resp);
			}

			if (alert == null) {
				switch (sqlType) {
				case "insert":
					result = dcp.setRegPayment(cycleUnit, regMonth, regDay,
							regPayment, cardId, summary, invalidFlg);
					break;
				case "update":
					int regId = Integer.parseInt(req.getParameter("reg_id"));
					result = dcp.updRegPayment(cycleUnit, regMonth, regDay,
							regPayment, cardId, summary, invalidFlg, regId);
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