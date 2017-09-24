package WestendTheatre.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javafx.collections.FXCollections;

public class DiscountBooking extends Booking {
	/*
	 * Connect to Sqlite database
	 * check get the discount rate when promotion code is typed and correct
	 */
	Connection conn;
	public DiscountBooking(){
		conn = SqliteConnection.Connector();
	}
	
	public List<String> getRate(String code) throws SQLException{
		List<String> rate = FXCollections.observableArrayList();
		String SQL = "select Rate from PromotionCode where Code = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(SQL);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount(); i++){
					rate.add(rs.getString(i));
				}

			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			rs.close();
			ps.close();
		}	
		return rate;
	}
}
