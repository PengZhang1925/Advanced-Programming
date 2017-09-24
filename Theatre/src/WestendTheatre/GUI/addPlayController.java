package WestendTheatre.GUI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import WestendTheatre.model.Play;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
public class addPlayController implements Initializable{
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private TextField playName;
	
	@FXML
	private TextField playType;
	
	@FXML
	private ListView<String> playList;
	
	Play play =  new Play();
	ObservableList<String> list = FXCollections.observableArrayList(play.playlist());
	//Initialize the listView
	public void initialize(URL location, ResourceBundle resources){
		playList.setItems(list);
	}
	//fetch data from textfield ,transfer to model
	public void addPlaybutton(ActionEvent event) throws SQLException{
		play.addplay(playName.getText(),playType.getText());
		ObservableList<String> newlist = FXCollections.observableArrayList(play.playlist());
		playList.setItems(newlist);
	}
	
	//back to previous page
	public void CancelPlay(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
