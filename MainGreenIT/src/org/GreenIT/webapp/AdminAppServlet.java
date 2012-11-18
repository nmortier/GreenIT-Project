package org.GreenIT.webapp;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.GreenIT.publicAPI.interfaces.IPublicAPI;
import org.GreenIT.stats.interfaces.IStats;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.Component;

@Component(provide=Servlet.class, properties = { "alias=/admin" })
public class AdminAppServlet extends HttpServlet implements BundleActivator{

    /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private BundleContext context;
 private static IPublicAPI publicApiService;
 static IStats statsService;
 

 

 
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
  

	 
	 

  resp.setContentType("text/html");
  PrintWriter out = resp.getWriter();
  out.println("<html>");
  out.println("<head><title> GreenIT - Admin </TITLE><style>p{background-color:white;}</style></head>");
  out.println("<body style=\"background-color:white;\">");
  out.println("<p style=\"color: blue; text-align:center;\">");
  out.println("<img src=\"http://www.haute-performance.fr/Images/pic_articles/Decembre_09/GreenIT.jpg\"/> ");
  out.println("</p>");
  out.println("<div  style=\"text-align:justify; padding:10px; width:700; height:400; overflow:auto; align:center; background-color:rgb(162,250,146); color:rgb(5,186,10); margin:auto; border:solid 1px black;\">");
  out.println("<br/>");
  out.println("<table>" +
		   "<caption style=\" background-color:rgb(162,240,183); \">" +
		   " Statistiques GreenIT" +
		   "</caption>" +
		   
		   "<colgroup>" +
		   "<col span=\"1\" width=\"260\" style=\"background-color:#B8C7D3\" />" +
		   "<col span=\"1\" width=\"155\" style=\"background-color: #CCCCCC\" />" +
		   "<col span=\"1\" width=\"155\" style=\"background-color: #CCCCCC\" />" +
		   "<col span=\"1\" width=\"155\" style=\"background-color: #CCCCCC\" />" +
		   "<col span=\"1\" width=\"155\" style=\"background-color: #CCCCCC\" />" +
		   "</colgroup>" +
		   "<thead colspan=\"2\" rowspan=\"2\">" +
          "<tr colspan=\"2\" rowspan=\"2\">" +
           "<th>"  +
           " Campagne" +
           "</th>" +
           "<th>"  +
           " Total vues" +
           "</th>" +
           "<th>"  +
           " Vues type video" +
           "</th>" +
           "<th>"  +
           " Vues type image" +
           "</th>" +
           "<th>"  +
           " Vues type texte" +
           "</th>" +
       "</tr>" +
   "</thead>" +
   "<tbody align=center colspan=\"2\" rowspan=\"2\">");
  
  try {
	out.println(AdminAppServlet.statsService.getStats());
} catch (SQLException e) {
	e.printStackTrace();
}
  
  
  out.println("</tbody>" +
		  "</table>");
  out.println("<form Method=\"post\" Action=\"http://localhost:8080/admin\">" +
  		"<BR>" +
   		"<INPUT type=submit value=Réinitialiser>" +
  		"</form>");
  out.println("</div>");
  out.println("</body>");
  out.println("</html>");
  out.close();
  
  
  
 }
 
 public void  doPost ( HttpServletRequest  req, HttpServletResponse  resp) throws ServletException, IOException {
	 
	  resp.setContentType("text/html");
	  PrintWriter out = resp.getWriter();
	  
	  try {
		AdminAppServlet.statsService.initialize();
		doGet(req,resp);
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }


@Override
public void start(BundleContext context) throws Exception {
	System.out.println("Admin WEB start");
	this.context = context;
	ServiceReference reference = this.context.getServiceReference(IPublicAPI.class.getName());
	AdminAppServlet.publicApiService = (IPublicAPI) this.context.getService(reference);
	reference = this.context.getServiceReference(IStats.class.getName());
	AdminAppServlet.statsService = (IStats) this.context.getService(reference);
	
	
}

@Override
public void stop(BundleContext context) throws Exception {
	System.out.println("WEB Stop");
	
}


}