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
		
		// ListView 데이터 생성
		LstView.setItems(FXCollections.observableArrayList("갤럭시S1","갤럭시S2","갤럭시S3", "갤럭시S4", "갤럭시S5", "갤럭시S6", "갤럭시S7"));
		LstView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				TblView.getSelectionModel().select(newValue.intValue()); // 변경된 인덱스의 행 선택
				TblView.scrollTo(newValue.intValue()); // 변경된 인덱스 위치로 스크롤 시킴.
			}

		});		
	}
	
	public void SetTableView()
	{
		// TableView 리스트 데이터 생성
		ObservableList phoneList = FXCollections.observableArrayList(new Phone("갤럭시 S1", "phone01.png"),
				new Phone("갤럭시 S2", "phone02.png"), new Phone("갤럭시 S3", "phone03.png"),
				new Phone("갤럭시 S4", "phone04.png"), new Phone("갤럭시 S5", "phone05.png"),
				new Phone("갤럭시 S6", "phone06.png"), new Phone("갤럭시 S7", "phone07.png"));
		
		// 첫번째 열(세로)에 들어갈 Offset
		TableColumn smartphone = TblView.getColumns().get(0);
		smartphone.setCellValueFactory(new PropertyValueFactory("smartphone")); 
		
		/*
		 * PropertyValueFactory 함수의 생성자는   TableView<Phone> 에서 생성한 Phone 클래스의 메소드의 Callback 함수 형태
		 *  smartphone은 Phone클래스의 getSmartphone 메소드에서 get을 제외한 글자이다.
		 */
		
		smartphone.setStyle("-fx-alignment: CENTER");
				
		// 두번째 열(세로)에 들어갈 Offset
		TableColumn image = TblView.getColumns().get(1);
		image.setCellValueFactory(new PropertyValueFactory("image"));
		image.setStyle("-fx-alignment: CENTER");
				
		// 만든 리스트 -> 테이블에 적용
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
