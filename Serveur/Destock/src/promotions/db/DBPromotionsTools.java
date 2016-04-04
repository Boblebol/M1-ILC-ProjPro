package promotions.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import settings.dbSettings.DBException;
import settings.dbSettings.DataBase;

public class DBPromotionsTools {

	public static boolean PromotionExistance(String ref,int idmag)throws DBException {
		try { 
			String query = "SELECT * FROM Promotions WHERE referencePromo=\""+ref+"\" AND idMagasin="+idmag;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.isFirst() && rs.isLast();

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return bool;

		}catch (SQLException e) {
			throw new DBException("DBPromotionsTools.PromotionExistance : " + e.getMessage());
		}
	}

	public static int getIdPromo(String ref,int idmag)  throws DBException{
		try { 
			int retour = -1;
			String query = "SELECT idPromo FROM Promotions WHERE referencePromo=\""+ref+"\" AND idMagasin="+idmag;
			
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			if (bool) {
				retour = rs.getInt("idPromo"); 
				
			}

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

			return retour;
		}catch (SQLException e) {
			throw new DBException("DBPromotionsTools.getIdPromo : " + e.getMessage());
		}
	}

	
}
