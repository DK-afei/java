package project;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Test2 extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		
		Polyline polyline = new Polyline();
		ObservableList<Double> list = polyline.getPoints();
		double scaleFactor = 0.0125;
		for(int x=-100;x<=100;x++)
		{
			list.add(x+120.0);
			list.add(-scaleFactor*x*x+120);
		}
		Line line1 = new Line();
		line1.setStartX(0);
		line1.setStartY(120);
		line1.setEndX(240);
		line1.setEndY(120);
		Line line2 = new Line();
		line2.setStartX(120);
		line2.setStartY(0);
		line2.setEndX(120);
		line2.setEndY(120);
		
		pane.getChildren().add(polyline);
		pane.getChildren().add(line1);
		pane.getChildren().add(line2);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("f(x)=x*x");
		primaryStage.show();
	}

}
