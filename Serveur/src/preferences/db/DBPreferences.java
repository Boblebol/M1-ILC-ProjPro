package preferences.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import magasins.db.DBMagasinsTools;
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
			String requete = "INSERT INTO `AssociationUtilisateurPromotion`(`idClient`, `idMagasin`) VALUES ("+idClient+","+idMagasin+")";
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
			String requete = "DELETE FROM `AssociationUtilisateurPromotion` WHERE `idClient`="+idClient+" AND `idMagasin`="+idMagasin+"";
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
	
	/**
	 * Retourne la liste des préférences d'un utilisateur
	 * @param idClient
	 * @return JSONArray avec toutes les preferences d'un utilisater
	 * @throws BDException
	 */
	public static JSONArray ListePref(int idClient) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT `idAssociation`, `idClient`, `categorie`, `idMagasin` FROM `AssociationUtilisateurPromotion` WHERE `idClient`="+idClient+"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				int idA = rs.getInt("idAssociation");
				int idC = rs.getInt("idClient");
				int idM = rs.getInt("idMagasin");
				String cat = rs.getString("categorie");
				
				JSONObject tmp = new JSONObject();
				tmp.put("idAssociation", idA);
				tmp.put("idClient", idC);
				
				tmp.put("categorie", cat);
				if (idM != 0) {
					tmp.put("idMagasin", idM);
					tmp.put("nomMagasin", DBMagasinsTools.getNomMagasinFromId(idM));
				}

				js.put(tmp);
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return js;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
		
	}
	
	
}
