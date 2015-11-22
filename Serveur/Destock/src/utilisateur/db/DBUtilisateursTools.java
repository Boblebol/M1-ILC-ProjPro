package utilisateur.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dbSettings.DBException;
import dbSettings.DataBase;

public class DBUtilisateursTools {
	
	
	/**
	 * Test l'existence d'un utilisateur dans la base 
	 * @param pseudo
	 * @throws BDException
	 */
	public static boolean utilisateurExistance (String pseudo ) throws DBException{
		try {
			String query = "SELECT * FROM utilisateurs WHERE pseudoUtilisateur=\"" + pseudo + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return bool;
		} catch (SQLException e) {
			throw new DBException("DBUtilisateurTools.utilisateurExistance" + e.getMessage());
		}
	}
	
	
	/**
	 * Test l'existence d'un utilisateur dans la base 
	 * @param pseudo
	 * @param mdp
	 * @throws BDException
	 */
	public static boolean utilisateurExistance (String pseudo, String mdp) throws DBException{
		try {
			String query = "SELECT * FROM utilisateurs WHERE pseudoUtilisateur=\'" + pseudo
					+ "\' AND motDePasseUtilisateur=\'" + mdp + "\'";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return bool;
		} catch (SQLException e) {
			throw new DBException("DBUtilisateurTools.utilisateurExistance" + e.getMessage());
		}

	}
	
	
	/**
	 * Test l'existence d'un utilisateur dans la base, et renvoi son id, -1 sinon
	 * @param pseudo
	 * @throws BDException
	 */
	public static int getIdUtilisateur (String pseudo )throws DBException {
		try {
			int rez = -1;
			String query = "SELECT idUtilisateur FROM utilisateurs WHERE pseudoUtilisateur=\'" + pseudo+ "\'";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			
			if (bool) {
				rez = rs.getInt("idUtilisateur"); 
				
			}
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			
			return rez ; 
				
		} catch (SQLException e) {
			throw new DBException("DBUtilisateurTools.getIdUtilisateur" + e.getMessage());
		}
		
		
	}
}
