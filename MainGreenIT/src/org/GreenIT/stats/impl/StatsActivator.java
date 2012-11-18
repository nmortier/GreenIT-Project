package org.GreenIT.stats.impl;

import org.GreenIT.stats.interfaces.IStats;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class StatsActivator implements BundleActivator {


	private BundleContext ctx;	
	private ServiceRegistration service;
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		System.out.println("STATS : statsActivator start");		
		this.ctx = context; 

		/*****************************************************************/
		/**																**/
		/**						Provided Services						**/
		/**																**/
		/*****************************************************************/
		IStats statsService= new Stats();
        this.service =this.ctx.registerService(IStats.class.getName(), statsService,null); 
        
        System.out.println(IStats.class.getName() + " Is Registered");
        
        Stats stats = new  Stats();
        stats.nbStats();
	}
	

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		System.out.println("STATS : statsActivator stop");
		System.out.println("		Unregister service : " + service.toString());
		service.unregister();
	}

}
