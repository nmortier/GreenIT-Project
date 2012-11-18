package org.GreenIT.stats.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.GreenIT.pub.interfaces.ConnectionFactory;
import org.GreenIT.pub.interfaces.Dao;
import org.GreenIT.pubVideo.impl.VideoBean;

public class StatsDao extends Dao{

	Connection conn;
	ResultSet resultSet;
	
	public StatsBean getStats(int idCampagne) throws SQLException {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM campagnestats WHERE Campagne_idCampagne="+ idCampagne);
		resultSet.next();
		StatsBean statsBean = new StatsBean(
				resultSet.getLong("Campagne_idCampagne"),
				resultSet.getString("NameCampagne"),
				resultSet.getLong("MediaCount"),
				resultSet.getLong("TextCount"),
				resultSet.getLong("ImageCount"),
				resultSet.getLong("VideoCount")
				);
		resultSet.close();
		conn.close();
		return statsBean;
		
	}

	@Override
	public Object find(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countRows() throws SQLException {
		
		int res;
		
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM campagnestats");
		resultSet.last();
		res = resultSet.getRow();
		resultSet.close();
		conn.close();
		return res;
	}

	public void initialize() throws SQLException {
		conn = ConnectionFactory.getInstance().getConnection();
		conn.createStatement().execute("UPDATE campagnestats SET MediaCount=0, TextCount=0, VideoCount=0, ImageCount=0 ");
		conn.close();
	}



}
