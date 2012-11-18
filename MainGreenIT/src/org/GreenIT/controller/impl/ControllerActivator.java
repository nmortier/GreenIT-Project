package org.GreenIT.controller.impl;

import java.util.Observable;
import java.util.Observer;

import org.GreenIT.publicAPI.interfaces.IPublicAPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

public class ControllerActivator implements BundleActivator, Observer{

	private static IPublicAPI servicePublic;
	private BundleContext context;
	private ControllerObservable controllerObservable = new ControllerObservable();
	private ControllerThread controllerThread = new ControllerThread();
	
	private int  currentSignal;
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		
		System.out.println("CONTROLER : ControlerActivator start");
		this.context = context;
		this.currentSignal = 3;
		ServiceReference reference = this.context.getServiceReference(IPublicAPI.class.getName());
		ControllerActivator.servicePublic = (IPublicAPI) this.context.getService(reference);
		init(controllerObservable);
	
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("CONTROLER : ControlerActivator stop");
		
	}
	
	void init(ControllerObservable controllerObservable) {
		controllerObservable.addObserver(this); //ajout d'observateur
		controllerThread.setControllerObservable(controllerObservable);
		controllerThread.start();
	  }
	
	@Override
	public void update(Observable o, Object signal) {
		try {
			servicePublic.switchBundle((int)signal);
		} catch (BundleException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
