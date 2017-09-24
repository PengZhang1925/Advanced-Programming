package WestendTheatre.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class upgradePlanController implements Initializable{
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private Label planCheck;
	
	@FXML
	private TextField memberID;
	
	@FXML
	private TextField planType;
	
	Plan plan = new Plan();
	//back to previous page
	public void cancelUpgrade(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//upgrade the plan then jump to topup page
	public void jumpToTopUp(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("topUpView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//change the plan in database for corresponding user
	public void upgradePlan(ActionEvent event) throws SQLException{
		String type = planType.getText();
		String id = memberID.getText();
			plan.changePlan(type, id);

	}
	//display the plan when the memberid is typed in textfield
	public void checkPlan(ActionEvent event) throws Exception{
		try{
			ObservableList<String> check = FXCollections.observableArrayList(plan.getPlan(memberID.getText()));
			planCheck.setText(check.get(0));
		}catch(Exception e){
			e.printStackTrace();
			planCheck.setText("ID not Found");
		}
	}
	//initialize the label
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		planCheck.setText(null);
	}
}
