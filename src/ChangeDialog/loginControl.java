package ChangeDialog;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class loginControl implements Initializable{

	@FXML private AnchorPane login;
	@FXML private Button goMain;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub				
		goMain.setOnAction(event->BtngoMainAction());
	}
	

	public void BtngoMainAction()
	{
			
			StackPane root = (StackPane) goMain.getScene().getRoot();
			root.getChildren().remove(login);
		
	}
}
