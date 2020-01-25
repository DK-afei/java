package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Test1 extends Application{
	private CirclePane circlePane = new CirclePane();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		
		Button btn1 = new Button("Up");
		Button btn2 = new Button("Down");
		Button btn3 = new Button("Left");
		Button btn4 = new Button("Right");
		
		hBox.getChildren().addAll(btn1,btn2,btn3,btn4);
		
		btn1.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				circlePane.Up();
			}
	
		});
		btn2.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
				circlePane.Down();
		}

		});
		btn3.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
		public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
				circlePane.Left();
		}

		});
		btn4.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
				circlePane.Right();
		}

		});
		
		
		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(hBox);
		borderPane.setCenter(circlePane);
		BorderPane.setAlignment(hBox,Pos.CENTER);
		
		
		
		Scene scene = new Scene(borderPane,200,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest1");
		primaryStage.show();
		
	}
}
class CirclePane extends Pane
{
	private Circle circle = new Circle(100,100,10);
	public CirclePane()
	{
		getChildren().add(circle);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
	}
	public void Up()
	{
		circle.setCenterY(circle.getCenterY()+10);
	}
	public void Down()
	{
		circle.setCenterY(circle.getCenterY()-10);
	}
	public void Left()
	{
		circle.setCenterX(circle.getCenterX()-10);
	}
	public void Right()
	{
		circle.setCenterX(circle.getCenterX()+10);
	}
}
