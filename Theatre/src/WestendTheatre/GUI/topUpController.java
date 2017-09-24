package WestendTheatre.GUI;

import WestendTheatre.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class topUpController {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private Label balanceStatus;
	
	@FXML
	private TextField memberID;
	
	@FXML
	private TextField amount;
	
	int newbalance;
	Member member = new Member();
	public void balanceCheck(ActionEvent event) throws Exception{
		ObservableList<String> check = FXCollections.observableArrayList(member.getBalance(memberID.getText()));
		balanceStatus.setText(check.get(0));
	}
	//fetch data from textfield and add the price to database
	public void topUp(ActionEvent event) throws Exception{
		ObservableList<String> newcheck = FXCollections.observableArrayList(member.getBalance(memberID.getText()));
		newbalance = Integer.parseInt(amount.getText()) + Integer.parseInt(newcheck.get(0).toString());	
		member.addbalance(newbalance, memberID.getText());
		ObservableList<String> balanceAdded = FXCollections.observableArrayList(member.getBalance(memberID.getText()));
		balanceStatus.setText(balanceAdded.get(0).toString());
	}
	//back to previous page
	public void cancelTopUp(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// connect to upgradePlan page, when the price get to the right number, it can bring to upgrade page
	public void jumpToUpgrade(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("upgradePlanView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
