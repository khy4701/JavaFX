package ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class v_control implements Initializable {

	@FXML private ListView<String> LstView;
	@FXML private TableView<Phone> TblView;
	@FXML private ImageView ImgView;
	@FXML private Button Ok;
	@FXML private Button Cancel;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SetListView();
		
		SetTableView();
		
	}
	
	public void SetListView()
	{
		
		// ListView ������ ����
		LstView.setItems(FXCollections.observableArrayList("������S1","������S2","������S3", "������S4", "������S5", "������S6", "������S7"));
		LstView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				TblView.getSelectionModel().select(newValue.intValue()); // ����� �ε����� �� ����
				TblView.scrollTo(newValue.intValue()); // ����� �ε��� ��ġ�� ��ũ�� ��Ŵ.
			}

		});		
	}
	
	public void SetTableView()
	{
		// TableView ����Ʈ ������ ����
		ObservableList phoneList = FXCollections.observableArrayList(new Phone("������ S1", "phone01.png"),
				new Phone("������ S2", "phone02.png"), new Phone("������ S3", "phone03.png"),
				new Phone("������ S4", "phone04.png"), new Phone("������ S5", "phone05.png"),
				new Phone("������ S6", "phone06.png"), new Phone("������ S7", "phone07.png"));
		
		// ù��° ��(����)�� �� Offset
		TableColumn smartphone = TblView.getColumns().get(0);
		smartphone.setCellValueFactory(new PropertyValueFactory("smartphone")); 
		
		/*
		 * PropertyValueFactory �Լ��� �����ڴ�   TableView<Phone> ���� ������ Phone Ŭ������ �޼ҵ��� Callback �Լ� ����
		 *  smartphone�� PhoneŬ������ getSmartphone �޼ҵ忡�� get�� ������ �����̴�.
		 */
		
		smartphone.setStyle("-fx-alignment: CENTER");
				
		// �ι�° ��(����)�� �� Offset
		TableColumn image = TblView.getColumns().get(1);
		image.setCellValueFactory(new PropertyValueFactory("image"));
		image.setStyle("-fx-alignment: CENTER");
				
		// ���� ����Ʈ -> ���̺� ����
		TblView.setItems(phoneList);
		
		// TableView Listener		
		TblView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Phone>() {

					@Override
					public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
						// TODO Auto-generated method stub
						if(newValue != null)
						{
							ImgView.setImage(new Image(getClass().getResource("images/"+newValue.getImage()).toString()));
						}
					}
					
				});
		
	}
	
	public void SubmitAction(ActionEvent e)
	{
		String item = LstView.getSelectionModel().getSelectedItem();
		System.out.println("ListView - item :" + item);
		
		Phone phone = TblView.getSelectionModel().getSelectedItem();
		System.out.println("TableView - phone :" + phone.getSmartphone());
	}
	
	public void CancelAction(ActionEvent e)
	{
		Platform.exit();
	}

}
