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

	public static int getIdMagasin(String mail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
