package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test4 extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane stackPane = new StackPane();
		Label l = new Label("Label");
		l.setAlignment(Pos.CENTER);
		
		stackPane.getChildren().add(l);
		StackPane.setAlignment(l, Pos.CENTER);
		
		stackPane.setOnMouseClicked(e->{
			l.setText(e.getX()+","+e.getY());
		});
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(stackPane);
		BorderPane.setAlignment(stackPane, Pos.CENTER);
		
		Scene scene = new Scene(borderPane,500,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest4");
		primaryStage.show();
	}

}
