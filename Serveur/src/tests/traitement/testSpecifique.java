package tests.traitement;

import org.json.JSONException;

import clients.db.DBClientTools;
import clients.traitement.ClientsTraitements;
import preferences.traitement.PreferencesTraitements;
import settings.dbSettings.DBException;

public class testSpecifique {
	public static void main (String[] args) throws DBException, JSONException {
		int idcli1=DBClientTools.getIdClient("client1@gmail.com");
		System.out.println("###Liste des preference de cli1 ###");
		System.out.println(PreferencesTraitements.ListePref(idcli1));
	}
}
