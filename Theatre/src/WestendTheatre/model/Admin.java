package WestendTheatre.model;
import java.sql.*;

public class Admin extends User {
	/*
	 * Connect to Sqlite database
	 * check admin account
	 */
	static Connection connection;
	public Admin(){
		connection = SqliteConnection.Connector();
		if(connection == null) {
			System.out.println("connection not successful");
			System.exit(1);};
	}
	
	public boolean isDbConnected(){
		try{
			return !connection.isClosed();
		}catch(SQLException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String user, String pass) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "Select * from admin where username = ? and password = ?";
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,user);
			preparedStatement.setString(2,pass);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}finally{
			preparedStatement.close();
			resultSet.close();
		}
	}
}

