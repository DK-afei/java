package project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Test8 extends Application {
	private ArrayList<Circle> circles = new ArrayList();
//	private Circle[] circles = new Circle[100];
	private static int i = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setOnMouseClicked(e->{
			circles.add(i,new Circle(e.getX(),e.getY(),5));
			circles.get(i).setStroke(Color.BLACK);
			circles.get(i).setFill(null);
			if(e.getButton()==MouseButton.PRIMARY)
			{
				pane.getChildren().add(circles.get(i));
				i++;
			}
			else if(e.getButton()==MouseButton.SECONDARY)
			{
				for(int j=0;j<i;j++)
				{
					if(Math.abs(e.getX()-circles.get(j).getCenterX())<=5&&Math.abs(e.getY()-circles.get(j).getCenterY())<=5)
					
						{
							pane.getChildren().remove(circles.get(j));
							circles.remove(j);
							i--;
						}
				}
			}
		});
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);

		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest8");
		primaryStage.show();
	}

}
