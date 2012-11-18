package org.GreenIT.pubText.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.GreenIT.pub.interfaces.ConnectionFactory;
import org.GreenIT.pub.interfaces.Dao;

public class TextDao extends Dao{

	Connection conn;
	ResultSet resultSet;
	
	@Override
	public TextBean find(long id) throws SQLException {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM text WHERE idText="+ id);
		resultSet.next();
		TextBean textBean = new TextBean(
				resultSet.getLong("idText"), 
				resultSet.getString("Libelle"),
				resultSet.getString("Contenu")
				);
		resultSet.close();
		conn.close();
		return textBean;
	}
	
	public void incrementStats(long id) throws SQLException  {
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT idCampagne FROM campagne WHERE Text_idText="+id);
		resultSet.next();
		long idCampagneText = resultSet.getLong("idCampagne");
		conn.createStatement().execute("UPDATE campagnestats set TextCount = TextCount+1,MediaCount = MediaCount + 1 WHERE Campagne_idCampagne =" +idCampagneText);
		resultSet.close();
		conn.close();
	}
	
	@Override
	public TextBean create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextBean update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public int countRows() throws SQLException {
		int res;
		conn = ConnectionFactory.getInstance().getConnection();
		resultSet = conn.createStatement().executeQuery("SELECT * FROM text");
		resultSet.last();
		res = resultSet.getRow();
		resultSet.close();
		conn.close();
		return res;
	}

}
