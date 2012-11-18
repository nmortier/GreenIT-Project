package org.GreenIT.pubText.impl;

import org.GreenIT.pubText.interfaces.IPubText;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceRegistration;

public class pubTextActivator implements BundleActivator, BundleListener {


	private BundleContext ctx;	
	private ServiceRegistration service;
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		System.out.println("TEXT : pubTextActivator start");	
		this.ctx = context; 
        
		/*****************************************************************/
		/**																**/
		/**						Provided Services						**/
		/**																**/
		/*****************************************************************/
        // Mise en oeuvre du service PubText
        IPubText pubTextService = new PubText();       
        this.service = this.ctx.registerService(IPubText.class.getName(), pubTextService,null); 
        
        System.out.println(IPubText.class.getName() + " Is Registered");
        
        PubText pubText = new PubText();
        pubText.nbTexts();
	}
	

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		System.out.println("TEXT : pubTextActivator stop");
		System.out.println("		Unregister service : " + service.toString());
		service.unregister();
	}


	@Override
	public void bundleChanged(BundleEvent event) {
	       final Bundle bundle = event.getBundle(); 

	        System.out.println("SIGNAL" + bundle.getSymbolicName() + " - " 
	+ event.getType()); 
		
	}

}
