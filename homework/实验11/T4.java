package project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class T4 extends Application{
	Label l1,l2,l3,l4;
	TextField t1,t2,t3,t4;
	ImageView [] cars = new ImageView[4];
	static final int maxSpeed = 100;
	private Timeline animation;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	public void moveCar(int i)
	{
		cars[i].setX(cars[i].getX()+10);
	}
	public void setSpeed(int i,int speed)
	{
		moveCar(i);
		if(speed>0&&speed<=maxSpeed) 
		{
			animation = new Timeline(new KeyFrame(Duration.millis(50),e-> moveCar(i)));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}
	}
	@Override
	public void start(Stage ps) throws Exception {
		// TODO Auto-generated method stub
		l1 = new Label("Car 1:");
		l2 = new Label("Car 2:");
		l3 = new Label("Car 3:");
		l4 = new Label("Car 4:");
		t1 = new TextField();
		t1.setOnAction(e->{
			setSpeed( 0,Integer.parseInt(t1.getText()));
		});
		t2 = new TextField();
		t2.setOnAction(e->{
			setSpeed( 1,Integer.parseInt(t2.getText()));
		});
		t3 = new TextField();
		t3.setOnAction(e->{
			setSpeed( 2,Integer.parseInt(t3.getText()));
		});
		t4 = new TextField();
		t4.setOnAction(e->{
			setSpeed(3, Integer.parseInt(t4.getText()));
		});
		HBox hBox = new HBox();
		hBox.getChildren().addAll(l1,t1,l2,t2,l3,t3,l4,t4);
		VBox vBox = new VBox();
		Pane pane1 = new Pane();
		Pane pane2 = new Pane();
		Pane pane3 = new Pane();
		Pane pane4 = new Pane();
		vBox.getChildren().addAll(pane1,pane2,pane3,pane4);
		vBox.setSpacing(20);
		cars[0] = new ImageView(new Image("image/car.gif"));
		cars[0].setFitHeight(100);
		cars[0].setFitWidth(200);
		cars[1] = new ImageView(new Image("image/car.gif"));
		cars[1].setFitHeight(100);
		cars[1].setFitWidth(200);
		cars[2] = new ImageView(new Image("image/car.gif"));
		cars[2].setFitHeight(100);
		cars[2].setFitWidth(200);
		cars[3] = new ImageView(new Image("image/car.gif"));
		cars[3].setFitHeight(100);
		cars[3].setFitWidth(200);
		pane1.getChildren().add(cars[0]);
		pane2.getChildren().add(cars[1]);
		pane3.getChildren().add(cars[2]);
		pane4.getChildren().add(cars[3]);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBox);
		borderPane.setCenter(vBox);
		Scene scene = new Scene(borderPane,780,500);
		ps.setScene(scene);
		ps.setTitle("ShowT4");
		ps.show();
	}

}
