package clients.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBClient {

	/**
	 * Ajoute un client dans la base de donnee
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param longitude
	 * @param lattitude
	 * @throws BDException
	 */
	
	public static void AddClient(String mail,String mdp,String nom,String prenom,int longitude,int lattitude)throws DBException, SQLException {
	try {
		// Requete
			String requete = "INSERT INTO `Client`(`mailClient`, `motDePasseClient`, `nomClient`, `prenomClient`,`lastLongitudeClient`,`lastLattitudeClient`) VALUES (\""+mail+"\",\""+mdp+"\",\""+nom+"\",\""+prenom+"\",\""+longitude+"\",\""+lattitude+"\")";
			
		// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

		// Execution de la requete
			s.executeUpdate(requete);

		// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBClient.AddClient : " + e.getMessage());
	}
	}
	
	/**
	 * Supprime un client dans la base de donnnee
	 * @param mail
	 * @param mdp
	 * @throws BDException
	 */
	public static void DelClient (String mail,String mdp) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `Client` WHERE `mailClient`=\""+mail+"\" AND `motDePasseClient`=\""+mdp+"\"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBClient.DelClient : " + e.getMessage());
		}
	}
	
	/**
	 * Modification des informations d'un client
	 * @param idClient
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @throws BDException
	 */
	public static void updateClient (String mail,String mdp,String nom,String prenom,int longitude,int lattitude) throws DBException {
		try { 
			if (DBClientTools.clientExistance(mail)) {
				int id = DBClientTools.getIdClient(mail);
				// Requete
				String requete = 
				"UPDATE `Client` SET `mailClient`=\""+mail+"\",`motDePasseClient`=\""+mdp+"\",`nomclient`=\""+nom+"\",`prenomClient`=\""+prenom+"\" ,`lastLongitudeClient`=\""+longitude+"\",`lastLattitudeClient`=\""+lattitude+"\" WHERE idClient=\"" + id + "\"";
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
			throw new DBException("DBClient.updateClient : " + e.getMessage());
		}
	}
	
	/**
	 * connecte un client
	 * @param adresse mail
	 * @param mdp
	 * @return un json avec les informations d'un client si il est en base ou un json vide
	 * @throws JSONException 
	 * @throws BDException
	 */
	public static JSONObject connecteClient (String mail,String mdp) throws DBException, JSONException {
		try {
			JSONObject tmp = new JSONObject();
			if (DBClientTools.clientExistance(mail)) {
				// Requete
				String requete = "SELECT `idClient`, `nomClient`, `prenomClient` FROM `Client` WHERE `mailClient` = \""+mail+"\" AND `motDePasseClient`=\""+mdp+"\"";

				// Ouverture de la connexion
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();

				s.executeQuery(requete);
				ResultSet rs = s.getResultSet();

				while (rs.next() && rs.isLast()) {
					int id = rs.getInt("idClient");
					String nom = rs.getString("nomClient");
					String prenom = rs.getString("prenomClient");
					tmp.put("id", id);
					tmp.put("mail", mail);
					tmp.put("nom", nom);
					tmp.put("prenom", prenom);
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
	
	/**
	 * lister des clients
	 * @return un json array avec tous les clients
	 * @throws BDException
	 */
	 /* public static JSONArray listeClient () throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT `idClient`, `nomClient`, `prenom`,`lastLongitudeClient`,`lastLattitudeClient` FROM `Client`";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				int id = rs.getInt("idClient");
				String nom = rs.getString("nomClient");
				String prenom = rs.getString("prenom");
				int longitude = rs.getInt("lastLongitudeClient");
				int lattitude = rs.getInt("lastLattitudeClient");
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
	}*/
}