package org.GreenIT.pubVideo.impl;

import java.sql.SQLException;

import org.GreenIT.pubVideo.interfaces.IPubVideo;

public class PubVideo implements IPubVideo{
	
	VideoDao videoDao= new VideoDao();
	static int taille;
	
	public String getPub() {
		
		
		VideoBean videoBean = null;
		int random = (int)(Math.random() * (getTaille())) + 1; //  /!\TAILLE 
		try {
			videoBean = videoDao.find(random);
			videoDao.incrementStats(random);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ""+videoBean.getPath();
	}

	
	public void nbVideos() throws SQLException
	{
		setTaille(videoDao.countRows());
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		PubVideo.taille = taille;
	}


}
