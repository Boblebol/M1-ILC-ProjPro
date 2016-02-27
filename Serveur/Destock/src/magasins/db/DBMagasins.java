package magasins.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBMagasins {

	/**
	 * Ajoute un magasin dans la base de donnnée
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param adresse
	 * @throws BDException
	 */
	public static void AddMagasin (String mail,String mdp,String nom,String adresse) throws DBException, SQLException {
		try {
			// Requete
			String requete = "INSERT INTO `Magasin`(`mailMagasin`, `motDePasseMagasin`, `nomMagasin`, `addresseMagasin`) VALUES (null,\""+mail+"\",\""+mdp+"\",\""+nom+"\",\""+adresse+"\")";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBMagasin.AddMagasin : " + e.getMessage());
		}
	}

	/**
	 * Supprime un magasin dans la base de donnnée
	 * @param mail
	 * @param mdp
	 * @throws BDException
	 */
	public static void DelMagasin (String mail,String mdp) throws DBException, SQLException {
		try {
			// Requete
			String requete = "DELETE FROM `Magasin` WHERE mailMagasin=\""+mail+"\" AND motDePasseMagasin=\""+mdp+"\"";

			// Ouverture de la connexion
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Execution de la requete
			s.executeUpdate(requete);

			// Fermeture de la connexion
			s.close();
			c.close();
		}catch (SQLException e) {
			throw new DBException("DBMagasin.DelMagasin : " + e.getMessage());
		}
	}

	/**
	 * Modification d'un magasin
	 * @para idMagasin
	 * @param mail
	 * @param mdp
	 * @param nom
	 * @param adresse
	 * @throws BDException
	 */
	public static void updateMagasin (String mail,String mdp,String nom,String adresse) throws DBException {
		try { 
			if (DBMagasinsTools.magasinExistance(mail)) {
				int id = DBMagasinsTools.getIdMagasin(mail);
				// Requete
				String requete = "UPDATE `Magasin` SET `mailMagasin`=\""+mail+"\",`motDePasseMagasin`=\""+mdp+"\",`nomMagasin`=\""+nom+"\",`addresseMagasin`=\""+adresse+"\" WHERE idMagasin=\"" + id + "\"";
				// Ouverture de la connexion
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();

				// Execution de la requete
				s.executeUpdate(requete);

				// Fermeture de la connexion
				s.close();
				c.close();
			}
		}catch (SQLException e) {
			throw new DBException("DBMagasin.updateMagasin : " + e.getMessage());
		}
	}
}
