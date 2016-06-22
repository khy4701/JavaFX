package Dialog;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controller implements Initializable{

	private Stage primaryStage; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setPrimaryStage(Stage pri_stage)
	{
		// Main에서 받아온 Stage.
		primaryStage = pri_stage;
	}
	
	// FileChooser 창 만들기 - ExtensionFilter를 작성하여 Open시 파일을 필터함.
	public void handleOpenFileChooser(ActionEvent e)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text files","*.txt"),
				new ExtensionFilter("Image files","*.png","*.jpg","*.gif"),
				new ExtensionFilter("Audio files","*.wav","*.mp3","*.aac"),
				new ExtensionFilter("All files","*.*")
				);
		
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if(selectedFile != null)
		{
			System.out.println(selectedFile.getPath());
		}
	}
		
	public void handleSaveFileChooser(ActionEvent e)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All files","*.*"));
		
		File selectedFile = fileChooser.showSaveDialog(primaryStage);
		if(selectedFile != null)
		{
			System.out.println(selectedFile.getPath());
		}
	}
	
 
	public void handleDirectoryChooser(ActionEvent e)
	{
		DirectoryChooser d_chooser = new DirectoryChooser();
		File selectedFile = d_chooser.showDialog(primaryStage);
		if(selectedFile != null)
		{
			System.out.println(selectedFile.getPath());
		}
	}
	
	
	public void handlePopup(ActionEvent e) throws IOException
	{
		Popup popup = new Popup();
		
		HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("popup.fxml"));
		
		// Popup.fxml 에 ImageView, Label을 추가하고 ID를 imgMessage, lblMessage로 정의한다.
		// lookup() 함수가 해당 hbox에 존재하는 ImageView, Label의 id가 매개변수로 오는 값과 비교하여 일치하면 가져온다.
		ImageView imageView = (ImageView) hbox.lookup("#imgMessage");
		
		imageView.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));
		imageView.setOnMouseClicked(event->popup.hide());
		
		Label lblMessage = (Label)hbox.lookup("#lblMessage");
		lblMessage.setText("메시지가 왔습니다.");
		lblMessage.setFont(new Font(50));
		
		popup.getContent().add(hbox);
		//popup.setAutoHide(true);
		popup.show(primaryStage);
	}
	
	public void handleCustom(ActionEvent e) throws IOException
	{
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);   // Modal 형태( 현재 창만 사용 ) 
		dialog.initOwner(primaryStage);
		
		/*
		 * [ 방법 2 ]  @FXML private Button btnOk 이외에 FXML의 객체 아이디를 불러와 사용하는 방법. 
		 * 1. custom_dialog.fxml 을 생성한다. ( AnchorPane \ Label(txtTitie) , Button(btnOk ) 형태
		 * 2. 여기의 메소드에서 FXMLLoader.load 함수를 통해 fxml 파일을 불러오고 파일에 있는 rootContainer인 AnchorPane 객체를 불러온다.
		 * 3. AnchorPane 객체에 나열되있는 것 중에, txtTitle, btnOk 아이디를 가지는 Label, Button의 객체를 불러와 사용한다.
		 */
		
		AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("custom_dialog.fxml"));
		
		Label txtTitle = (Label) anchorPane.lookup("#txtTitle");
		txtTitle.setText("확인하셨습니까");
		
		Button btnOk = (Button) anchorPane.lookup("#btnOk");
		btnOk.setOnAction(event->dialog.close());
		
		Scene scene = new Scene(anchorPane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();
	}

}
