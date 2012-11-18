package org.GreenIT.pubImage.impl;

import java.sql.SQLException;

import org.GreenIT.pubImage.interfaces.IPubImage;

public class PubImage implements IPubImage{
	
	static int taille;
	ImageDao imageDao= new ImageDao();
	
	public String getPub() {
		
		
		ImageBean imageBean = null;
		int random = (int)(Math.random() * (getTaille())) + 1; //  /!\TAILLE 
		try {
			imageBean = imageDao.find(random);
			imageDao.incrementStats(random);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ""+imageBean.getPath();
	}
	
	public void nbImages() throws SQLException
	{
		setTaille(imageDao.countRows());
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

}
