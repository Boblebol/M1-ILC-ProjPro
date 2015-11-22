package utilisateur.db;

import dbSettings.DBException;

public class Test {
	
	public static void main (String[] args) throws DBException {

		System.out.println("#### addUtilisateur #### ");
		DBUtilisateurs.addUtilisateur("boblebolde", "trololo" , "enouf", "alex","alex@gmail");
		DBUtilisateurs.addUtilisateur("bobleboltri", "trololo" , "enouf", "alex","alex@gmail");
		//System.out.println("#### delUtilisateur #### ");
		//DBUtilisateurs.delUtilisateur("boblebol", "trololo","alex@gmail");
		System.out.println("#### updatePosUtilisateur #### ");
		DBUtilisateurs.updatePosUtilisateur ("boblebolde", 62.4, 50);
		//System.out.println("#### getIdUtilisateur #### ");
		//System.out.println(DBUtilisateursTools.getIdUtilisateur ("boblebol"));
	}
}
