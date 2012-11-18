package org.GreenIT.publicAPI.impl;

import org.GreenIT.pub.interfaces.IPub;
import org.GreenIT.pubImage.interfaces.IPubImage;
import org.GreenIT.pubText.interfaces.IPubText;
import org.GreenIT.pubVideo.interfaces.IPubVideo;
import org.GreenIT.publicAPI.interfaces.IPublicAPI;
import org.GreenIT.publicAPI.interfaces.IPublicAPIServlet;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

public class PublicAPI implements IPublicAPI, IPublicAPIServlet 
{
	public static final int START_BUNDLE_TEXT= 1;
	public static final int START_BUNDLE_IMAGE = 2;
	public static final int START_BUNDLE_VIDEO = 3;
	
	public static final String BUNDLE_TEXT = IPubText.class.getName();
	public static final String BUNDLE_VIDEO = IPubVideo.class.getName();
	public static final String BUNDLE_IMAGE= IPubImage.class.getName();


	private BundleContext context;
	private Bundle currentBundle;
	
	private long bundlePubTextid;
	private long bundlePubImageid;
	private long bundlePubVideoid;
	
	private String bundleNameToStart="";
	
	private IPub serviceIPub;


	public PublicAPI(BundleContext context)
	{
		this.context = context;
	}
	
	
	@Override
	public void switchBundle(int signal) throws BundleException, InterruptedException 
	{
		
		Bundle bundleToStop = this.currentBundle;
		Bundle bundleToStart = null;
		String bundleNameToStart ="";
		// Put the bundle to start
		
		System.out.println("switchBundle start     , Signal :" + signal);

		if(signal == START_BUNDLE_TEXT)
		{
			bundleToStart = this.context.getBundle(bundlePubTextid);
			bundleNameToStart= BUNDLE_TEXT;
		}
		else if (signal == START_BUNDLE_IMAGE)
		{
			bundleToStart = this.context.getBundle(bundlePubImageid);
			bundleNameToStart= BUNDLE_IMAGE;
		}
		else if (signal == START_BUNDLE_VIDEO)
		{
			bundleToStart = this.context.getBundle(bundlePubVideoid);
			bundleNameToStart= BUNDLE_VIDEO;
		}
		
		
		// Start the bundle
		if(bundleToStop!=null && bundleToStart != null && bundleToStart.getBundleId() != bundleToStop.getBundleId()
				
				&& (bundleToStart.getBundleId() == bundlePubTextid 
				|| bundleToStart.getBundleId() == bundlePubImageid 
				|| bundleToStart.getBundleId() == bundlePubVideoid))
		{
			bundleToStart.start();
			System.out.println("START !!");
			setCurrentBundle(bundleToStart);

			ServiceReference reference = this.context.getServiceReference(bundleNameToStart);
			this.serviceIPub = (IPub) this.context.getService(reference);
			bundleToStop.stop();
			System.out.println("START !!");
		}
		else if (bundleToStop == null && bundleToStart != null)
		{
			bundleToStart.start();
			System.out.println("START !!");
			setCurrentBundle(bundleToStart);

			ServiceReference reference = this.context.getServiceReference(bundleNameToStart);
			this.serviceIPub = (IPub) this.context.getService(reference);
		}
	}
	
	
	public void setCurrentBundle(Bundle currentBundle)
	{
		this.currentBundle = currentBundle;
		
		if (currentBundle.toString().contains("pubText"))
		{
			bundleNameToStart = IPubText.class.getName();
		}
		else if (currentBundle.toString().contains("pubImage"))
		{
			bundleNameToStart = IPubImage.class.getName();
		}
		else if (currentBundle.toString().contains("pubVideo"))
		{
			bundleNameToStart = IPubVideo.class.getName();
		}
		else
		{
			bundleNameToStart= "Erreur";
		}
		
		System.out.println("fregtrhrth"+this.currentBundle == null);
		
		
	}


	@Override
	public void setBundlesIds(long bundlePubTextid, long bundlePubVideoid, long bundlePubImageid) 
	{
		this.bundlePubTextid = bundlePubTextid;
		this.bundlePubVideoid = bundlePubVideoid;
		this.bundlePubImageid = bundlePubImageid;
	}


	@Override
	public String getPub() {
		//ServiceReference reference = this.context.getServiceReference(bundleNameToStart);
		//this.serviceIPub = (IPub) this.context.getService(reference);
		return this.serviceIPub.getPub();
	}


	@Override
	public int getTypePub() {

		int type=0;
		
		if (bundleNameToStart==BUNDLE_TEXT)
		{
			type=1;
			
		}
		else if (bundleNameToStart==BUNDLE_IMAGE)
		{
			type=2;
			
		}
		else if (bundleNameToStart==BUNDLE_VIDEO)
		{
			type=3;
			
		}
		return type;
	}

}
