package tests.traitement;

import java.sql.Date;
import java.util.Calendar;

import org.json.JSONException;

import clients.db.DBClientTools;
import clients.traitement.ClientsTraitements;
import magasins.db.DBMagasinsTools;
import magasins.traitement.MagasinsTraitements;
import preferences.traitement.PreferencesTraitements;
import promotions.db.DBPromotionsTools;
import promotions.traitement.PromotionsTraitements;
import settings.dbSettings.DBException;

public class TraitementTest {
	public static void main (String[] args) throws DBException, JSONException {
		System.out.println("### Création client cli1 ###");
		System.out.println(ClientsTraitements.AddClient("client1@gmail.com","mdp","nom1","prenom1",0,0));
		System.out.println("### Création client cli2 ###");
		System.out.println(ClientsTraitements.AddClient("client2@gmail.com","mdp","nom2","prenom2",0,0));
		
		System.out.println("### Modification client cli2 ###");
		System.out.println(ClientsTraitements.updateClient("client2@gmail.com", "mdp", "nom2", "prenom2", 10,10));
		
		System.out.println("### Création magasin mag1 ###");
		System.out.println(MagasinsTraitements.AddMagasin("magasin1@gmail.com", "mdp", "mag1", "adresse1"));
		System.out.println("### Création magasin mag2 ###");
		System.out.println(MagasinsTraitements.AddMagasin("magasin2@gmail.com", "mdp", "mag2", "adresse2"));
		
		System.out.println("### Liste des maasin ###");
		System.out.println(MagasinsTraitements.listeMagasin());
		
		System.out.println("### Modification magasin mag2 ###");
		System.out.println(MagasinsTraitements.updateMagasin("magasin2@gmail.com", "mdp", "mag2", "NouvelleAdresse2"));
		
		System.out.println("### Detail magasin mag2 ###");
		int idmag2=DBMagasinsTools.getIdMagasinFromNom("mag2");
		System.out.println(MagasinsTraitements.detailMagasin(idmag2, "mag2"));
		
		System.out.println("### Creation promo p1 du mag1 ###");
		int idmag1=DBMagasinsTools.getIdMagasinFromNom("mag1");
		//java.sql.Date creation = new Date(Calendar.getInstance().getTimeInMillis());
		float oldP =70;
		float newP = 50; 
		System.out.println(PromotionsTraitements.AddPromotion("p1", "marque", "nomprod1", "categorie1", "description1", oldP, newP,/*creation,*/ 3, 1, idmag1));
		System.out.println("### Creation promo p2 du mag1 ###");
		System.out.println(PromotionsTraitements.AddPromotion("p2", "marque", "nomprod2", "categorie2", "description2", oldP, newP,/*creation,*/ 3, 1, idmag1));
		
		System.out.println("### Liste promos ###");
		System.out.println(PromotionsTraitements.listePrommotions());
		
		System.out.println("### Modification promo p2 du mag1 ###");
		System.out.println("Refaire update promotions");
		
		System.out.println("### Detail promo p2 du mag1 ###");
		int idpromo2 = DBPromotionsTools.getIdPromo("p2", idmag1);
		System.out.println(PromotionsTraitements.detailPromotion(idpromo2));
		
		System.out.println("### Ajout preference cli1 mag1 ###");
		int idcli1=DBClientTools.getIdClient("client1@gmail.com");
		System.out.println(PreferencesTraitements.AddPrefMagasin(idcli1, idmag1));
		System.out.println("### Ajout preference cli1 categorie lolo ###");
		System.out.println(PreferencesTraitements.AddPrefCat(idcli1, "lolo"));
		
		System.out.println("### Suppression preference cli1 categorie lolo ###");
		System.out.println(PreferencesTraitements.DelPrefCat(idcli1, "lolo"));
		System.out.println("### Suppression preference cli1 mag1 ###");
		System.out.println(PreferencesTraitements.AddPrefMagasin(idcli1, idmag1));
		
		System.out.println("### Suppression promo p1 du mag1 ###");
		System.out.println(PromotionsTraitements.DelPromotion("p1", idmag1));
		System.out.println("### Suppression promo p2 du mag1 ###");
		System.out.println(PromotionsTraitements.DelPromotion("p2", idmag1));
		
		System.out.println("### Suppression magasin mag1 ###");
		System.out.println(MagasinsTraitements.DelMagasin("magasin1@gmail.com", "mdp"));
		System.out.println("### Suppression magasin mag2 ###");
		System.out.println(MagasinsTraitements.DelMagasin("magasin2@gmail.com", "mdp"));
		
		System.out.println("### Suppression client cli1 ###");
		System.out.println(ClientsTraitements.DelClient("client1@gmail.com", "mdp"));
		System.out.println("### Suppression client cli2 ###");
		System.out.println(ClientsTraitements.DelClient("client2@gmail.com", "mdp"));
	}
}
