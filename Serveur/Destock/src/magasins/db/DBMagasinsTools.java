package magasins.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBMagasinsTools {

	public static boolean magasinExistance(String mail) throws DBException {
		try { 
			String query = "SELECT * FROM Magasin WHERE mailMagasin=\"" + mail + "\"";

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

		}catch (SQLException e) {
			throw new DBException("DBMagasinTools.magasinExistance : " + e.getMessage());
		}
	}

	public static int getIdMagasin(String mail) throws DBException{
		try { 
			int rez = -1;
			String query = "SELECT idMagasin FROM Magasin WHERE mailMagasin=\"" + mail + "\"";
			
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			if (bool) {
				rez = rs.getInt("idMagasin"); 
				
			}

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return rez;
		}catch (SQLException e) {
			throw new DBException("DBMagasinTools.magasinExistance : " + e.getMessage());
		}
	}
	
	public static boolean magasinExistance(String mail,String mdp) throws DBException {
		try { 
			String query = "SELECT * FROM Magasin WHERE mailMagasin=\"" + mail + "\" and motDePasseMagasin=\"" + mdp + "\"";

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

		}catch (SQLException e) {
			throw new DBException("DBMagasinTools.magasinExistance : " + e.getMessage());
		}
	}


}
