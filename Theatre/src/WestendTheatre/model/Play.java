package WestendTheatre.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;


public class Play {
	
	/*
	 * Connect to Sqlite database
	 * check play information
	 */
	static Connection connection;
	public Play(){
		connection = SqliteConnection.Connector();
	}
	//get play list when play name is verified
	public List<String> playlist(){
		List<String> data = FXCollections.observableArrayList();
		Statement st;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from PlayInfo order by playName");
		while(rs.next()){
			for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
				data.add(rs.getString(i));
			}
		}
		return data;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;

}
	// get play name and show it orderly
	public List<String> playname(){
		List<String> data = FXCollections.observableArrayList();
		Statement st;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select playName from PlayInfo order by playName");
		while(rs.next()){
			for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
				data.add(rs.getString(i));
			}
		}
		return data;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
}
	// connect to add play controller
	public boolean addplay(String playName,String playType) throws SQLException{
		String SQL = "Insert INTO PlayInfo(playName,playType) VALUES(?,?)";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, playName);
			ps.setString(2, playType);
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	//get play type when play name is verified
	public List<String> getType(String playName){
		List<String> bal = new ArrayList<>();
		String SQL = "select playType from PlayInfo where playName = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, playName);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}

			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return bal;
	}
	//get the corresponding price when play type is verified
	public List<String> getPrice(String playType){
		List<String> bal = new ArrayList<>();
		String SQL = "select Price from Price where playType = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, playType);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return bal;
	}
}
