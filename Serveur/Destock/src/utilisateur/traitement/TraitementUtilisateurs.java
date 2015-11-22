package utilisateur.traitement;

import org.json.JSONObject;

import traitement.TraitementTools;
import utilisateur.db.DBUtilisateurs;
import utilisateur.db.DBUtilisateursTools;

public class TraitementUtilisateurs {


	/**
	 * Ajoute un utilisateur dans la base de donnnée
	 * @param pseudo
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return return JSON ok ou erreur
	 */
	public static JSONObject  addUtilisateur (String pseudo, String mdp, String nom, String prenom, String mail) {
		try {
			if ((DBUtilisateursTools.utilisateurExistance(pseudo))) {
				return TraitementTools.JSONerreur("Ce pseudo est deja pris.", 2);
			}

			DBUtilisateurs.addUtilisateur(pseudo, mdp, nom, prenom, mail);
			return TraitementTools.JSONok();
		} catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}


	/**
	 * Supprime un utilisateur de la base de donnée
	 * @param pseudo
	 * @param mdp
	 * @param mail
	 * @return JSON ok ou erreur
	 */
	public static JSONObject delUtilisateur (String pseudo, String mdp, String mail) {
		try { 
			if (DBUtilisateursTools.utilisateurExistance(pseudo,mdp)) {
				DBUtilisateurs.delUtilisateur(pseudo, mdp, mail);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("L'utilisateur n'existe pas.", 1);
		}catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}
	}


	/**
	 * Met à jour la position d'un utilisateur
	 * @param pseudo
	 * @param longitude
	 * @param lattitude
	 * @throws BDException
	 */
	public static JSONObject updatePosUtilisateur (String pseudo, double longitude, double lattitude) {
		try { 
			if (DBUtilisateursTools.utilisateurExistance(pseudo)) {
				DBUtilisateurs.updatePosUtilisateur(pseudo, longitude, lattitude);
				return TraitementTools.JSONok();
			}
			return TraitementTools.JSONerreur("L'utilisateur n'existe pas.", 1);
		}catch (Exception e) {
			return TraitementTools.JSONBDerreur(e.getMessage());
		}

	}
}
