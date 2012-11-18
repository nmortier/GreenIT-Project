package org.GreenIT.controller.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ControllerThread extends Thread{
	
	ControllerObservable controllerObservable;

	
	public void run() {

		while (true){
			try {
				BufferedReader lecteurAvecBuffer = null;
				String ligne;
				FileReader FileReader = new FileReader(
						new File("D:\\Projets\\GreenIT\\MainGreenIT\\wildcat\\State.txt"));

				lecteurAvecBuffer = new BufferedReader(FileReader);
				while ((ligne = lecteurAvecBuffer.readLine()) != null)
				{
					
					controllerObservable.changeBandle(ligne);
					System.out.println(ligne);
				}
				lecteurAvecBuffer.close();
				// Wait 1s	
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println("Exception: "+ e);
			}
			
		}
	}
	
	public void setControllerObservable(ControllerObservable myControllerObservable) {
		controllerObservable = myControllerObservable;
	}
}
