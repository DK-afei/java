package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test2 extends Application{
		StackPane sp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage ps) throws Exception {
		// TODO Auto-generated method stub		
		sp = new StackPane();
		HBox hbox = new HBox();
		hbox.setStyle("-fx-border-color: green");
		RadioButton rbArde = new RadioButton("Arde");
		RadioButton rbRectangle = new RadioButton("Rectangle");
		RadioButton rbEllipse = new RadioButton("Ellipse");
		hbox.getChildren().addAll(rbArde,rbRectangle,rbEllipse);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		ToggleGroup group = new ToggleGroup();
		rbArde.setToggleGroup(group);
		rbRectangle.setToggleGroup(group);
		rbEllipse.setToggleGroup(group);
		rbArde.setOnAction(e->{
			sp.getChildren().add(new Circle(100,100,50));
		});
		rbRectangle.setOnAction(e->{
			sp.getChildren().add(new Rectangle(10,10,50,50));
		});
		rbEllipse.setOnAction(e->{
			sp.getChildren().add(new Ellipse(10,10,50,50));
		});
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp,250,250);
		bp.setCenter(sp);
		bp.setBottom(hbox);
		ps.setTitle("ShowTest2");
		ps.setScene(scene);
		ps.show();
	}

}
