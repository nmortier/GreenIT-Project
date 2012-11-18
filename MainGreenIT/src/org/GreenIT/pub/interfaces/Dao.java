package org.GreenIT.pub.interfaces;


import java.sql.Connection;
import java.sql.SQLException;


public abstract class Dao<T> {
	
	   @SuppressWarnings("unused")
	private Connection getConnection() throws SQLException {
           Connection conn;
           conn = ConnectionFactory.getInstance().getConnection();
           return conn;
	   }
		/**
		 * Permet de r�cup�rer un objet via son ID
		 * @param id
		 * @return
		 * @throws SQLException 
		 */
		public abstract T find(long id) throws SQLException;
		
		/**
		 * Permet de cr�er une entr�e dans la base de donn�es
		 * par rapport � un objet
		 * @param obj
		 */
		public abstract T create(T obj);
		
		/**
		 * Permet de mettre � jour les donn�es d'une entr�e dans la base 
		 * @param obj
		 */
		public abstract T update(T obj);
		
		/**
		 * Permet la suppression d'une entr�e de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
		
		public abstract int countRows() throws SQLException;

		
	}

