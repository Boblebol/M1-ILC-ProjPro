package tests.traitement;

import org.json.JSONException;

import clients.traitement.ClientsTraitements;
import settings.dbSettings.DBException;

public class testSpecifique {
	public static void main (String[] args) throws DBException, JSONException {
		System.out.println("### Suppression client cli1 ###");
		System.out.println(ClientsTraitements.DelClient("client1@gmail.com", "mdp"));
		System.out.println("### Suppression client cli2 ###");
		System.out.println(ClientsTraitements.DelClient("client2@gmail.com", "mdp"));
	}
}
