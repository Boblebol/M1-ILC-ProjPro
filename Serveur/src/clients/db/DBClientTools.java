package clients.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;


public class DBClientTools {

	public static boolean clientExistance(String mail)throws DBException {
		try {
			String query = "SELECT * FROM Client WHERE mailClient=\"" + mail + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();

			rs.close();
			s.close();
			c.close();

			return bool;
		}catch (SQLException e) {
			throw new DBException("DBClientTools.ClientExistance : " + e.getMessage());
		}
	}

	
	
	public static int getIdClient(String mail) throws DBException{
		try {
			int id = -1;
			String query = "SELECT idClient FROM Client WHERE mailClient=\"" + mail + "\"";
			
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			if (bool) {
				id = rs.getInt("idClient"); 
			}

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return id;
		}catch (SQLException e) {
			throw new DBException("DBClientTools.getIdClient : " + e.getMessage());
		}
	}

	
}
