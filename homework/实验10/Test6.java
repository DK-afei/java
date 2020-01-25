package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test6 extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane stackPane = new StackPane();
		Label l = new Label("Label");
		stackPane.getChildren().add(l);
		
		
		stackPane.setOnMousePressed(e->{
			l.setText(e.getX()+","+e.getY());
		});
		stackPane.setOnMouseReleased(e->{
			l.setText(null);
		});
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(stackPane);
		Scene scene = new Scene(borderPane,500,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest5");
		primaryStage.show();
	}

}
