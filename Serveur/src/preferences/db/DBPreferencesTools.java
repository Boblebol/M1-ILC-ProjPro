package preferences.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;
import settings.traitementSettings.TraitementTools;

public class DBPreferencesTools {

	/**
	 * Retourne la liste des magasins préférés d'un utilisateur
	 * @param idClient
	 * @return ArrayList avec toutes les magasins préférés d'un utilisateur
	 * @throws BDException
	 */
	public static ArrayList <Integer> ListePrefMagasin(int idClient) throws DBException, JSONException {
		try { 
			ArrayList <Integer> magasinPref = new ArrayList <Integer>();
			// Requete
			String requete = "SELECT `idMagasin` FROM `AssociationUtilisateurPromotion` WHERE `idClient`="+idClient+"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();

			while (rs.next()) {

				int idM = rs.getInt("idMagasin");
				if (idM!=0){
					magasinPref.add(idM);
				}
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return magasinPref;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}

	/**
	 * Retourne la liste des catégories préférées d'un utilisateur
	 * @param idClient
	 * @return ArrayList avec toutes les catégories préférées d'un utilisateur
	 * @throws BDException
	 */
	public static ArrayList <String> ListePrefCat(int idClient) throws DBException, JSONException {
		try { 
			ArrayList <String> catPref = new ArrayList <String>();
			// Requete
			String requete = "SELECT `categorie` FROM `AssociationUtilisateurPromotion` WHERE `idClient`="+idClient+"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();

			while (rs.next()) {

				String ct = rs.getString("categorie");
				if (ct!=null) {
					catPref.add(ct);
				}
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return catPref;

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}

	}

	public static JSONObject DelPrefIdUser(int id) throws DBException {
		try { 
			String requete ="DELETE FROM `AssociationUtilisateurPromotion` WHERE `idClient` ="+id+"" ;

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeUpdate(requete);
			// Fermeture de la connexion
			s.close();
			c.close();

			return TraitementTools.JSONok();

		}catch (SQLException e) {
			throw new DBException("DBUtilisateurs.updatePosUtilisateur : " + e.getMessage());
		}
	}

}
