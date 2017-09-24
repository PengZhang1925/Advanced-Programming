package WestendTheatre.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javafx.collections.FXCollections;



public class Member extends User {
	/*
	 * Connect to Sqlite database
	 * check member account
	 */
	
	static Connection connection;
	public Member(){
		connection = SqliteConnection.Connector();
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
		String query = "Select * from Member where username = ? and password = ?";
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
	//get member information from database and return it
	public List<String> showMember(){
		List<String> data = FXCollections.observableArrayList();
		Statement st;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from MemberInfo");
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
	//get member information when the member id is verified
	public List<String> showMemberfromID(String userID){
		List<String> bal = new ArrayList<>();
		String SQL = "select * from MemberInfo where MemberID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1;i<=rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
					}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
	
	//get the balance when the member id is verified
	public List<String> getBalance(String balance){
		List<String> bal = new ArrayList<>();
		String SQL = "select MemberBalance from MemberInfo where MemberID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, balance);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
	//connect to top up controller, update balance to memberInfo table when member ID is verified
	public boolean addbalance(int newbalance, String MemberID) throws SQLException{
		String SQL = "Update MemberInfo set MemberBalance = ? where MemberID = ?";
		PreparedStatement ps = null;
		String memberbalance = Integer.toString(newbalance);
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, memberbalance);
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
	//connect to add member controller, when all the member information is setted
	public boolean addmember(String MemberName,String MemberID,String MemberBalance) throws SQLException{
		String SQL = "Insert INTO MemberInfo(MemberID,MemberName,MemberBalance) VALUES(?,?,?)";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, MemberID);
			ps.setString(2, MemberName);
			ps.setString(3, MemberBalance);
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	//get member id from the temporary table "save", when the member login
	public List<String> getMemberID(String username){
		List<String> bal = new ArrayList<>();
		String SQL = "select MemberID from Member where username = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
	//save the member id when member login
	public boolean saveID(String id) throws SQLException{
		String SQL = "Update Save set ID = ?";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, id);

			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	//get id from "save", and return it
	public List<String> getID(){
		List<String> data = FXCollections.observableArrayList();
		Statement st;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select ID from Save");
		while(rs.next()){
			for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
				data.add(rs.getString(i));
			}
		}rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	}
	//connect to upgrade plan page, when member id is verified
	public List<String> getPlanType(String memberID){
		List<String> bal = new ArrayList<>();
		String SQL = "select MemberType from MemberInfo where MemberID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, memberID);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
	//get the discount rate when the play type of correspoding member is verified
	public List<String> getRate(String playType,String planType){
		List<String> bal = new ArrayList<>();
		String SQL = "select DiscountRate from Plan where PlayType = ? and PlanType = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, playType);
			ps.setString(2, planType);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					bal.add(rs.getString(i));
				}
			}return bal;
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
}
