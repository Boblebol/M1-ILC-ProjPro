package magasins.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;
import settings.traitementSettings.TraitementTools;

public class DBMagasins {

	/**
	 * Ajoute un magasin dans la base de donnnée
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param adresse
	 * @param longitude
	 * @param lattitude
	 * @throws BDException
	 */
	public static void AddMagasin (String mail,String mdp,String nom,String adresse, float latitude, float longitude) throws DBException, SQLException {
		try {
			// Requete
			String requete = "INSERT INTO `Magasin`(`mailMagasin`, `motDePasseMagasin`, `nomMagasin`, `addresseMagasin`, `LattitudeMagasin`,`LongitudeMagasin`) VALUES (\""+mail+"\",\""+mdp+"\",\""+nom+"\",\""+adresse+"\",\""+latitude+"\",\""+longitude+"\")";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBMagasin.AddMagasin : " + e.getMessage());
		}
	}

	/**
	 * Supprime un magasin dans la base de donnnée
	 * @param mail
	 * @param mdp
	 * @throws BDException
	 */
	public static void DelMagasin (String mail,String mdp) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `Magasin` WHERE `mailMagasin`=\""+mail+"\" AND `motDePasseMagasin`=\""+mdp+"\"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBMagasin.DelMagasin : " + e.getMessage());
		}
	}

	/**
	 * Modification d'un magasin
	 * @para idMagasin
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param adresse
	 * @throws BDException
	 */
	public static void updateMagasin (String mail,String mdp,String nom,String adresse) throws DBException {
		try { 
			if (DBMagasinsTools.magasinExistance(mail)) {
				int id = DBMagasinsTools.getIdMagasinFromMail(mail);
				// Requete
				String requete = "UPDATE `Magasin` SET `mailMagasin`=\""+mail+"\",`motDePasseMagasin`=\""+mdp+"\",`nomMagasin`=\""+nom+"\",`addresseMagasin`=\""+adresse+"\" WHERE idMagasin=\"" + id + "\"";
				// Ouverture de la connexion
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();

				// Execution de la requete
				s.executeUpdate(requete);

				// Fermeture de la connexion
				s.close();
				c.close();
			}
		}catch (SQLException e) {
			throw new DBException("DBMagasin.updateMagasin : " + e.getMessage());
		}
	}
	
	
	/**
	 * lister les magasin
	 * @return un json array avec tous les magasin des utilisateurs
	 * @throws BDException
	 */
	public static JSONArray listeMagasin () throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT `idMagasin`, `nomMagasin`, `addresseMagasin` FROM `Magasin`";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				int id = rs.getInt("idMagasin");
				String nom = rs.getString("nomMagasin");
				String adresse = rs.getString("addresseMagasin");
				JSONObject tmp = new JSONObject();
				tmp.put("idMagasin", id);
				tmp.put("nomMagasin", nom);
				tmp.put("addresseMagasin", adresse);
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
	
	
	/**
	 * lister les magasins proches
	 * @param distance
	 * @param latitude
	 * @param longitude
	 * @return un json array avec tous les magasin des utilisateurs
	 * @throws BDException
	 */
	public static JSONArray listeMagasinProche (int distance, float longitude, float latitude) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT `idMagasin`, `nomMagasin`, `addresseMagasin`, `LatitudeMagasin`, `LongitudeMagasin` FROM `Magasin`";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				int id = rs.getInt("idMagasin");
				String nom = rs.getString("nomMagasin");
				String adresse = rs.getString("addresseMagasin");
				float longitudeM = rs.getFloat("LongitudeMagasin");
				float latitudeM =rs.getFloat("LatitudeMagasin");
				JSONObject tmp = new JSONObject();
				tmp.put("idMagasin", id);
				tmp.put("nomMagasin", nom);
				tmp.put("addresseMagasin", adresse);
				if ( (Math.sqrt(Math.pow(longitude-longitudeM, 2)+Math.pow(latitude-latitudeM, 2))) < distance) {
					js.put(tmp);
				}	
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return js;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}
	
	
	/**
	 * Obtenir les details sur un magasin
	 * @param id
	 * @param nom
	 * @return un json avec toute les information d'un magasin
	 * @throws BDException
	 */
	public static JSONObject detailMagasin (int id,String nom) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT `mailMagasin`, `addresseMagasin`, `LongitudeMagasin`, `LattitudeMagasin` FROM `Magasin` WHERE idMagasin=\""+id+"\" AND nomMagasin=\""+nom+"\"" ;
			
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONObject tmp = new JSONObject();
			while (rs.next()) {
			if (rs.isFirst() && rs.isLast() ) {
				String adresse = rs.getString("addresseMagasin");
				String mail = rs.getString("mailMagasin");
				Double longitude = rs.getDouble("LongitudeMagasin");
				Double lattitude = rs.getDouble("LattitudeMagasin");
				
				tmp.put("nomMagasin", nom);
				tmp.put("idMagasin", id);
				tmp.put("mailMagasin", mail);
				tmp.put("LongitudeMagasin",longitude);
				tmp.put("LattitudeMagasin",lattitude);
				tmp.put("addresseMagasin", adresse);
				
			}
			else {
				TraitementTools.JSONBDerreur("DBMagasin.detailMagasin: Il y a plusieur magasin pour les infos données");
			}
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return tmp;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}
	
	/**
	 * connecte un magasin
	 * @param adresse mail
	 * @param mdp
	 * @return un json avec les informations d'un client si il est en base ou un json vide
	 * @throws JSONException 
	 * @throws BDException
	 */
	public static JSONObject connecteMagasin (String mail,String mdp) throws DBException, JSONException {
		try {
			JSONObject tmp = new JSONObject();
			if (DBMagasinsTools.magasinExistance(mail)) {
				// Requete
				String requete = "SELECT `idMagasin`, `nomMagasin` FROM `Magasin` WHERE `mailMagasin` = \""+mail+"\" AND `motDePasseMagasin`=\""+mdp+"\"";

				// Ouverture de la connexion
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();

				s.executeQuery(requete);
				ResultSet rs = s.getResultSet();

				while (rs.next() && rs.isLast()) {
					int id = rs.getInt("idMagasin");
					String nom = rs.getString("nomMagasin");
					tmp.put("id", id);
					tmp.put("mail", mail);
					tmp.put("nom", nom);
				}

				// Fermeture de la connexion
				s.close();
				c.close();

				return tmp;
			} else {
				return tmp;
			}
		} catch (SQLException e) {
			throw new DBException("DBClient.updateClient : " + e.getMessage());
		}
	}
}
