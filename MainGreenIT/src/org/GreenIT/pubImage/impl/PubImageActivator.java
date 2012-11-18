package org.GreenIT.pubImage.impl;

import org.GreenIT.pubImage.interfaces.IPubImage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PubImageActivator implements BundleActivator {


	private BundleContext ctx;	
	private ServiceRegistration service;
	private PubImage pubImage = new PubImage();
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		System.out.println("IMAGE : pubImageActivator start");		
		this.ctx = context; 

		/*****************************************************************/
		/**																**/
		/**						Provided Services						**/
		/**																**/
		/*****************************************************************/
		IPubImage pubImageService= new PubImage();
        this.service =this.ctx.registerService(IPubImage.class.getName(), pubImageService, null); 
        
        System.out.println(IPubImage.class.getName() + " Is Registered");
        
        //Calcul du nombre d'images disponbiles.
        pubImage.nbImages();

	}
	

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		System.out.println("IMAGE : pubImageActivator stop");
		System.out.println("		Unregister service : " + service.toString());
		service.unregister();
	}

}
