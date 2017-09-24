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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class searchShowController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private ListView<String> showView;
	
	@FXML
	private TextField showName;
	
	@FXML
	private Label status;
	
	Show show = new Show();
	// fetch data from database when the show name is already typed in textfield
	// check show name is correct or not then display show information on listview
	public void SearchShow(ActionEvent event) throws Exception{
		try{
		ObservableList<String> oListStavaka = FXCollections.observableArrayList(show.showlist(showName.getText()));
		showView.setItems(oListStavaka);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//initialize the listview
	public void initialize(URL location, ResourceBundle resources){
		showView.setItems(null);
	}
	//back to previous page
	public void CancelSearch(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
