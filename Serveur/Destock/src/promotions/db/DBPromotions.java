package promotions.db;

import java.sql.Connection;
import  java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBPromotions {

	/**
	 * Ajouter une promotion
	 * @param referencePromo
	 * @param marquePromo
	 * @param nomProduit
	 * @param categorie
	 * @param description
	 * @param ancienPrix
	 * @param nouveauPrix
	 * @param dateCreation
	 * @param dureeValidite
	 * @param magasin
	 * @throws BDException
	 */
	public static void AddPromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix,/*Date creation,*/ int duree,int active, int idMagasin) throws DBException, SQLException {
		try {
			// Requete
			/*String requete = "INSERT INTO `Promotions`(`referencePromo`,`marquePromo`,`nomProduit`,`categorie`, `description`, `ancienPrix`,`nouveauPrix`,`dateCreation`,`dureeValidite`,`active`,`idMagasin`) "
					+ "VALUES (\""+ref+"\",\""+marque+"\",\""+nomprod+"\",\""+categorie+"\",\""+description+"\",\""+ancienprix+"\",\""+nouveauprix+"\",\""+creation+"\",\""+duree+"\",\""+active+"\","+idMagasin+")";*/
			String requete = "INSERT INTO `Promotions`(`referencePromo`,`marquePromo`,`nomProduit`,`categorie`, `description`, `ancienPrix`,`nouveauPrix`,`dureeValidite`,`active`,`idMagasin`) "
					+ "VALUES (\""+ref+"\",\""+marque+"\",\""+nomprod+"\",\""+categorie+"\",\""+description+"\",\""+ancienprix+"\",\""+nouveauprix+"\",\""+duree+"\",\""+active+"\","+idMagasin+")";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPromotions.AddPromotion : " + e.getMessage());
		}
	}
	
	/**
	 * Modification une promotion
	 * @param referencePromo
	 * @param marquePromo
	 * @param nomProduit
	 * @param categorie
	 * @param description
	 * @param ancienPrix
	 * @param nouveauPrix
	 * @param dateCreation
	 * @param dureeValidite
	 * @param magasin
	 * @throws BDException
	 */ 

	public static void updatePromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix,Date creation, int duree,String active, int idMagasin) throws DBException, SQLException {
		try {
			if (DBPromotionsTools.PromotionExistance(ref,idMagasin)){
				int id = DBPromotionsTools.getIdPromo(ref,idMagasin);
				// Requete
				String requete = 
				"UPDATE `Promotions` SET `referencePromo`=\""+ref+"\",`marquePromo`=\""+marque+"\",`nomProduit`=\""+nomprod+"\",`categorie`=\""+categorie+"\",`description`=\""+description+"\","
						+ "`ancienPrix`=\""+ancienprix+"\",`nouveauPrix`=\""+nouveauprix+"\",`dateCreation`=\""+creation+"\",`dureeValidite`=\""+duree+"\",`active`=\""+active+"\",`idMagasin`=\""+idMagasin+"\" WHERE idClient=\"" + id + "\"";
				// Ouverture de la connexion
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();

				// Execution de la requete
				s.executeUpdate(requete);

				// Fermeture de la connexion
				s.close();
				c.close();
			}
		} catch (SQLException e) {
			throw new DBException("DBPromotions.updatePromotion : " + e.getMessage());
			}	
	}
	
	/**
	 * Supprime une promotion
	 * @param ref
	 * @param idmag
	 * @throws BDException
	 */
	
	 public static void DelPromotion (String ref,int idmag) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `Promotions` WHERE `referencePromo`=\""+ref+"\" AND `idMagasin`="+idmag+"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBPromotion.DelPromotion : " + e.getMessage());
		}
	}
	 
 
	 /**
	 * lister les promotions
	 * @return un json array avec tous les promotions avec les magasins
	 * @throws BDException
	 */
	public static JSONArray listePromotion () throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions`";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				int idPromo = rs.getInt("idPromo");
				String referencePromo = rs.getString("referencePromo");
				int idMagasin = rs.getInt("idMagasin");  

				JSONObject tmp = new JSONObject();
				tmp.put("idPromo", idPromo);
				tmp.put("referencePromo", referencePromo);
				tmp.put("idMagasin", idMagasin);
				js.put(tmp);
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return js;

		}catch (SQLException e) {
			throw new DBException("DBPromotions.listePromotion : " + e.getMessage());
		}
	}
	
	
	public static JSONObject detailPromotion (int idpromo) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`marquePromo`,`nomProduit`,"
					+ "`categorie`, `description`, `ancienPrix`,`nouveauPrix`,"
					+ "`dureeValidite`,`active`,`idMagasin` FROM `Promotions`"
					+ "WHERE idPromo=\""+idpromo+"\"";
			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONObject tmp = new JSONObject();
			while (rs.next()) {
				int idPromo = rs.getInt("idPromo");
				String referencePromo = rs.getString("referencePromo");
				String marquePromo = rs.getString("marquePromo");
				String nomProduit = rs.getString("nomProduit"); 
				String categorie = rs.getString("categorie");   
				String description = rs.getString("description"); 
				Float ancienPrix  = rs.getFloat("ancienPrix"); 
				Float nouveauPrix  = rs.getFloat("nouveauPrix");  
				//Date dateCreation = rs.getDate ("dateCreation"); 
				int dureeValidite = rs.getInt("dureeValidite");
				String statut = rs.getString("active");  
				int idMagasin = rs.getInt("idMagasin");  

				tmp.put("idPromo", idPromo);
				tmp.put("referencePromo", referencePromo);
				tmp.put("marquePromo" ,marquePromo );
				tmp.put("nomProduit",nomProduit );
				tmp.put("categorie" , categorie);
				tmp.put("description" , description);
				tmp.put("ancienPrix" ,ancienPrix );
				tmp.put("nouveauPrix" , nouveauPrix);
				//tmp.put("dateCreation" ,dateCreation );
				tmp.put("dureeValidite" ,dureeValidite );
				tmp.put("statut" ,statut );
				tmp.put("idMagasin", idMagasin);
				
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return tmp;

		}catch (SQLException e) {
			throw new DBException("DBPromotions.detailPromotion : " + e.getMessage());
		}
	}
	
}	