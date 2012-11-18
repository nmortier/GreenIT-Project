package org.GreenIT.stats.impl;

import java.sql.SQLException;

import org.GreenIT.stats.interfaces.IStats;

public class Stats implements IStats{

	StatsDao statsDao = new StatsDao();
	static int nbCampagne=0;
	
	public String getStats() throws SQLException {
		
		String stringRes="";
		StatsBean stats = null;
		
		for (int i=1; i<=nbCampagne;i++)
		{
			stats = statsDao.getStats(i);
			stringRes +="<tr colspan=\"2\" rowspan=\"2\">" +
			           "<td>"  +
			           stats.getNameCampagne() +
			           "</td>" +
			           "<td>"  +
			           stats.getMediaCount() +
			           "</td>" +
				        "<td>"  +
				        stats.getVideoCount() +
				        "</td>" +
				        "<td>"  +
				        stats.getImageCount() +
				        "</td>" +
				        "<td>"  +
				        stats.getTextCount() +
				        "</td>" +
				        "</tr>";			    
		}
		return stringRes;
	}
	
	public void nbStats() throws SQLException {
		setNbStats(statsDao.countRows());
	}

	public static int getNbStats() {
		return nbCampagne;
	}

	public static void setNbStats(int nbCampagne) {
		Stats.nbCampagne = nbCampagne;
	}

	@Override
	public void initialize() throws SQLException {
		statsDao.initialize();
	}
}
