package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Test1 extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		
		
		
		Circle circle1 = new Circle(150,150,150,Color.YELLOW);
		Circle circle2 = new Circle(75,90,20,Color.BLACK);
		Circle circle3 = new Circle(225,90,20,Color.BLACK);
		
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[] {
				150.0,120.0,
				130.0,170.0,
				170.0,170.0
				
		});
		polygon.setFill(Color.RED);
		
		Ellipse ellipse1 = new Ellipse();
		ellipse1.setCenterX(75.0f);
		ellipse1.setCenterY(90.0f);
		ellipse1.setRadiusX(40.0f);
		ellipse1.setRadiusY(30.0f);
		ellipse1.setStroke(Color.BLACK);
		ellipse1.setFill(null);
		Ellipse ellipse2 = new Ellipse();
		ellipse2.setCenterX(225.0f);
		ellipse2.setCenterY(90.0f);
		ellipse2.setRadiusX(40.0f);
		ellipse2.setRadiusY(30.0f);
		ellipse2.setStroke(Color.BLACK);
		ellipse2.setFill(null);
		
		Arc arc = new Arc();
		arc.setCenterX(150.0f);
		arc.setCenterY(200.0f);
		arc.setRadiusX(60.0f);
		arc.setRadiusY(30.0f);
		arc.setStartAngle(200.0f);
		arc.setLength(140.0f);
		arc.setType(ArcType.OPEN);
		arc.setFill(Color.BLUE);
		
		
		
		
		pane.getChildren().add(circle1);
		pane.getChildren().add(circle2);
		pane.getChildren().add(circle3);
		pane.getChildren().add(polygon);
		pane.getChildren().add(ellipse1);
		pane.getChildren().add(ellipse2);
		pane.getChildren().add(arc);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowSmileFace");
		primaryStage.show();
	}

}
