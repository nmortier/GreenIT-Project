package org.GreenIT.controller.impl;

import java.util.Observable;

public class ControllerObservable extends Observable{

	public static final int START_BUNDLE_TEXT= 1;
	public static final int START_BUNDLE_IMAGE = 2;
	public static final int START_BUNDLE_VIDEO = 3;
	
	public static final String BUNDLE_TEXT = "TEXT";
	public static final String BUNDLE_VIDEO = "VIDEO";
	public static final String BUNDLE_IMAGE = "IMAGE";
	
	public void changeBandle(String TypeBundle) {
		int signal = 3;
		
		if(TypeBundle.equals(BUNDLE_TEXT))
		{
			signal = START_BUNDLE_TEXT;
		}
		else if (TypeBundle.equals(BUNDLE_IMAGE))
		{
			signal = START_BUNDLE_IMAGE;
		}
		else if (TypeBundle.equals(BUNDLE_VIDEO))
		{
			signal = START_BUNDLE_VIDEO;
		}
		
		setChanged();
		notifyObservers(signal);
	}

}
