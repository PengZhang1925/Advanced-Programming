package WestendTheatre.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import WestendTheatre.model.Member;
import WestendTheatre.model.Plan;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addMemberController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private ListView<String> memberList;
	
	@FXML
	private TextField memberName;
	
	@FXML
	private TextField memberID;
	
	@FXML
	private TextField memberBalance;
	
	Plan plan = new Plan();
	Member member = new Member();
	ObservableList<String> list = FXCollections.observableArrayList(member.showMember());
	//Initialize the listView
	public void initialize(URL location, ResourceBundle resources){
		memberList.setItems(list);
	}
	//Set action when button clicked, fetch data from textfield, transfer to model
	public void addMember(ActionEvent event) throws Exception{
		member.addmember(memberName.getText(),memberID.getText(),memberBalance.getText());
		ObservableList<String> newlist = FXCollections.observableArrayList(member.showMember());
		memberList.setItems(newlist);
		
	}
	// back to previous page
	public void CanceladdMember(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
