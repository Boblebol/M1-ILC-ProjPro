package promotions.traitement;

import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import promotions.db.DBPromotions;
import promotions.db.DBPromotionsTools;
import settings.dbSettings.DBException;
import settings.traitementSettings.TraitementTools;


public class PromotionsTraitements {
	/**
	 * Ajoute une promotion dans la base de donnnï¿½e
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
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject  AddPromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix,Date creation, int duree,String active, int idmag) {
		try {
			if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				return TraitementTools.JSONerreur("Cette promotion existe déja.", 2);
			}
			DBPromotions.AddPromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, creation, duree, active, idmag);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Supprime une promotion dans la base de donnnï¿½e
	 * @param ref
	 * @param idmag
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject DelPromotion (String ref,int idmag) {
		try {
			if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				DBPromotions.DelPromotion(ref, idmag);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("Cette combinaison mail/mot de passe n'existe pas.", 2);
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
	 * @param dateCreation
	 * @param dureeValidite
	 * @param magasin
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject updatePromotion (String ref, String marque, String nomprod,String categorie,String description,Float ancienprix,Float nouveauprix,Date creation, int duree,String active, int idmag) {
		try {
			if (DBPromotionsTools.PromotionExistance(ref, idmag)) {
				DBPromotions.updatePromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, creation, duree, active, idmag);
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
