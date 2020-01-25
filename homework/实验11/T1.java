package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class T1 extends Application {
	private TextField tf1 = new TextField();
	private TextField tf2 = new TextField();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	private void change1()
	{
		double Mile = Double.parseDouble(tf1.getText());
		double Kilometer = Mile*1.6023;
		tf2.setText(""+Kilometer);
		
	}
	private void change2()
	{
		double Kilometer = Double.parseDouble(tf2.getText());
		double  Mile = Kilometer/1.6023;
		tf1.setText(""+Mile);
	}
	@Override
	public void start(Stage ps) throws Exception {
		// TODO Auto-generated method stub
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Mile"),0,0);
		gridPane.add(tf1,1,0);
		gridPane.add(new Label("Kilometer"),0,1);
		gridPane.add(tf2,1,1);
		gridPane.setAlignment(Pos.CENTER);
		tf1.setAlignment(Pos.BOTTOM_RIGHT);
		tf2.setAlignment(Pos.BOTTOM_RIGHT);
		
		tf1.setOnAction(e->{change1();});
		tf2.setOnAction(e->{change2();});

		Scene scene = new Scene(gridPane,250,250);
		ps.setTitle("ShowTest0");
		ps.setScene(scene);
		ps.show();
	}

}