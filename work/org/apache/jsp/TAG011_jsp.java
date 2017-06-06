/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2017-06-05 16:03:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Carm.EntityCard;
import Carm.EntityAnalyticalData;
import Carm.DaoCarmPersonal;
import java.util.List;
import java.util.ArrayList;
import Carm.GpDateConversion;
import java.sql.Date;

public final class TAG011_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>クレジットカード管理システム</title>\n");
      out.write("<link href=\"./Main.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t");

		request.setAttribute("page_name", "▶︎ タグを金額で比較");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
		List<EntityAnalyticalData>eadList = (ArrayList<EntityAnalyticalData>)request.getAttribute("ead_list");
		List<EntityCard>ecList = (ArrayList<EntityCard>)request.getAttribute("ec_list");
		String cardName = (String)request.getAttribute("card_name");
		Date endDate = (Date) request.getAttribute("end_date");
		Date startDate = (Date) request.getAttribute("start_date");

		GpDateConversion gdc1 = new GpDateConversion(startDate);
		GpDateConversion gdc2 = new GpDateConversion(endDate);
		
		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
			.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}
	
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\n");
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
      out.write("\t\t<br>集計範囲↓\n");
      out.write("\t\t<form action=\"./SvTAG010\" method=\"post\">\n");
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
      out.write("\t\t\t日 <br> <br> カード別に集計する <SELECT name=\"card_id\"\n");
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
      out.write("\t\t</div>\n");
      out.write("\t<!--Load the AJAX API-->\n");
      out.write("    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("   \n");
      out.write("      // Load the Visualization API and the piechart package.\n");
      out.write("      google.load('visualization', '1.0', {'packages':['corechart']});\n");
      out.write("     \n");
      out.write("      // Set a callback to run when the Google Visualization API is loaded.\n");
      out.write("      google.setOnLoadCallback(drawChart);\n");
      out.write("     \n");
      out.write("      // Callback that creates and populates a data table,\n");
      out.write("      // instantiates the pie chart, passes in the data and\n");
      out.write("      // draws it.\n");
      out.write("      function drawChart() {\n");
      out.write("\n");
      out.write("      // Create the data table.\n");
      out.write("      var data = new google.visualization.DataTable();\n");
      out.write("      data.addColumn('string', 'Topping');\n");
      out.write("      data.addColumn('number', 'Slices');\n");
      out.write("      data.addRows([\n");
      out.write("        ");
for(int i = 0 ; i< eadList.size() ; i++){
      out.write("\n");
      out.write("        ['");
      out.print(eadList.get(i).getTagName());
      out.write('\'');
      out.write(',');
      out.write(' ');
      out.print(eadList.get(i).getPayment());
      out.write("],\n");
      out.write("        ");
}
      out.write("\n");
      out.write("      ]);\n");
      out.write("\n");
      out.write("      // Set chart options\n");
      out.write("      var options = {'title':'金額別比較 （");
      out.print(cardName);
      out.write("）',\n");
      out.write("                     'width':600,\n");
      out.write("                     'height':400};\n");
      out.write("\n");
      out.write("      // Instantiate and draw our chart, passing in some options.\n");
      out.write("      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));\n");
      out.write("      chart.draw(data, options);\n");
      out.write("    }\n");
      out.write("    </script>\n");
      out.write("\t<div id=\"chart_div\"></div>\n");
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
