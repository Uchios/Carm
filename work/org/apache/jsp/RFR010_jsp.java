/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2017-05-07 14:27:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.DecimalFormat;
import Carm.GpDateConversion;
import java.util.ArrayList;
import java.util.List;
import Carm.EntityUse;
import Carm.EntityCard;
import Carm.DaoCarmPersonal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public final class RFR010_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=UTF-8>\n");
      out.write("<title>クレジットカード管理</title>\n");
      out.write("<link href=\"./Main.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t");

		request.setAttribute("page_name", "▶︎ 利用明細︎");	
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");

		Integer sumPayment = (Integer) request.getAttribute("sum_payment");

		List<EntityUse> euList = (ArrayList<EntityUse>) request
				.getAttribute("eu_list");
		List<EntityCard> ecList = (ArrayList<EntityCard>) request
				.getAttribute("ec_list");
		Date endDate = (Date) request.getAttribute("end_date");
		Date startDate = (Date) request.getAttribute("start_date");

		GpDateConversion gdc1 = new GpDateConversion(startDate);
		GpDateConversion gdc2 = new GpDateConversion(endDate);

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		DecimalFormat df = new DecimalFormat("#,###");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E)");
	
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\t\n");
      out.write("\t<div id=\"sub_page\">\n");
      out.write("\t\t");

			if (alert != null) {
		
      out.write("\n");
      out.write("\t\t<div class=\"alert\">");
      out.print(alert);
      out.write("</div>\n");
      out.write("\t\t");

			}
		
      out.write("\n");
      out.write("\t\t<br>利用日時の表示範囲↓\n");
      out.write("\t\t<form action=\"./SvRFR010\" method=\"post\">\n");
      out.write("\t\t\t<input type=\"text\" name=\"start_year\" value=");
      out.print(gdc1.year);
      out.write("\n");
      out.write("\t\t\t\tclass=\"sForm\">\n");
      out.write("\t\t\t年\n");
      out.write("\t\t\t<input type=\"text\" name=\"start_month\" value=");
      out.print(gdc1.month);
      out.write("\n");
      out.write("\t\t\t\tclass=\"sForm\">\n");
      out.write("\t\t\t月\n");
      out.write("\t\t\t<input type=\"text\" name=\"start_day\" value=");
      out.print(gdc1.day);
      out.write(" class=\"sForm\">\n");
      out.write("\t\t\t日 〜\n");
      out.write("\t\t\t<input type=\"text\" name=\"end_year\" value=");
      out.print(gdc2.year);
      out.write(" class=\"sForm\">\n");
      out.write("\t\t\t年\n");
      out.write("\t\t\t<input type=\"text\" name=\"end_month\" value=");
      out.print(gdc2.month);
      out.write("\n");
      out.write("\t\t\t\tclass=\"sForm\">\n");
      out.write("\t\t\t月\n");
      out.write("\t\t\t<input type=\"text\" name=\"end_day\" value=");
      out.print(gdc2.day);
      out.write(" class=\"sForm\">\n");
      out.write("\t\t\t日 <br> <br> カードを絞り込む <SELECT name=\"card_id\"\n");
      out.write("\t\t\t\tonChange=\"this.form.submit()\">\n");
      out.write("\t\t\t\t<option value=\"-1\"></option>\n");
      out.write("\t\t\t\t<option value=\"-1\">全て</option>\n");
      out.write("\t\t\t\t");

					for (int j = 0; j < ecList.size(); j++) {
				
      out.write("\n");
      out.write("\t\t\t\t<option value=\"");
      out.print(ecList.get(j).getCardId());
      out.write('"');
      out.write('>');
      out.print(ecList.get(j).getCardName());
      out.write("\n");
      out.write("\t\t\t\t</option>\n");
      out.write("\t\t\t\t");

					}
				
      out.write("\n");
      out.write("\t\t\t</SELECT>\n");
      out.write("\t\t</form>\n");
      out.write("\t\t<br> 【利用合計】 <span class=\"sum\">");
      out.print(df.format(sumPayment));
      out.write("円</span>\n");
      out.write("\t\t<table border=\"1\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<th>利用日時</th>\n");
      out.write("\t\t\t\t<th>摘要</th>\n");
      out.write("\t\t\t\t<th>支払金額</th>\n");
      out.write("\t\t\t\t<th>カード</th>\n");
      out.write("\t\t\t\t<th>引き落とし予定日</th>\n");
      out.write("\t\t\t\t<th></th>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t");

				for (int i = 0; i < euList.size(); i++) {
					int useId = euList.get(i).getUseId();
					Date useDate = euList.get(i).getUseDate();
					String strUseDate = sdf.format(useDate);
					String summary = euList.get(i).getSummary();
					int payment = euList.get(i).getPayment();
					String strPayment = df.format(payment);
					int cardId = euList.get(i).getCardId();
					String useCardName = dcp.getCardEntityByCardId(cardId)
							.getCardName();
					Date paymentDate = euList.get(i).getPaymentDate();
					String strPaymentDate = sdf.format(paymentDate);
			
      out.write("\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td>");
      out.print(strUseDate);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(summary);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(strPayment);
      out.write("円</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(useCardName);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print(strPaymentDate);
      out.write("</td>\n");
      out.write("\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t<form action=\"./RFR020.jsp\" method=\"post\" style=\"display: inline\">\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" value=\"");
      out.print(useId);
      out.write("\" name=\"use_id\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"修正\" class=\"upd_button\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t<form action=\"./SvRFR030\" method=\"post\" style=\"display: inline\">\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" value=\"");
      out.print(useId);
      out.write("\" name=\"use_id\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"削除\" class=\"del_button\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\t\t</table>\n");
      out.write("\n");
      out.write("\t\t<br> <br> <br> <br> <a href=\"./CMM030.jsp\">ホーム</a>\n");
      out.write("\t</div>\n");
      out.write("\n");
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