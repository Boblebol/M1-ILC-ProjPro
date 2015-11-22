package promotions.db;

import dbSettings.DBException;

public class Test {
	public static void main (String[] args) throws DBException {
		//(String nomPromo, String nomProduit, String marque,String categorie,String description,double ancienPrix,double nouveauPrix,String estimationStock,String  dateCreation,int dureeValidite,String magasin,int numRue,String rue,int codePostal,String ville, double longitude,double lattitude, int idUtilisateur)
		DBPromotions.addPromotion("nomPromo", "nomProduit", "marque", "categorie", "description", 50.1, 15.1, 11,25, "magasin", 26, 32, 6);
	}
}