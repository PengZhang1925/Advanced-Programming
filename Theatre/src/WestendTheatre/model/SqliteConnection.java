package WestendTheatre.model;
import java.sql.*;

public class SqliteConnection {
	/*
	 * Connect to Sqlite database
	 * The connection between database and java
	 */
	public static Connection Connector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
			return conn;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
