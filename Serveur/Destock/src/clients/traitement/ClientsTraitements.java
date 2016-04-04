package clients.traitement;

import org.json.JSONObject;

import clients.db.DBClient;
import clients.db.DBClientTools;
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
}
