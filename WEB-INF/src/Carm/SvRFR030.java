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

@WebServlet("/SvRFR030")
public class SvRFR030 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		try {
			req.setCharacterEncoding("UTF-8"); 
			HttpSession session = req.getSession(false);
			int userId = (Integer)session.getAttribute("user_id");
			
			DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
			Integer useId= Integer.parseInt(req.getParameter("use_id"));
			dcp.delTagsByUseId(useId);
			int result = dcp.delUses(useId);
			
			req.setAttribute("result", result);
			req.setAttribute("type", "delete");
			RequestDispatcher rd = req.getRequestDispatcher("/CMM030.jsp");
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
