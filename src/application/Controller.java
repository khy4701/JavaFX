package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable{

	@FXML private PasswordField passwd;
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private TextArea textfield;
	@FXML private DatePicker date;
	@FXML private ComboBox<String> combo1;
	@FXML private TextField subject;
	
	ObservableList<String> list = FXCollections.observableArrayList("공개","비공개");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		combo1.setItems(list); // ComboBox를 수행하기 위해선 ObservableList 클래스 객체 필요.
							   // 또는 FXML에서 수정하는 방법도 있음
		
		
		
	}
	
	public void SubmitAction(ActionEvent e)
	{
		String title = subject.getText();
		String pwd = passwd.getText();
		String str_public = combo1.getValue();
		LocalDate localDate = date.getValue(); 
		String content = textfield.getText();
		
		
		System.out.println("title :"+ title);
		System.out.println("pwd :"+ pwd);
		System.out.println("str_public :"+ str_public);
		
		if(localDate != null)
			System.out.println("dateExit: "+ localDate.toString());
		
		System.out.println("content : "+ content);
		
		
	}
	
	public void CancelAction(ActionEvent e)
	{
		Platform.exit();
		
	}
	

}

