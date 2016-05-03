package tests.traitement;

import org.json.JSONException;

import promotions.traitement.PromotionsTraitements;
import settings.dbSettings.DBException;

public class testSpecifique {
	public static void main (String[] args) throws DBException, JSONException {
		
		System.out.println("### Liste promos Actives###");
		float a = new Float(90.0);
		System.out.println(PromotionsTraitements.listePromotionActivesProche(50000000, a,a));
	}
}
