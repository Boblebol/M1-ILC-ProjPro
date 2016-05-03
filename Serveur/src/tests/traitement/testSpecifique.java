package tests.traitement;

import org.json.JSONException;

import promotions.traitement.PromotionsTraitements;
import settings.dbSettings.DBException;

public class testSpecifique {
	public static void main (String[] args) throws DBException, JSONException {
		
		System.out.println("### Liste promos Actives###");
		System.out.println(PromotionsTraitements.listePrommotionsActives ());
	}
}
