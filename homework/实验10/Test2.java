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

public class Test2 extends Application{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		Circle circle = new Circle(50,50,20);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.RED);
		pane.getChildren().add(circle);
		
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		
		Button btn1 = new Button("Left");
		Button btn2 = new Button("Right");
		Button btn3 = new Button("Up");
		Button btn4 = new Button("Down");
		
		hBox.getChildren().addAll(btn1,btn2,btn3,btn4);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(hBox);
		borderPane.setCenter(pane);
		BorderPane.setAlignment(hBox,Pos.CENTER);
		
		
		
		Scene scene = new Scene(borderPane,400,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowTest1");
		primaryStage.show();
		
		btn1.setOnAction(e->{circle.setCenterX(circle.getCenterX() > 0 ? circle.getCenterX() - 10 : 0);});  
        btn2.setOnAction(e->{circle.setCenterX(circle.getCenterX() < scene.getWidth() ? circle.getCenterX() + 10 : 0);});  
        btn3.setOnAction(e->{circle.setCenterY(circle.getCenterY() > 0 ? circle.getCenterY() - 10 : 0);});  
        btn4.setOnAction(e->{circle.setCenterY(circle.getCenterY() < scene.getHeight() ? circle.getCenterY() + 10 : 0);});  
    }  
	}

