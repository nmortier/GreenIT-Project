package org.GreenIT.pubText.impl;

import java.sql.SQLException;

import org.GreenIT.pubText.interfaces.IPubText;

public class PubText implements IPubText {

	TextDao textDao= new TextDao();
	static int taille;
	
	public String getPub() {
		
		
		TextBean textBean = null;
		int random = (int)(Math.random() * (getTaille())) + 1;
		try {
			textBean = textDao.find(random);
			textDao.incrementStats(random);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return textBean.getContenu();
	}
	
	public void nbTexts() throws SQLException
	{
		setTaille(textDao.countRows());
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		PubText.taille = taille;
	}

	
}
