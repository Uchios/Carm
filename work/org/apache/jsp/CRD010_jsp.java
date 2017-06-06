/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2017-05-31 14:33:11 UTC
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
import Carm.EntityCard;
import Carm.EntityAccount;
import java.sql.Date;
import java.text.DecimalFormat;

public final class CRD010_jsp extends org.apache.jasper.runtime.HttpJspBase
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

		request.setAttribute("page_name", "▶︎ クレジットカードの設定");
		Integer userId = (Integer) session.getAttribute("user_id");

		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
					.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityCard> ecList = dcp.getCardEntity();
		DecimalFormat df = new DecimalFormat("#,###");
	
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\t\n");
      out.write("\t<br>\n");
      out.write("\t<div id=\"sub_page\">\n");
      out.write("\t\t<input type=button onclick=\"location.href='./CRD040.jsp'\"\n");
      out.write("\t\t\tvalue=\"+ 新規登録\" class=\"new_button\">\n");
      out.write("\t\t<br>\n");
      out.write("\t\t<table border=\"1\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<th>カード名</th>\n");
      out.write("\t\t\t\t<th>締め日</th>\n");
      out.write("\t\t\t\t<th>支払日</th>\n");
      out.write("\t\t\t\t<th>引き落とし口座</th>\n");
      out.write("\t\t\t\t<th>利用限度額</th>\n");
      out.write("\t\t\t\t<th>利用期限</th>\n");
      out.write("\t\t\t\t<th>状態</th>\n");
      out.write("\t\t\t\t<th></th>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t");

				for (int i = 0; i < ecList.size(); i++) {
					int cardId = ecList.get(i).getCardId();
					String cardName = ecList.get(i).getCardName();
					int cutoffDay = ecList.get(i).getCutoffDay();
					int paymentDay = ecList.get(i).getPaymentDay();
					int paymentMonth = ecList.get(i).getPaymentMonth();
					int accountId = ecList.get(i).getAccountId();
					int paymentLimit = ecList.get(i).getPaymentLimit();
					String strPaymentLimit = df.format(paymentLimit);
					Date timeLimit = ecList.get(i).getTimeLimit();
					int invalidFlg = ecList.get(i).getInvalidFlg();
					String strCutoffDay = null;
					String strPaymentMonth = null;
					String paymentMonthDay = null;
					String strInvalidFlg = null;
					EntityAccount ea = dcp.getAccountEntityByAccountId(accountId);
					String accountName = ea.getAccountName();

					if (paymentMonth == 0) {
						strPaymentMonth = "当月";
					} else if (paymentMonth == 1) {
						strPaymentMonth = "翌月";
					} else if (paymentMonth == 2) {
						strPaymentMonth = "翌々月";
					}

					if (cutoffDay == 0) {
						strCutoffDay = "月末";
					} else {
						strCutoffDay = cutoffDay + "日";
					}

					if (paymentDay == 0) {
						paymentMonthDay = strPaymentMonth + "末";
					} else {
						paymentMonthDay = strPaymentMonth + paymentDay + "日";
					}

					if (invalidFlg == 1) {
						strInvalidFlg = "解約済";
					} else {
						strInvalidFlg = "利用中";
					}
			
      out.write("\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td>");
      out.print(cardName);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(strCutoffDay);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(paymentMonthDay);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(accountName);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(strPaymentLimit);
      out.write("円</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(timeLimit);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(strInvalidFlg);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t<form action=\"./CRD020.jsp\" method=\"post\" style=\"display: inline\">\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" value=\"");
      out.print(cardId);
      out.write("\" name=\"card_id\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"修正\" class=\"upd_button\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t<form action=\"./SvCRD030\" method=\"post\" style=\"display: inline\">\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" value=\"");
      out.print(cardId);
      out.write("\" name=\"card_id\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"削除\" class=\"del_button\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\t\t</table>\n");
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
