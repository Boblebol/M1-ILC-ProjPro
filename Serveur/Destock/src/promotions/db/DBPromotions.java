package promotions.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dbSettings.DBException;
import dbSettings.DataBase;
import traitement.TraitementTools;

public class DBPromotions {


	/**
	 * Ajoute une promotion dans la base de donnnée
	 * @param nomPromo
	 * @param nomProduit
	 * @param marque
	 * @param categorie
	 * @param ancienPrix
	 * @param nouveauPrix
	 * @param estimationStock
	 * @param dateCreation
	 * @param dureeValidite
	 * @param magasin
	 * @param longitude
	 * @param lattitude
	 * @param pseudo
	 * @throws BDException
	 */
	public static void addPromotion (String nomPromo, String nomProduit, String marque,String categorie,String description,double ancienPrix,double nouveauPrix,int estimationStock,int dureeValidite,String magasin, double longitude,double lattitude, int idUtilisateur) throws DBException {
		// if (nomPromo.length()!=0 || nomProduit.length()!=0 || marque.length()!=0 || longitude!=0 || lattitude!=0 || pseudo.length()!=0 ) {
		String requete = "INSERT INTO `prommotions`(`idPromo`, `nomPromo`, `nomProduit`, `marque`, `catégorie`, `description`, `ancienPrix`, `nouveauPrix`, `estimationStock`, `dateCreation`, `dureeValidite`, `magasin`, `longitude`, `lattitude`, `active`, `idUtilisateur`) "
				+ "VALUES (null,\""+nomPromo+"\",\""+nomProduit+"\",\""+marque +"\",\""+categorie +"\",\""+description +"\",\""+ancienPrix +"\",\""+ nouveauPrix+"\",\""+estimationStock +"\",\""+LocalDateTime.now() +"\",\""+ dureeValidite+"\",\""+magasin +"\",\""+longitude +"\",\""+ lattitude+"\",\"1\",\""+idUtilisateur +"\")";
		try {
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("promotions.db.DBPromotions.addPromotion : " + e.getMessage());
		}
		//}
	}


	/**
	 * modifie une promotion dans la base de donnnée
	 * @param nomPromo
	 * @param nomProduit
	 * @param marque
	 * @param categorie
	 * @param ancienPrix
	 * @param nouveauPrix
	 * @param estimationStock
	 * @param dateCreation
	 * @param dureeValidite
	 * @param magasin
	 * @param longitude
	 * @param lattitude
	 * @param pseudo
	 * @throws BDException
	 */
	public static void updatePromotion (int idPromo, String nomPromo, String nomProduit, String marque,String categorie,String description,double ancienPrix,double nouveauPrix,int estimationStock,int dureeValidite, String magasin ,double longitude,double lattitude, int idUtilisateur) throws DBException {
		//if (nomPromo.length()!=0 || nomProduit.length()!=0 || marque.length()!=0 || longitude!=0 || lattitude!=0 || pseudo.length()!=0 ) {
		String requete = "UPDATE `prommotions` SET `nomPromo`=\""+nomPromo+"\",`nomProduit`=\""+nomProduit+"\",`marque`=\""+marque+"\",`catégorie`=\""+categorie+"\",`description`=\""+description+"\",`ancienPrix`=\""+ancienPrix+"\",`nouveauPrix`=\""+nouveauPrix+"\",`estimationStock`=\""+estimationStock+"\",`dureeValidite`=\""+dureeValidite+"\",`magasin`=\""+magasin+"\",`longitude`=\""+longitude+"\",`lattitude`=\""+lattitude+"\",`active`=\"1\" WHERE `idUtilisateur`=\""+idUtilisateur+"\" AND `idPromo`=\""+idPromo+"\"";
		try {
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("promotions.db.DBPromotions.updatePromotion : " + e.getMessage());
		}
		//}

	}



	/**
	 * desactive un promotion
	 * @param idPromo
	 * @param idUtilisateur
	 */
	public static void desactivePromotion (int idPromo, int idUtilisateur) throws DBException {
		String requete = "UPDATE `prommotions` SET `active`=\""+0+"\" WHERE `idUtilisateur`=\""+idUtilisateur+"\" AND `idPromo`=\""+idPromo+"\"";
		try {
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("promotions.db.DBPromotions.desactivePromotion : " + e.getMessage());
		}

	}


	/**
	 * active un promotion
	 * @param idPromo
	 * @param idUtilisateur
	 */
	public static void activePromotion (int idPromo, int idUtilisateur) throws DBException {
		String requete = "UPDATE `prommotions` SET `active`=\""+1+"\" WHERE `idUtilisateur`=\""+idUtilisateur+"\" AND `idPromo`=\""+idPromo+"\"";
		try {
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new DBException("promotions.db.DBPromotions.activePromotion : " + e.getMessage());
		}
	}


	/**
	 * renvoi la liste des promotions
	 * @return listePromotion
	 */
	public static JSONArray listePromotion () throws JSONException {
		String query = "SELECT * FROM `prommotions`";
		try {
			JSONArray jo = new JSONArray();
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = s.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int idPromo = rs.getInt("idPromo");
				String nomPromo = rs.getString("nomPromo");
				String nomProduit = rs.getString("nomProduit");
				String marque = rs.getString("marque");
				String categorie = rs.getString("categorie");
				String description = rs.getString("description");
				double ancienPrix = rs.getDouble("ancienPrix");
				double nouveauPrix = rs.getDouble("nouveauPrix");
				Date dateCreation = rs.getDate("dateCreation");
				int estimationStock = rs.getInt("estimationStock");
				int dureeValidite = rs.getInt("dureeValidite");
				String magasin = rs.getString("magasin");
				double lattitue = rs.getDouble("lattitue");
				double longitude = rs.getDouble("longitude");
				int active = rs.getInt("active");
				int idUtilisateur = rs.getInt("idUtilisateur");

				JSONObject tmp = new JSONObject();
				tmp.put("idPromo", idPromo);
				tmp.put("nomPromo",nomPromo );
				tmp.put("nomProduit",nomProduit );
				tmp.put("marque",marque );
				tmp.put("categorie",categorie );
				tmp.put("description",description );
				tmp.put("ancienPrix",ancienPrix );
				tmp.put("nouveauPrix",nouveauPrix );
				tmp.put("dateCreation",dateCreation );
				tmp.put("estimationStock",estimationStock );
				tmp.put("dureeValidite",dureeValidite );
				tmp.put("magasin",magasin );
				tmp.put("lattitue",lattitue );
				tmp.put("longitude",longitude );
				tmp.put("active",active );
				tmp.put("idUtilisateur",idUtilisateur );
				
				jo.put(tmp);
			}
			s.close();
			return jo;
		} catch (SQLException e) {
			e.printStackTrace();
			return new JSONArray(
					TraitementTools.JSONBDerreur("BDUserListe (pb SQL) -> "
							+ e.getMessage()));
		}
	}
}