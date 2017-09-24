package WestendTheatre.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Show {
	/*
	 * Connect to Sqlite database
	 * check show information
	 */
	static Connection connection;
	public Show(){
		connection = SqliteConnection.Connector();
	}
	
	public List<String> showInfo(){
		List<String> info = new ArrayList<String>();
		Statement st;
		try{
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from ShowInfo order by ShowName");
			while(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
					info.add(rs.getString(i));
				}
			}return info;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	//get show information when show name is verified
	public List<String> showlist(String showName){
		List<String> data = new ArrayList<String>();
		PreparedStatement ps =null;
		String SQL = "select * from ShowInfo where ShowName = ?";
		try {
			ps = connection.prepareStatement(SQL);
			ps.setString(1, showName);
			ResultSet rs = ps.executeQuery();
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
		return null;
}
	//get distinct show date when show name is verified
	public List<String> showdate(String showName){
		List<String> data = new ArrayList<String>();
		PreparedStatement ps =null;
		String SQL = "select distinct ShowDate from ShowInfo where ShowName = ?";
		try {
			ps = connection.prepareStatement(SQL);
			ps.setString(1, showName);
			ResultSet rs = ps.executeQuery();
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
		return null;
}
	//get show time when show name and show date is verified
	public List<String> showtime(String showName,String showDate){
		List<String> data = new ArrayList<String>();
		PreparedStatement ps =null;
		String SQL = "select ShowTime from ShowInfo where ShowName = ? and ShowDate = ?";
		try {
			ps = connection.prepareStatement(SQL);
			ps.setString(1, showName);
			ps.setString(2, showDate);
			ResultSet rs = ps.executeQuery();
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
		return null;
}
	// add new show information into database
	public boolean addshow(String ShowName,String ShowVenue,String ShowDate,String ShowTime) throws SQLException{
		String SQL = "Insert INTO ShowInfo(ShowName,ShowVenue,ShowDate,ShowTime) VALUES(?,?,?,?)";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, ShowName);
			ps.setString(2, ShowVenue);
			ps.setString(3, ShowDate);
			ps.setString(4, ShowTime);
			
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
