package clients.traitement;

import org.json.JSONException;
import org.json.JSONObject;

import clients.db.DBClient;
import clients.db.DBClientTools;
import settings.dbSettings.DBException;
import settings.traitementSettings.TraitementTools;


public class ClientsTraitements {
	/**
	 * Ajoute un client
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param longitude
	 * @param lattitude
	 * @return return JSON ou Erreur
	 */
	public static JSONObject  AddClient(String mail,String mdp,String nom,String prenom,int longitude,int lattitude) {
		try {
			if ((DBClientTools.clientExistance(mail))) {
				return TraitementTools.JSONerreur("Ce mail est deja pris.", 2);
			}
			DBClient.AddClient (mail,mdp,nom,prenom, longitude,lattitude );
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Supprime un client 
	 * @param mail
	 * @param mdp
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject DelClient (String mail,String mdp) {
		try {
			if (DBClientTools.clientExistance(mail)) {
				DBClient.DelClient(mail, mdp);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("ce client n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Modification d'un client
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param longitude 
	 * @param lattitude
	 * @throws BDException
	 */
	public static JSONObject updateClient (String mail,String mdp,String nom,String prenom,int longitude,int lattitude) {
		try {
			if (DBClientTools.clientExistance(mail)) {
				DBClient.updateClient(mail,mdp,nom,prenom,longitude,lattitude);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("ce client n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Modification de la position d'un client
	 * @param mail
	 * @param mdp
	 * @param longitude 
	 * @param lattitude
	 * @throws BDException
	 */
	public static JSONObject updatePosClient (String mail,int longitude,int lattitude) {
		try {
			if (DBClientTools.clientExistance(mail)) {
				DBClient.updatePosClient(mail,longitude,lattitude);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("ce client n'existe pas.", 2);
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}
	
	/**
	 * Connexion d'un client
	 * @param mail
	 * @param mdp
	 * @return un json avec toute les information d'un client ou un json vide
	 * @throws JSONException 
	 * @throws DBException 
	 */
	public static JSONObject connexionClient (String mail,String mdp) throws DBException, JSONException {
		return DBClient.connecteClient(mail, mdp);
	}	
}
