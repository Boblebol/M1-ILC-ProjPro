package connections.db;

import utilisateur.db.DBUtilisateursTools;
import dbSettings.DBException;

public class DBConnections {


	/**
	 * Teste si pseudo/pass/key est dans lma base de donnée
	 * @param pseudo
	 * @param pass
	 * @param clefDeLog
	 * @return -1,-2 ou l'id de la connection
	 */
	// -1 le pseudo et le mdp ne correspondent pas
	// -2 l'utilisateur est deja connecté
	public static int pseudo(String pseudo,String pass ,String key) throws DBException {
		if ( DBUtilisateursTools.utilisateurExistance(pseudo,pass)){
			//l'utilisateur existe dans la base de donnée users 
			if (! DBConnectionsTools.pseudoUtilisateurExistance(pseudo)){
				//l'utilisateur n'est pas dans la base de connection
				//on creer une authentification
				DBConnectionsTools.createConnection (pseudo);
			}
			//l'utilisateur existe dans la base de donnée connections 
			if (DBConnectionsTools.estConnecte(pseudo)){
				//l'utilisateur est connecté
				return -2;
			}else {
				//l'utilisateur n'est pas connécté
				// on modifie la clef
				DBConnectionsTools.setKey (pseudo,key);
				// on connecte l'utilisateur
				DBConnectionsTools.connection (pseudo);
				// on renvoi son id
				return DBConnectionsTools.getIdUtilisateur(key);
			}
		} else {
			//l'utilisateur n'existe pas dans la base de donnée users
			return -1;
		}
	}
}
