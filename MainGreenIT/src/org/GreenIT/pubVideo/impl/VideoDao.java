package org.GreenIT.pubVideo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.GreenIT.pub.interfaces.ConnectionFactory;
import org.GreenIT.pub.interfaces.Dao;

public class VideoDao extends Dao{

	Connection conn;
	ResultSet resultSet;
	
	@Override
	public VideoBean find(long id) throws SQLException {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM video WHERE idVideo="+ id);
		resultSet.next();
		VideoBean videoBean = new VideoBean(
				resultSet.getLong("idVideo"),
				resultSet.getString("pathVideo")
				);
		resultSet.close();
		conn.close();
		return videoBean;
	}

	public void incrementStats(long id) throws SQLException  {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT idCampagne FROM campagne WHERE Video_idVideo="+id);
		resultSet.next();
		long idCampagneVideo = resultSet.getLong("idCampagne");
		conn.createStatement().execute("UPDATE campagnestats set VideoCount = VideoCount+1,MediaCount = MediaCount + 1 WHERE Campagne_idCampagne =" +idCampagneVideo);
		resultSet.close();
		conn.close();
	}
	
	
	@Override
	public VideoBean create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VideoBean update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public int countRows() throws SQLException
	{
		int res;
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM video");
		resultSet.last();
		res = resultSet.getRow();
		resultSet.close();
		conn.close();
		return res;
	}


}
