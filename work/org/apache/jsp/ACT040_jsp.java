/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2017-05-31 14:33:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Carm.DaoCarmPersonal;
import java.util.List;
import Carm.EntityAccount;

public final class ACT040_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>クレジットカード管理システム</title>\n");
      out.write("<link href=\"./Main.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t");

		request.setAttribute("page_name", "▶︎ 銀行口座の設定 ▶ 新規登録︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
	
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write('\n');
      out.write('	');

		if (alert != null) {
	
      out.write("\n");
      out.write("\t<div class=\"alert\">");
      out.print(alert);
      out.write("</div>\n");
      out.write("\t");

		}
	
      out.write("\n");
      out.write("\t<div id=\"sub_page\">\n");
      out.write("\t\t<form action=\"./SvACT040\" method=\"post\">\n");
      out.write("\t\t\t<h4>口座名</h4>\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"sql_type\" value=\"");
      out.print("insert");
      out.write("\">\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"account_id\">\n");
      out.write("\t\t\t<input type=\"text\" name=\"account_name\">\n");
      out.write("\t\t\t<h4>定休日の設定</h4>\n");
      out.write("\t\t\t<input type=\"radio\" name=\"reg_holiday_flg\" value=\"1\" checked>\n");
      out.write("\t\t\t土曜・日曜は引き落としを行わない\n");
      out.write("\t\t\t<input type=\"radio\" name=\"reg_holiday_flg\" value=\"0\">\n");
      out.write("\t\t\t土曜・日曜も引き落としを行う\n");
      out.write("\t\t\t<h4>状態</h4>\n");
      out.write("\t\t\t<input type=\"radio\" name=\"invalid_flg\" value=\"0\" checked>\n");
      out.write("\t\t\t有効\n");
      out.write("\t\t\t<input type=\"radio\" name=\"invalid_flg\" value=\"1\">\n");
      out.write("\t\t\t無効 <br> <br>\n");
      out.write("\t\t\t<input type=\"submit\" value=\"登録\" class=\"decision\">\n");
      out.write("\t\t</form>\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
