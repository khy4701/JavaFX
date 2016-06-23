package ChangeDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class rootControl implements Initializable{

	@FXML private Button goLogin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub				
		goLogin.setOnAction(event->BtnLoginAction());
	}
	

	public void BtnLoginAction()
	{
		try {
			Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			StackPane root = (StackPane) goLogin.getScene().getRoot();
			root.getChildren().add(login);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
