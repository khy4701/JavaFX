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
		primaryStage = pri_stage;
	}
	
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
		
		// Popup.fxml �� ImageView, Label�� �߰��ϰ� ID�� imgMessage, lblMessage�� �����Ѵ�.
		// lookup() �Լ��� �ش� hbox�� �����ϴ� ImageView, Label�� id�� �Ű������� ���� ���� ���Ͽ� ��ġ�ϸ� �����´�.
		ImageView imageView = (ImageView) hbox.lookup("#imgMessage");
		
		imageView.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));
		imageView.setOnMouseClicked(event->popup.hide());
		
		Label lblMessage = (Label)hbox.lookup("#lblMessage");
		lblMessage.setText("�޽����� �Խ��ϴ�.");
		lblMessage.setFont(new Font(50));
		
		popup.getContent().add(hbox);
		//popup.setAutoHide(true);
		popup.show(primaryStage);
	}
	
	public void handleCustom(ActionEvent e) throws IOException
	{
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		
		AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("custom_dialog.fxml"));
		
		Label txtTitle = (Label) anchorPane.lookup("#txtTitle");
		txtTitle.setText("Ȯ���ϼ̽��ϱ�");
		
		Button btnOk = (Button) anchorPane.lookup("#btnOk");
		btnOk.setOnAction(event->dialog.close());
		
		Scene scene = new Scene(anchorPane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();
	}

}
