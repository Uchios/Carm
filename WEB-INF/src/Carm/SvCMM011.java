package Carm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SvCMM011")
public class SvCMM011 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
		req.setCharacterEncoding("UTF-8");// 文字コード変換（重要！）
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate();
		}
		req.setAttribute("message", "logout");
		req.setAttribute("style", "complete");
		RequestDispatcher rd = req.getRequestDispatcher("/CMM010.jsp");
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
