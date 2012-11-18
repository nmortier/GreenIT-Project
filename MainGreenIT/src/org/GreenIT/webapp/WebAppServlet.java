package org.GreenIT.webapp;



import java.io.IOException;
import java.io.PrintWriter;

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

@Component(provide=Servlet.class, properties = { "alias=/" })
public class WebAppServlet extends HttpServlet implements BundleActivator{

    /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private BundleContext context;
 static IPublicAPI publicApiService;
 
 

 
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
    
  resp.setContentType("text/html");
  PrintWriter out = resp.getWriter();
  out.println("<HTML>");
  out.println("<HEAD><TITLE> GreenIT - Publicité </TITLE><style>p{background-color:white;}</style></HEAD>");
  out.println("<BODY style=\"background-color:white;\">");
  out.println("<p style=\"color: blue; text-align:center;\">");
  out.println("<img src=\"http://www.haute-performance.fr/Images/pic_articles/Decembre_09/GreenIT.jpg\"/> ");
  out.println("</p>");
  out.println("<div  style=\"text-align:justify; padding:10px; width:700; height:400; overflow:auto; align:center; background-color:rgb(162,250,146); color:rgb(5,186,10); margin:auto; border:solid 1px black;\">");
  
  switch (WebAppServlet.publicApiService.getTypePub()) {

  case 1: //TEXT
	  out.println(WebAppServlet.publicApiService.getPub());
  break;

  case 2: //IMAGE
	  ImageServlet.setPath(WebAppServlet.publicApiService.getPub());
	  out.println("<img width=690 height=390 src=\""+ "image"+ "\"/> ");
  break;

  case 3: //VIDEO
	  VideoServlet.setPath(WebAppServlet.publicApiService.getPub());
	  out.println("<object>" +
				"<embed width=690 height=390 type=\"application/x-mplayer2\" pluginspage = \"http://www.microsoft.com/Windows/MediaPlayer/\" " +
				"SRC=\"video\"  AutoPlay=\"true\" " +
				"autosize=\"1\" transparentatStart=\"false\" animationatStart=\"true\" showControls=\"true\"/> " +
				"</object");
  break;

  default: 
  out.println("erreur");
  }
  
  out.println("</div>");
  out.println("</BODY>");
  out.println("</HTML>");
  out.close();
  
  
 }


@Override
public void start(BundleContext context) throws Exception {
	System.out.println("WEB start");
	this.context = context;
	
	ServiceReference reference = this.context.getServiceReference(IPublicAPI.class.getName());
	System.out.println(reference);
	WebAppServlet.publicApiService = (IPublicAPI) this.context.getService(reference);
	
	AdminAppServlet app = new AdminAppServlet();
	app.start(context);
	ImageServlet imgServlet = new ImageServlet();
	imgServlet.start(context);
	VideoServlet videoServlet = new VideoServlet();
	videoServlet.start(context);
}

@Override
public void stop(BundleContext context) throws Exception {
	System.out.println("WEB Stop");
	
}

}