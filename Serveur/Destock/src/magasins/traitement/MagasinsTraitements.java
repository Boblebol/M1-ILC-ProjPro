package magasins.traitement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import magasins.db.DBMagasins;
import magasins.db.DBMagasinsTools;
import settings.dbSettings.DBException;
import settings.traitementSettings.TraitementTools;


public class MagasinsTraitements {
	/**
	 * Ajoute un magasin dans la base de donnn�e
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param addresse
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject  AddMagasin (String mail,String mdp,String nom,String adresse) {
		try {
			if ((DBMagasinsTools.magasinExistance(mail))) {
				return TraitementTools.JSONerreur("Ce mail est deja pris.", 2);
			}
			DBMagasins.AddMagasin (mail,mdp,nom,adresse);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Supprime un magasin dans la base de donnn�e
	 * @param mail
	 * @param mdp
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject DelMagasin (String mail,String mdp) {
		try {
			if ((DBMagasinsTools.magasinExistance(mail,mdp))) {
				DBMagasins.DelMagasin(mail, mdp);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("Cette combinaison mail/mot de passe n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
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
	public static JSONObject updateMagasin (String mail,String mdp,String nom,String adresse) {
		try {
			if ((DBMagasinsTools.magasinExistance(mail,mdp))) {
				DBMagasins.updateMagasin(mail, mdp, nom, adresse);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("Cette combinaison mail/mot de passe n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * lister les magasin
	 * @return un json array avec tous les magasin des utilisateurs
	 * @throws BDException
	 */
	public static JSONArray listeMagasin () throws DBException, JSONException {
		return DBMagasins.listeMagasin();
	}
	
	/**
	 * Obtenir les details sur un magasin
	 * @param id
	 * @param nom
	 * @return un json avec toute les information d'un magasin
	 * @throws BDException
	 */
	public static JSONObject detailMagasin (int id,String nom) throws DBException, JSONException {
		return DBMagasins.detailMagasin(id, nom);
	}
}
