package project;
import java.io.File;
import java.net.URI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class T3 extends Application {
	File file = new File("e://广东雨神 - 广东十年爱情故事.mp3");
	URI url = file.toURI();
  private String MEDIA_URL = url.toString();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Media media = new Media(MEDIA_URL);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaView mediaView = new MediaView(mediaPlayer);

    Button playButton = new Button("Play");
    Button loopButton = new Button("Loop");
    playButton.setOnAction(e -> {
      if (playButton.getText().equals("Play")) {
        mediaPlayer.play();
        playButton.setText("Stop");
      } else {
        mediaPlayer.pause();
        playButton.setText("Play");
      }
    });

    Button rewindButton = new Button("Back");
    rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
    
    Slider slVolume = new Slider();
    slVolume.setPrefWidth(150);
    slVolume.setMaxWidth(Region.USE_PREF_SIZE);
    slVolume.setMinWidth(30);
    slVolume.setValue(50);
    mediaPlayer.volumeProperty().bind(
      slVolume.valueProperty().divide(100));

    HBox hBox = new HBox(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(playButton,loopButton, rewindButton,
      new Label("Volume"), slVolume);

    BorderPane pane = new BorderPane();
    pane.setCenter(mediaView);
    pane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 80);
    primaryStage.setTitle("MediaDemo"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage    
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
	  Application.launch(args);
  }
}
