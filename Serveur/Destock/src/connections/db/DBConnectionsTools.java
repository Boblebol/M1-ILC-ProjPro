package connections.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbSettings.DBException;
import dbSettings.DataBase;

public class DBConnectionsTools {


	/**
	 * Teste si pseudoUtilisateur est dans la base de donnee connection
	 * @param pseudoUtilisateur : pseudoUtilisateur
	 * @return un boolean
	 */
	public static boolean pseudoUtilisateurExistance(String pseudoUtilisateur) throws DBException {
		try {
			String query = "SELECT * FROM connections WHERE pseudoUtilisateur=\"" + pseudoUtilisateur + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return bool;
		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.pseudoUtilisateurExistance : " + e.getMessage());
		}
	}


	/**
	 * creer une ligne de connection dans la base
	 * @param pseudoUtilisateur : pseudoUtilisateur
	 * @return un boolean
	 */
	public static boolean createConnection (String pseudoUtilisateur) throws DBException {
		String query = "INSERT INTO `connections`(`idConnections`, `pseudoUtilisateur`, `clefDeLog`, `utilisateurConnecte`) VALUES (null,\""+ pseudoUtilisateur +"\",\"\",0)";
		boolean bool;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(query);
			bool = pseudoUtilisateurExistance(pseudoUtilisateur);
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.createAuthentification : " + e.getMessage());
		}

		return bool;
	}


	/**
	 * on attribut une clef a un pseudoUtilisateur
	 * @param pseudoUtilisateur 
	 * @param clefDeLog 
	 * @return la clef
	 */
	public static String setKey (String pseudoUtilisateur,String clefDeLog) throws DBException {
		String query = "SELECT clefDeLog FROM connections WHERE pseudoUtilisateur=\"" + pseudoUtilisateur + "\" AND clefDeLog=\"\"";
		String query2 = "UPDATE `connections` SET `clefDeLog`=\""+clefDeLog+"\" WHERE pseudoUtilisateur=\""+pseudoUtilisateur+"\"";
		String clefDeLogret="";
		//on regarde si la clefDeLog est ""
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next())
				clefDeLogret=rs.getString("clefDeLog");;
				s.close();
				c.close();
		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.setKey : " + e.getMessage());
		}

		//si la clefDeLog est "" on la change 
		if (clefDeLogret.equals("")) {
			try {
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();
				s.executeUpdate(query2);
				s.close();
				c.close();
			} catch (SQLException e) {
				throw new DBException("connections.db.DBConnectionsTools.setKey : " + e.getMessage());
			}
			return clefDeLog;
		}else {
			return clefDeLogret;
		}

	}


	/**
	 * connecte un utilisateur
	 * @param pseudoUtilisateur 
	 * @return boolean
	 */
	public static boolean connection (String pseudoUtilisateur) throws DBException {
		boolean bool;
		String query ="UPDATE `connections` SET `utilisateurConnecte`=1 WHERE pseudoUtilisateur=\""+pseudoUtilisateur+"\"";
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(query);
			bool = pseudoUtilisateurExistance(pseudoUtilisateur);
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.connection : " + e.getMessage());
		}
		return bool;
	}


	/**
	 * regarde si un tutilisateur est connecte
	 * @param pseudoUtilisateur 
	 * @return boolean
	 */
	public static boolean estConnecte(String pseudoUtilisateur) throws DBException {
		String query = "SELECT * FROM connections WHERE pseudoUtilisateur=\"" + pseudoUtilisateur + "\" AND utilisateurConnecte = 1 ";
		try{
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);

			ResultSet rs = s.getResultSet();
			boolean bool = rs.next();

			rs.close();
			s.close();
			c.close();
			return bool;

		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.estConnecte : " + e.getMessage());
		}
	}


	/**
	 * regarde si une clef est connecte
	 * @param clefDeLog
	 * @return boolean
	 */
	public static boolean checkKey(String clefDeLog) throws DBException {
		try {
			boolean bool = false;
			String string = "SELECT * FROM connections WHERE clefDeLog=\"" + clefDeLog
					+ "\" and utilisateurConnecte=1";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				bool = true;

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;

		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.checkKey : " + e.getMessage());
		}
	}


	/**
	 * recupere le pseudo en fonction de la clef
	 * @param clefDeLog
	 * @return pseudo
	 */
	public static String getPseudo(String clefDeLog) throws DBException {
		String query = "SELECT pseudo FROM connections WHERE clefDeLog=\"" + clefDeLog+ "\" ";
		String pseudo = "";
		try{
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next()){
				pseudo=rs.getString("PpseudoUtilisateur");
			}
		} catch (SQLException e) {
			throw new DBException("connections.db.DBConnectionsTools.getPseudo : " + e.getMessage());
		}
		return pseudo;
	}


	/**
	 * recupere l'idConnections de la connection
	 * @param clefDeLog
	 * @return idUtilisateur
	 */
	public static int getIdUtilisateur (String clefDeLog) throws DBException {

		String pseudo = getPseudo(clefDeLog);
		if (pseudo != null) {
			String query = "SELECT idUtilisateur FROM utilisateurs WHERE pseudoUtilisateur = \""+pseudo+"\"";	
			int idUtilisateur = 0 ;
			try{
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();
				s.executeQuery(query);
				ResultSet rs = s.getResultSet();
				while(rs.next()){
					idUtilisateur=rs.getInt(" idUtilisateur");
				}
			} catch (SQLException e) {
				throw new DBException("connections.db.DBConnectionsTools.getIdUtilisateur : " + e.getMessage());
			}
			return  idUtilisateur;

		}
		return -1;
	}

}