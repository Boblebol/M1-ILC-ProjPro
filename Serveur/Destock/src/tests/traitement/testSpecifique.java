package tests.traitement;

import org.json.JSONException;

import clients.traitement.ClientsTraitements;
import magasins.db.DBMagasinsTools;
import magasins.traitement.MagasinsTraitements;
import promotions.traitement.PromotionsTraitements;
import settings.dbSettings.DBException;

public class testSpecifique {
	public static void main (String[] args) throws DBException, JSONException {
		int idmag1=DBMagasinsTools.getIdMagasinFromNom("mag1");
		
		System.out.println("### Suppression client cli1 ###");
		System.out.println(ClientsTraitements.DelClient("client1@gmail.com", "mdp"));
		System.out.println("### Suppression client cli2 ###");
		System.out.println(ClientsTraitements.DelClient("client2@gmail.com", "mdp"));
	}
}
