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
		 * Permet de récupérer un objet via son ID
		 * @param id
		 * @return
		 * @throws SQLException 
		 */
		public abstract T find(long id) throws SQLException;
		
		/**
		 * Permet de créer une entrée dans la base de données
		 * par rapport à un objet
		 * @param obj
		 */
		public abstract T create(T obj);
		
		/**
		 * Permet de mettre à jour les données d'une entrée dans la base 
		 * @param obj
		 */
		public abstract T update(T obj);
		
		/**
		 * Permet la suppression d'une entrée de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
		
		public abstract int countRows() throws SQLException;

		
	}

