package WestendTheatre.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import WestendTheatre.model.Booking;
import WestendTheatre.model.DiscountBooking;
import WestendTheatre.model.Member;
import WestendTheatre.model.Play;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class makeBookingController implements Initializable {
	/**
	 * 
	 * Setting buttons and connecting  model;
	 * 
	 */
	//Initialize fxid to scene builder
	@FXML
	private ToggleButton tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10,tb11,tb12,tb13,tb14,tb15,tb16,tb17,tb18,tb19,tb20,tb21,tb22,tb23,tb24,tb25,tb26,tb27,tb28,tb29,tb30,tb31,tb32,tb33,tb34,tb35,tb36,tb37,tb38,tb39,tb40,tb41,tb42,tb43,tb44,tb45,tb46,tb47,tb48,tb49,tb50;
	
	@FXML
	private ComboBox<String> playSelect;
	
	@FXML
	private ComboBox<String> dateSelect;
	
	@FXML
	private ComboBox<String> timeSelect;
	
	@FXML
	private Label amount;
	
	@FXML
	private Label price;
	
	@FXML
	private ListView<String> memberInfo;
	
	@FXML
	private ListView<String> seatSelected;
	
	@FXML
	private TextField promotionCode;
	
	Member member = new Member();
	Play play = new Play();
	Show show = new Show();
	Booking book =  new Booking();
	DiscountBooking db = new DiscountBooking();
	
	private ArrayList<ToggleButton> bt = new ArrayList<>();
	ObservableList<String> list = FXCollections.observableArrayList();
	ObservableList<String> discount = FXCollections.observableArrayList();
	ObservableList<String> playname = FXCollections.observableArrayList(play.playname());
	ObservableList<String> memberinfo = FXCollections.observableArrayList(member.showMemberfromID(member.getID().get(0)));
	ObservableList<String> id = FXCollections.observableArrayList(member.getID());
	//confirm booking , and send booking information to txt, calculate the total price
	public void buttonSelect(ActionEvent event) throws SQLException, IOException{
		int count = 0;
		double t = 0;
		for(int i=0; i<50; i++){
			if(bt.get(i).isSelected()){
				count++;
				list.add(bt.get(i).getText());
			}
		}
		String playprice = play.getPrice(play.getType(playSelect.getValue()).get(0)).get(0);
		String discount = member.getRate(play.getType(playSelect.getValue()).get(0), member.getPlanType(member.getID().get(0)).get(0)).get(0);
		t = count*Integer.parseInt(playprice)*Integer.parseInt(discount)*0.01;

		String s = Double.toString(t);
		String p = Integer.toString(count);
		seatSelected.setItems(list);
		price.setText(s);
		amount.setText(p);
		String ID = id.get(0);
		 book.creatTxtFile();  
	     book.readTxtFile();  
	     book.writeTxtFile(ID,list.toString(),playSelect.getValue(),dateSelect.getValue(),timeSelect.getValue()); 
	}
	//confirm discount booking, read promotion code, check to database, calculate the total price
	public void discountBooking(ActionEvent event) throws Exception{
		int count = 0;
		double t = 0;
		for(int i=0; i<50; i++){
			if(bt.get(i).isSelected()){
				count++;
				discount.add(bt.get(i).getText());
			}
		}
		String playprice = play.getPrice(play.getType(playSelect.getValue()).get(0)).get(0);
		String plan = member.getRate(play.getType(playSelect.getValue()).get(0), member.getPlanType(member.getID().get(0)).get(0)).get(0);
		String promotion = db.getRate(promotionCode.getText()).get(0);
		t = count*Integer.parseInt(playprice)*Integer.parseInt(plan)*0.01*Integer.parseInt(promotion)*0.01;

		String s = Double.toString(t);
		String p = Integer.toString(count);
		seatSelected.setItems(discount);
		price.setText(s);
		amount.setText(p);
		book.creatTxtFile();  
	     book.readTxtFile();  
	     book.writeTxtFile(id.get(0),list.toString(),playSelect.getValue(),dateSelect.getValue(),timeSelect.getValue()); 
	}
	//display corresponding date when the play is selected
	public void playSelected(ActionEvent event) throws Exception{
		ObservableList<String> showdate = FXCollections.observableArrayList(show.showdate(playSelect.getValue()));
		dateSelect.setItems(showdate);
	}
	//display corresponding time when play and date is selected
	public void dateSelected(ActionEvent event) throws Exception{
		ObservableList<String> showtime = FXCollections.observableArrayList(show.showtime(playSelect.getValue(),dateSelect.getValue()));
		timeSelect.setItems(showtime);
	}
	//back to previous page
	public void cancelMakeBooking(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("MemberView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//initialize the toggle buttons and listview, fetch show information and display on combobox
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bt.add(tb1);bt.add(tb2);bt.add(tb3);bt.add(tb4);bt.add(tb5);bt.add(tb6);bt.add(tb7);bt.add(tb8);bt.add(tb9);bt.add(tb10);bt.add(tb11);bt.add(tb12);bt.add(tb13);bt.add(tb14);bt.add(tb15);bt.add(tb16);bt.add(tb17);bt.add(tb18);bt.add(tb19);bt.add(tb20);bt.add(tb21);bt.add(tb22);bt.add(tb23);bt.add(tb24);bt.add(tb25);
		bt.add(tb26);bt.add(tb27);bt.add(tb28);bt.add(tb29);bt.add(tb30);bt.add(tb31);bt.add(tb32);bt.add(tb33);bt.add(tb34);bt.add(tb35);bt.add(tb36);bt.add(tb37);bt.add(tb38);bt.add(tb39);bt.add(tb40);bt.add(tb41);bt.add(tb42);bt.add(tb43);bt.add(tb44);bt.add(tb45);bt.add(tb46);bt.add(tb47);bt.add(tb48);bt.add(tb49);bt.add(tb50);
		memberInfo.setItems(memberinfo);
		playSelect.setItems(playname);
		dateSelect.setItems(null);
		timeSelect.setItems(null);
	}
}
