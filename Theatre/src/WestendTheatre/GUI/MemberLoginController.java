package WestendTheatre.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.*;
import WestendTheatre.model.Member;
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

public class MemberLoginController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	public Member member = new Member();
	@FXML
	private Label isConnected;
	
	@FXML
	private Label Status;
	
	@FXML
	private TextField UserName;
	
	@FXML
	private TextField PassWord;
	
	List<String> ID;
	public void Login(ActionEvent event) throws Exception{
		ID = member.getMemberID(UserName.getText());
		
	try{
		if(member.isLogin(UserName.getText(),PassWord.getText())){
			isConnected.setText("Login Success");
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("MemberView.fxml"));
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
	}member.saveID(ID.get(0));		
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(member.isDbConnected()){
			isConnected.setText("Connected");
		}else{
			isConnected.setText("Not Connected");
		}
	}
	
}