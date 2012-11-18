package org.GreenIT.publicAPI.impl;

import org.GreenIT.pubImage.interfaces.IPubImage;
import org.GreenIT.pubText.interfaces.IPubText;
import org.GreenIT.pubVideo.interfaces.IPubVideo;
import org.GreenIT.publicAPI.interfaces.IPublicAPI;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class publicAPIActivator implements BundleActivator{

	private IPubText serviceText;
	private IPubVideo serviceVideo;
	private IPubImage serviceImage;
	
	private long bundlePubTextid;
	private long bundlePubVideoid;
	private long bundlePubImageid;
	
	private BundleContext context;
	private Bundle currentBundle;
	
	private ServiceRegistration service;
	
	private IPublicAPI publicService;
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		this.context = context;
		System.out.println("API :publicAPIActivator start");
	
		
		/*****************************************************************/
		/**																**/
		/**						Provided Services						**/
		/**																**/
		/*****************************************************************/
        this.publicService = new PublicAPI(context);       
        this.service = this.context.registerService(IPublicAPI.class.getName(), publicService,null); 
		
        System.out.println("SERVICE 1");
		
		/*****************************************************************/
		/**																**/
		/**						Referenced Services						**/
		/**																**/
		/*****************************************************************/
		
		// Référence vers le service PubText
		ServiceReference reference = this.context.getServiceReference(IPubText.class.getName());
		serviceText = (IPubText) context.getService(reference);
		
		
		bundlePubTextid = reference.getBundle().getBundleId();
		System.out.println("	Bundle PubText id : " + bundlePubTextid);
		
		// Référence vers le service PubImage
		reference = context.getServiceReference(IPubImage.class.getName());
		serviceImage = (IPubImage) context.getService(reference);
		
		bundlePubImageid = reference.getBundle().getBundleId();
		System.out.println("	Bundle PubImage id : " + bundlePubImageid);
		
		// Référence vers le service PubVidéo
		reference = context.getServiceReference(IPubVideo.class.getName());
		serviceVideo = (IPubVideo) context.getService(reference);
		
		bundlePubVideoid = reference.getBundle().getBundleId();
		System.out.println("	Bundle PubVideo id : " + bundlePubVideoid);
		
		
		this.initialize();

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("API : publicAPIActivator stop");
		
	}
	
	public void initialize() throws BundleException
	{
		// On stop tous les bundles.
		Bundle bundleToStop = context.getBundle(this.bundlePubVideoid);
		bundleToStop.stop();
		
		bundleToStop = context.getBundle(this.bundlePubTextid);
		bundleToStop.stop();
		
		bundleToStop = context.getBundle(this.bundlePubImageid);
		bundleToStop.stop();
		
		// On lance le Bundle courrant
		//this.currentBundle = context.getBundle(this.bundlePubTextid);
		//currentBundle.start();
		//this.publicService.setCurrentBundle(currentBundle);
		this.publicService.setBundlesIds(this.bundlePubTextid, this.bundlePubVideoid, this.bundlePubImageid);
		
	}

}
