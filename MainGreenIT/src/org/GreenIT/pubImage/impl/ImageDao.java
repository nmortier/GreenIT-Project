package org.GreenIT.pubImage.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.GreenIT.pub.interfaces.ConnectionFactory;
import org.GreenIT.pub.interfaces.Dao;

public class ImageDao extends Dao{

	static Connection conn;
	static ResultSet resultSet;
	
	@Override
	public ImageBean find(long id) throws SQLException {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM image WHERE idImage="+ id);
		resultSet.next();
		ImageBean imageBean = new ImageBean(
				resultSet.getLong("idImage"),
				resultSet.getString("pathImage")
				);
		resultSet.close();
		conn.close();
		return imageBean;
	}

	public void incrementStats(long id) throws SQLException  {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT idCampagne FROM campagne WHERE Image_idImage="+id);
		resultSet.next();
		long idCampagneImage = resultSet.getLong("idCampagne");
		conn.createStatement().execute("UPDATE campagnestats set ImageCount = ImageCount+1,MediaCount = MediaCount + 1 WHERE Campagne_idCampagne =" +idCampagneImage);
		resultSet.close();
		conn.close();
	}
	
	
	@Override
	public ImageBean create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageBean update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	public int countRows() throws SQLException
	{
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM image");
		resultSet.last();
		return resultSet.getRow();
	}


}
