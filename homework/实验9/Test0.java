package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test0 extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane pane = new GridPane();
		
		Image image = new Image("image/us.gif");
		ImageView imageView1 = new ImageView(image);
		imageView1.setFitHeight(200);
		imageView1.setFitWidth(200);
		ImageView imageView2 = new ImageView(image);
		imageView2.setFitHeight(200);
		imageView2.setFitWidth(200);
		ImageView imageView3 = new ImageView(image);
		imageView3.setFitHeight(200);
		imageView3.setFitWidth(200);
		ImageView imageView4 = new ImageView(image);
		imageView4.setFitHeight(200);
		imageView4.setFitWidth(200);
		
		
		
		pane.add(imageView1, 0, 0);
		pane.add(imageView2, 0, 1);
		pane.add(imageView3, 1, 0);
		pane.add(imageView4, 1, 1);
		
		
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowImage");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
