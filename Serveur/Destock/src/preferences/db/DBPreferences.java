package preferences.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBPreferences {
	
	/**
	 * Ajoute une préférence magasin à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static void AddPrefMagasin (int idClient,int idMagasin) throws DBException, SQLException {
		try {
			// Requete
			String requete = "INSERT INTO `AssociationUtilisateurPromotion`(`idClient`, `magasin`) VALUES ("+idClient+","+idMagasin+")";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPreferences.AddPrefMagasin : " + e.getMessage());
		}
	}
	
	/**
	 * Supprime une préférence magasin à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static void DelPrefMagasin (int idClient,int idMagasin) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `AssociationUtilisateurPromotion` WHERE `idClient`="+idClient+" AND `magasin`="+idMagasin+"";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPreferences.DelPrefMagasin : " + e.getMessage());
		}
	}
	
	/**
	 * Ajoute une préférence catégorie à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static void AddPrefCat (int idClient,String categorie) throws DBException, SQLException {
		try {
			// Requete
			String requete = "INSERT INTO `AssociationUtilisateurPromotion`(`idClient`, `categorie`) VALUES ("+idClient+",\""+categorie+"\")";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPreferences.AddPrefCat : " + e.getMessage());
		}
	}
	
	/**
	 * Supprime une préférence magasin à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static void DelPrefCat (int idClient,String categorie) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `AssociationUtilisateurPromotion` WHERE `idClient`=\""+idClient+"\" AND `categorie`=\""+categorie+"\"";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPreferences.DelPrefCat : " + e.getMessage());
		}
	}
}
