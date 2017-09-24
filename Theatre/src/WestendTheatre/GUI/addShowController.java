package WestendTheatre.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import WestendTheatre.model.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class addShowController implements Initializable{
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private ListView<String> showList;
	
	@FXML
	private TextField showName;
	
	@FXML
	private TextField showVenue;
	
	@FXML
	private DatePicker showDate;
	
	@FXML
	private TextField showTime;
	
	Show show = new Show();
	ObservableList<String> list = FXCollections.observableArrayList(show.showInfo());
	//initialize the listview, display show in data base
	public void initialize(URL location, ResourceBundle resources){
		showList.setItems(list);
		showList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	// add show to database, read data from textfield
	public void addShow(ActionEvent event) throws Exception{
		show.addshow(showName.getText(),showVenue.getText(),showDate.getValue().toString(),showTime.getText());
		ObservableList<String> newlist = FXCollections.observableArrayList(show.showInfo());
		showList.setItems(newlist);
	}
	//back to previous page
	public void CancelShow(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
