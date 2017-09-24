package WestendTheatre.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;


public class Plan {
	/*
	 * Connect to Sqlite database
	 * 
	 */
	static Connection connection;
	public Plan(){
		connection = SqliteConnection.Connector();
	}
	//get member information
	public List<String> memberlist(){
		List<String> row = FXCollections.observableArrayList();
		Statement st;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select MemberID,MemberName,MemberBalance from MemberInfo");
		while(rs.next()){
			for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
				row.add(rs.getString(i));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return row;
}
	//get plan type
	public List<String> getPlan(String memberID){
		List<String> bal = new ArrayList<>();
		String SQL = "select MemberType from MemberInfo where MemberID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, memberID);
			rs = ps.executeQuery();
			if(rs.next()){
				bal.add(rs.getString(1));
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
	// change plan type ,connect to upgrade plan controller
	public boolean changePlan(String memberType, String MemberID) throws SQLException{
		String SQL = "Update MemberInfo set MemberType = ? where MemberID = ?";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, memberType);
			ps.setString(2, MemberID);

			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
}
