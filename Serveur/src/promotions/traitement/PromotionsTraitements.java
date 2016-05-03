package promotions.traitement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import promotions.db.DBPromotions;
import promotions.db.DBPromotionsTools;
import settings.dbSettings.DBException;
import settings.traitementSettings.TraitementTools;


public class PromotionsTraitements {
	
	/**
	 * Ajoute une promotion dans la base de donnn�e
	 * @param referencePromo
	 * @param marquePromo
	 * @param nomProduit
	 * @param categorie
	 * @param description
	 * @param ancienPrix
	 * @param nouveauPrix
	 * @param dureeValidite
	 * @param magasin
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject  AddPromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix,/*Date creation,*/ int duree,int active, int idmag) {
		try {
			if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				return TraitementTools.JSONerreur("Cette promotion existe d�ja.", 2);
			}
			DBPromotions.AddPromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, /*creation,*/ duree, active, idmag);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	
	/**
	 * Supprime une promotion dans la base de donnn�e
	 * @param ref
	 * @param idmag
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject DelPromotion (String ref,int idmag) {
		try {
			//if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				DBPromotions.DelPromotion(ref, idmag);
				return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
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
	 * @param dureeValidite
	 * @param magasin
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject updatePromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix, int duree,int active, int idmag) {
		try {
			if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				DBPromotions.updatePromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, duree, active, idmag);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("Cette promotion n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	
	/**
	 * lister les promotions
	 * @return un json array avec tous les promotions avec les magasins
	 */
	public static JSONArray listePrommotions () throws DBException, JSONException {
		return DBPromotions.listePromotion();
	}
	
	
	/**
	 * lister les promotions actives
	 * @return un json array avec tous les promotions actives
	 *  avec les magasins
	 */
	public static JSONArray listePrommotionsActives () throws DBException, JSONException {
		return  DBPromotions.listePromotionActives();
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
		return DBPromotions.listePromotionActivesProche(distance, latitude, longitude);
	}
	
	
	/**
	 * lister les promotions d'une categorie actives
	 * @param une categorie
	 * @return un json array avec tous les promotions actives
	 *  avec les magasins
	 */
	public static JSONArray listePrommotionsCat (String cat) throws DBException, JSONException {
		return  DBPromotions.listePromotionActives();
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
		return  DBPromotions.listePromotionCategorieProche(cat, distance,latitude,longitude);
	}
	
	
	/**
	 * lister les promotions d'un magasin donnée qui sont actives
	 * @param idMagasin
	 * @return un json array avec tous les promotions actives d'une catégorie
	 * @throws BDException
	 */
	public static JSONArray listePromotionMagasin (int idMag) throws DBException, JSONException {
		return  DBPromotions.listePromotionMagasin(idMag);
	}
	
	/**
	 * lister le promotions issues des préférences d'un utilisateur
	 * @param int IDUtilisateur
	 * @return un json array avec tous les promotions issues des préférences d'un utilisateur
	 * @throws BDException
	 */
	public static JSONArray listePromotionPréférences (int id) throws DBException, JSONException {
		return  DBPromotions.listePromotionPréférences(id);
	}
		
	
	/**
	 * Obtenir les details sur une promotion
	 * @param id
	 * @param nom
	 * @return un json avec toute les information d'un magasin
	 * @throws BDException
	 */
	public static JSONObject detailPromotion (int idpromo)throws DBException, JSONException {
		return DBPromotions.detailPromotion(idpromo);
	}
}
