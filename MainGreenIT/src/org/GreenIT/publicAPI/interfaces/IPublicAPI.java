package org.GreenIT.publicAPI.interfaces;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public interface IPublicAPI 
{
	
	public void switchBundle(int signal) throws BundleException, InterruptedException;
	
	public void setCurrentBundle(Bundle currentBundle);
	public void setBundlesIds(long bundlePubTextid, long bundlePubVideoid, long bundlePubImageid);
	public String getPub();
	public int getTypePub();
}