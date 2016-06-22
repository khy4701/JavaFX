package MediaControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class m_control implements Initializable {
	
	@FXML
	private MediaView videoView;
	@FXML
	private ImageView imageView;
	@FXML
	private Button play;
	@FXML
	private Button pause;
	@FXML
	private Button stop;
	@FXML
	private Slider soundSlider;
	@FXML
	private ProgressBar timeProgress;
	@FXML
	private ProgressIndicator timeIdent;
	@FXML
	private Label timeLabel;

	private boolean endOfMedia; // 재생 완료 확인 플래그

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);

		videoView.setMediaPlayer(mediaPlayer);

		// MediaPlayer Play,Pasue,Stop 관련 스레드 및 리스너 생성
		setMediaPlayerAction(mediaPlayer);

		// Button 관련 메소드
		setButtonAction(mediaPlayer);
		
		// Volume 관련 메소드
		setVolumeAction(mediaPlayer);
		
	}

	public void setMediaPlayerAction(MediaPlayer mediaPlayer) {
		
		// 준비 상태가 되면 --> 버튼 활성/비활성, Listenr 등록, media 시작.
		mediaPlayer.setOnReady(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				play.setDisable(false);
				pause.setDisable(true);
				stop.setDisable(true);

				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
							Duration newValue) {
						// TODO Auto-generated method stub

						setProgressbarAction(mediaPlayer);
						
					}
				});

				mediaPlayer.setAutoPlay(true);
			}
		});

		mediaPlayer.setOnPlaying(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				play.setDisable(true);
				pause.setDisable(false);
				stop.setDisable(false);

			}
		});

		mediaPlayer.setOnPaused(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				play.setDisable(false);
				pause.setDisable(true);
				stop.setDisable(false);

			}
		});

		mediaPlayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				play.setDisable(false);
				pause.setDisable(true);
				stop.setDisable(true);
				endOfMedia = true;
				
				timeProgress.setProgress(1.0);
				timeIdent.setProgress(1.0);
			}
		});

		mediaPlayer.setOnStopped(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				play.setDisable(false);
				pause.setDisable(true);
				stop.setDisable(true);
			}
		});

	}

	
	public void setButtonAction(MediaPlayer mediaPlayer) {

		// 미디어 플레이어 동작에 관련된 메소드

		play.setOnAction(event -> {
			if (endOfMedia) {
				mediaPlayer.stop();
				mediaPlayer.seek(mediaPlayer.getStartTime()); // 끝나면 처음으로
																// offset을 돌림.d
			}
			mediaPlayer.play(); // media 재생.
			endOfMedia = false;
		});

		pause.setOnAction(event -> mediaPlayer.pause());
		stop.setOnAction(event -> mediaPlayer.stop());

	}
	
	public void	setVolumeAction(MediaPlayer mediaPlayer)
	{
		soundSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(soundSlider.getValue()/ 100.0);
			}
		});
		
		soundSlider.setValue(50);
		
	}
	
	
	public void	setProgressbarAction(MediaPlayer mediaPlayer)
	{
		double progress = mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
				
		timeProgress.setProgress(progress);
		timeIdent.setProgress(progress);
		
		timeLabel.setText((int)mediaPlayer.getCurrentTime().toSeconds()+"/"+(int)mediaPlayer.getTotalDuration().toSeconds()
		 ) ;
	}
	

	
}
