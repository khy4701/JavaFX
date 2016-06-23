package JavaFXThread.PlatformRunLater;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class controller implements Initializable {

	@FXML private Label lblTime;
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	
	private boolean stop;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		btnStart.setOnAction(event->btnStartAction());
		btnStop.setOnAction(event->btnStopAction());
	}
	
	public void btnStartAction()
	{
		stop = false;
		
		Thread th = new Thread(new Runnable()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				
				while(!stop)
				{
					String strTime = sdf.format(new Date());

					Platform.runLater(()->{
						lblTime.setText(strTime);
					});

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
		
		th.setDaemon(true);
		th.start();
	}
	
	public void btnStopAction()
	{
		stop = true;
	}
}
