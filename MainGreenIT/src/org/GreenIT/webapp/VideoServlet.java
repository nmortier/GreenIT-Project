package org.GreenIT.webapp;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.GreenIT.publicAPI.interfaces.IPublicAPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.Component;

@Component(provide=Servlet.class, properties = { "alias=/video" })
public class VideoServlet extends HttpServlet implements BundleActivator{

    /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private BundleContext context;
 private static IPublicAPI publicApiService;
 private static String path= "";
 



@Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
  

	 	 
	 resp.setContentType("video/mp4");  
	 OutputStream out = resp.getOutputStream();  
	 FileInputStream in = new FileInputStream(getPath());  
	 int size = in.available();  
	 byte[] content = new byte[size];  
	 in.read(content);  
	 out.write(content);  
	 in.close();  
	 out.close();
  
  
 }


@Override
public void start(BundleContext context) throws Exception {
	System.out.println("VideoServlet OK");
	this.context = context;
	ServiceReference reference = this.context.getServiceReference(IPublicAPI.class.getName());
	VideoServlet.publicApiService = (IPublicAPI) this.context.getService(reference);
	
	
}

@Override
public void stop(BundleContext context) throws Exception {
	System.out.println("WEB Stop");
	
}

public static String getPath() {
	return path;
}


public static void setPath(String videoPath) {
	path = videoPath;
}

}