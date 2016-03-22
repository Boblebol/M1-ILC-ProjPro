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

public class DBMagasins {

	/**
	 * Ajoute un magasin dans la base de donnnée
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param adresse
	 * @throws BDException
	 */
	public static void AddMagasin (String mail,String mdp,String nom,String adresse) throws DBException, SQLException {
		try {
			// Requete
			String requete = "INSERT INTO `Magasin`(`mailMagasin`, `motDePasseMagasin`, `nomMagasin`, `addresseMagasin`) VALUES (\""+mail+"\",\""+mdp+"\",\""+nom+"\",\""+adresse+"\")";

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
			String requete = "DELETE FROM `Magasin` WHERE mailMagasin=\""+mail+"\" AND motDePasseMagasin=\""+mdp+"\"";

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
				int id = DBMagasinsTools.getIdMagasin(mail);
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

			// Fermeture de la connexion
			s.close();
			c.close();

			return tmp;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}
}
