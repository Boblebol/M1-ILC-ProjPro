package preferences.traitement;

import org.json.JSONObject;

import preferences.db.DBPreferences;
import settings.traitementSettings.TraitementTools;

public class PreferencesTraitements {

	/**
	 * Ajoute une préférence magasin à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static JSONObject AddPrefMagasin (int idClient,int idMagasin) {
		try {

			DBPreferences.AddPrefMagasin(idClient, idMagasin);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Suprime une préférence magasin à un utilisateur
	 * @param idClient
	 * @param idMagasin
	 * @throws BDException
	 */
	public static JSONObject DelPrefMagasin (int idClient,int idMagasin) {
		try {

			DBPreferences.DelPrefMagasin(idClient, idMagasin);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Ajoute une préférence catégorie à un utilisateur
	 * @param idClient
	 * @param categorie
	 * @throws BDException
	 */
	public static JSONObject AddPrefCat (int idClient,String categorie) {
		try {
			DBPreferences.AddPrefCat(idClient, categorie);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Supprime une préférence catégorie à un utilisateur
	 * @param idClient
	 * @param categorie
	 * @throws BDException
	 */
	public static JSONObject DelPrefCat (int idClient,String categorie) {
		try {
			DBPreferences.DelPrefCat(idClient, categorie);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
}
