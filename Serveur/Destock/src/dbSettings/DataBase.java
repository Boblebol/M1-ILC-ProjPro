package dbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * Classe qui possede les fonctions de connection aux base de données.
 * 
 */

public class DataBase {

	private static DataBase database = null;
	private DataSource dataSource;


	/**
	 * Constructeur de la base de donnee SQL
	 * 
	 * @param jndiname
	 * @throws SQLException
	 */
	public DataBase(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "
					+ e.getMessage());
		}
	}


	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}


	/**
	 * 
	 * @return une connection sur la base SQL
	 * @throws SQLException
	 */
	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysql_pooling == false) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				return (DriverManager.getConnection("jdbc:mysql://"
						+ DBStatic.mysql_host + "/" + DBStatic.mysql_db,
						DBStatic.mysql_username, DBStatic.mysql_password));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			if (database == null) {
				database = new DataBase("jdbc/db");
			}
			return (database.getConnection());
		}
	}


}
