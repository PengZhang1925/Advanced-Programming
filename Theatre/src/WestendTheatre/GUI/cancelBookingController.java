package WestendTheatre.GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import WestendTheatre.model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class cancelBookingController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private ListView<String> bookingList;
	
	Booking book = new Booking();
	
	
	ObservableList<String> info = FXCollections.observableArrayList();
	//initialize the listview to show existing booking
	public void initialize(URL location, ResourceBundle resources){
		 
		 try {	 
			book.creatTxtFile(); 
			String txt = book.readTxtFile();
			info.add(txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
		bookingList.setItems(info);
		bookingList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	//delete the booking information from txt 
	public void CancelBooking(ActionEvent event) throws Exception{
		File f = new File("F:/study tool/AP assignment/Theatre/bookinginfo.txt");
		FileWriter fw =  new FileWriter(f);
		fw.write("");
		fw.close();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {	 
			book.creatTxtFile(); 
			String txt = book.readTxtFile();
			list.add(txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		bookingList.setItems(list);
	}
	//back to previous page
	public void Back(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("MemberView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
