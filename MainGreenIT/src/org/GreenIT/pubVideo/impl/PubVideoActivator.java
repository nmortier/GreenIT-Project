package org.GreenIT.pubVideo.impl;

import org.GreenIT.pubVideo.interfaces.IPubVideo;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PubVideoActivator implements BundleActivator {


	private BundleContext ctx;	
	private ServiceRegistration service;
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		System.out.println("VIDEO : pubVideoActivator start");		
		this.ctx = context; 

		/*****************************************************************/
		/**																**/
		/**						Provided Services						**/
		/**																**/
		/*****************************************************************/
		IPubVideo pubVideoService= new PubVideo();
        this.service =this.ctx.registerService(IPubVideo.class.getName(), pubVideoService,null); 
        
        System.out.println(IPubVideo.class.getName() + " Is Registered");

        PubVideo pubVideo = new PubVideo();
        pubVideo.nbVideos();
	}
	

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		System.out.println("VIDEO : pubVideoActivator stop");
		System.out.println("		Unregister service : " + service.toString());
		service.unregister();
	}

}
