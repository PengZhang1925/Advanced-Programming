package WestendTheatre.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import java.sql.*;
import WestendTheatre.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AdminLoginController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	public Admin admin = new Admin();
	@FXML
	private Label isConnected;
	
	@FXML
	private Label Status;
	
	@FXML
	private TextField UserName;
	
	@FXML
	private TextField PassWord;
	//read account from textfield, check from database
	public void Login(ActionEvent event) throws Exception{
	try{
		if(admin.isLogin(UserName.getText(),PassWord.getText())){
			isConnected.setText("Login Success");
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}else{
			Status.setText("Login Failed");
		}
	}catch(SQLException e){
		Status.setText("Login Failed");
		e.printStackTrace();
	}
			
	}
	
	//back to previous page
	public void CancelLogin(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("TheatreView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//initialize the label, display weather the account is correct or not
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(admin.isDbConnected()){
			isConnected.setText("Connected");
		}else{
			isConnected.setText("Not Connected");
		}
	}
	
}
