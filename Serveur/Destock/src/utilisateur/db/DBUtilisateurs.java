package utilisateur.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dbSettings.DBException;
import dbSettings.DataBase;

public class DBUtilisateurs {


	/**
	 * Ajoute un utilisateur dans la base de donnnée
	 * @param pseudo
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @throws BDException
	 */
	public static void addUtilisateur(String pseudo, String mdp, String nom, String prenom, String mail) throws DBException {
		try {

			// Requete
			String requete = "INSERT INTO `utilisateurs`(`idUtilisateur`, `pseudoUtilisateur`,`motDePasseUtilisateur`, `nomUtilisateur`, `prenomUtilisateur`,`mailUtilisateur`) VALUES (null,\""+pseudo+"\",\""+mdp+"\",\""+nom+"\",\""+prenom+"\",\""+mail+"\")";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();

		} catch (SQLException e) {
			throw new DBException("utilisateur.db.DBUtilisateurs.addUtilisateur : " + e.getMessage());
		}
	}


	/**
	 * Supprime un utilisateur de la base de donnée
	 * @param pseudo
	 * @param mdp
	 * @param mail
	 * @throws BDException
	 */
	public static void delUtilisateur (String pseudo, String mdp, String mail) throws DBException {
		try { 
			// Requete
			String requete ="DELETE FROM `utilisateurs` WHERE  pseudoUtilisateur=\'" + pseudo
					+ "\' AND motDePasseUtilisateur=\'" + mdp + "\' AND mailUtilisateur=\'" + mail + "\'";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.delUtilisateur : " + e.getMessage());
		}
	}

	/**
	 * Met à jour la position d'un utilisateur
	 * @param pseudo
	 * @param longitude
	 * @param lattitude
	 * @throws BDException
	 */
	public static void updatePosUtilisateur (String pseudo, double longitude, double lattitude) throws DBException {
		try { 

			// Requete
			String requete = "UPDATE `utilisateurs` SET `lastLongitudeUtilisateur`=" + longitude + ",`lastLattitudeUtilisateur`=" + lattitude + " WHERE  pseudoUtilisateur=\"" + pseudo + "\"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}


	/**
	 * Modification d'un utilisateur
	 * @param pseudo
	 * @param longitude
	 * @param lattitude
	 * @throws BDException
	 */
	public static void updateUtilisateur (int idUtilisateur, String pseudoUtilisateur,String motDePasseUtilisateur,String nomUtilisateur,String prenomUtilisateur,String mailUtilisateur, double longitude, double lattitude) throws DBException {
		try { 
			if (DBUtilisateursTools.utilisateurExistance(pseudoUtilisateur)) {
				if (DBUtilisateursTools.getIdUtilisateur(pseudoUtilisateur) == idUtilisateur) {
					// Requete
					String requete = "UPDATE `utilisateurs` SET `lastLongitudeUtilisateur`=" + longitude + ",`lastLattitudeUtilisateur`=" + lattitude + " WHERE  idUtilisateur=\"" + idUtilisateur + "\"";

					// Ouverture de la connexion
					Connection c = DataBase.getMySQLConnection();
					Statement s = c.createStatement();

					// Execution de la requete
					s.executeUpdate(requete);

					// Fermeture de la connexion
					s.close();
					c.close();
				}
			}
		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}


	/**
	 * lister les utilisateurs
	 * @return un json avec tous les pseudo des utilisateurs
	 * @throws BDException
	 */
	public static JSONArray listeUtilisateurs () throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT pseudo FROM `utilisateurs` ";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				String pseudo = rs.getString("pseudo");
				JSONObject tmp = new JSONObject();
				tmp.put("pseudo", pseudo);
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