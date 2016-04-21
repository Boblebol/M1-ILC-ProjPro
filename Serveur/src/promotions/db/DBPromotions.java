package promotions.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import magasins.traitement.MagasinsTraitements;
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

	public static void updatePromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix, int duree,String active, int idMagasin) throws DBException, SQLException {
		try {
			if (DBPromotionsTools.PromotionExistance(ref,idMagasin)){
				int id = DBPromotionsTools.getIdPromo(ref,idMagasin);
				// Requete
				String requete = 
						"UPDATE `Promotions` SET `referencePromo`=\""+ref+"\",`marquePromo`=\""+marque+"\",`nomProduit`=\""+nomprod+"\",`categorie`=\""+categorie+"\",`description`=\""+description+"\","
								+ "`ancienPrix`=\""+ancienprix+"\",`nouveauPrix`=\""+nouveauprix+"\",`dureeValidite`=\""+duree+"\",`active`=\""+active+"\",`idMagasin`=\""+idMagasin+"\" WHERE idClient=\"" + id + "\"";
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
	 * @return un json array avec tous les promotions
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

	/**
	 * lister les promotions actives
	 * @return un json array avec tous les promotions actives
	 * @throws BDException
	 */
	public static JSONArray listePromotionActives () throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions` WHERE `active`=1";

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
	
	/**
	 * lister les promotions actives dans un periemtre donné par rapport a une longitude et une latitude
	 * @param distace
	 * @param longitude
	 * @param lattitude
	 * @return un json array avec tous les promotions actives d'une catégorie
	 * @throws BDException
	 */
	public static JSONArray listePromotionActivesProche ( int distance, float latitude, float longitude ) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions` WHERE `active`=1";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();
			
			JSONArray magasinsProches = MagasinsTraitements.listeMagasinProche(distance, longitude, latitude);
			ArrayList<Integer> listeMag = new ArrayList <Integer>();
			for (int i = 0; i < magasinsProches.length(); i++) {
			    JSONObject jsonobject = magasinsProches.getJSONObject(i);
			    listeMag.add (jsonobject.getInt("idMagasin"));
			}
			
			while (rs.next()) {
				int idPromo = rs.getInt("idPromo");
				String referencePromo = rs.getString("referencePromo");
				int idMagasin = rs.getInt("idMagasin");  
				
				if (listeMag.contains(idMagasin)) {
					JSONObject tmp = new JSONObject();
					tmp.put("idPromo", idPromo);
					tmp.put("referencePromo", referencePromo);
					tmp.put("idMagasin", idMagasin);
					js.put(tmp);
				}
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return js;

		}catch (SQLException e) {
			throw new DBException("DBPromotions.listePromotion : " + e.getMessage());
		}
	}

	/**
	 * lister les promotions d'une categorie donnée qui sont actives
	 * @param String categorie
	 * @return un json array avec tous les promotions actives d'une catégorie
	 * @throws BDException
	 */
	public static JSONArray listePromotionCategorie (String cat) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions` WHERE `categorie`=\""+cat+"\" AND `active`=1";

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
	
	/**
	 * lister les promotions d'une categorie donnée qui sont actives dans un perimetre donné
	 * @param categorie
	 * @param distance
	 * @param longitude
	 * @param lattitude
	 * @return un json array avec tous les promotions actives d'une catégorie
	 * @throws BDException
	 */
	public static JSONArray listePromotionCategorieProche (String cat,int distance, float latitude, float longitude) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions` WHERE `categorie`=\""+cat+"\" AND `active`=1";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			s.executeQuery(requete);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			JSONArray magasinsProches = MagasinsTraitements.listeMagasinProche(distance, longitude, latitude);
			ArrayList<Integer> listeMag = new ArrayList <Integer>();
			for (int i = 0; i < magasinsProches.length(); i++) {
			    JSONObject jsonobject = magasinsProches.getJSONObject(i);
			    listeMag.add (jsonobject.getInt("idMagasin"));
			}
			
			while (rs.next()) {
				int idPromo = rs.getInt("idPromo");
				String referencePromo = rs.getString("referencePromo");
				int idMagasin = rs.getInt("idMagasin");  
				if (listeMag.contains(idMagasin)) {
				JSONObject tmp = new JSONObject();
				tmp.put("idPromo", idPromo);
				tmp.put("referencePromo", referencePromo);
				tmp.put("idMagasin", idMagasin);
				js.put(tmp);
				}
			}

			// Fermeture de la connexion
			s.close();
			c.close();

			return js;

		}catch (SQLException e) {
			throw new DBException("DBPromotions.listePromotion : " + e.getMessage());
		}
	}

	/**
	 * lister les promotions d'un magasin donnée qui sont actives
	 * @param idMagasin
	 * @return un json array avec tous les promotions actives d'une catégorie
	 * @throws BDException
	 */
	public static JSONArray listePromotionMagasin (int idMag) throws DBException, JSONException {
		try { 
			// Requete
			String requete = "SELECT  `idPromo`,`referencePromo`,`idMagasin` FROM `Promotions` WHERE `idMagasin`=\""+idMag+"\" AND `active`=1";

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
	
	
	/**
	 * Renvoie le detail d'une promotion
	 * @return un json avec tous les details d'une promotions et de son magasin
	 * @throws BDException
	 */
	public static JSONObject detailPromotion (int idpromo) throws DBException, JSONException {
		int idMagasin = 1;
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
				idMagasin = rs.getInt("idMagasin");  

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

			// Requete
			requete = "SELECT  `nomMagasin`,`addresseMagasin`,`LattitudeMagasin`,`LongitudeMagasin`"
					+"FROM `Magasin`"
					+ "WHERE idMagasin=\""+idMagasin+"\"";

			s.executeQuery(requete);

			rs = s.getResultSet();
			while (rs.next()) {
				Float latitude  = rs.getFloat("LattitudeMagasin");
				Float longitude  = rs.getFloat("LongitudeMagasin");
				String nomMagasin = rs.getString("nomMagasin"); 
				String adresseMagasin = rs.getString("addresseMagasin"); 
				
				tmp.put("nomMagasin", nomMagasin);
				tmp.put("adresseMagasin", adresseMagasin);
				tmp.put("latitude", latitude);
				tmp.put("longitude", longitude);
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