package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test7 extends Application {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane stackPane = new StackPane();
		TextField tf = new TextField();
		stackPane.getChildren().add(tf);
		tf.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				System.exit(0);
			}
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(stackPane);

		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest7");
		primaryStage.show();
	}

}
