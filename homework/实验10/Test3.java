package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Test3 extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridpane.setHgap(5.5);
		gridpane.setVgap(5.5);

		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		

		gridpane.add(new Label("Number 1:"), 0, 0);
		gridpane.add(t1, 1, 0);
		gridpane.add(new Label("Number 2:"), 0, 1);
		gridpane.add(t2, 1, 1);
		gridpane.add(new Label("Result :"), 0, 2);
		gridpane.add(t3, 1, 2);

		Button b1 = new Button("Add");
		Button b2 = new Button("Subtract");
		Button b3 = new Button("Multiply");
		Button b4 = new Button("Divide");
		b1.setOnAction(e -> {
			double a = Double.parseDouble(t1.getText());
			double b = Double.parseDouble(t2.getText());
			t3.setText(String.format("%.2f", (a+b)));
		});
		b2.setOnAction(e -> {
			double a = Double.parseDouble(t1.getText());
			double b = Double.parseDouble(t2.getText());
			t3.setText(String.format("%.2f", (a-b)));
		});
		b3.setOnAction(e -> {
			double a = Double.parseDouble(t1.getText());
			double b = Double.parseDouble(t2.getText());
			t3.setText(String.format("%.2f", (a*b)));
		});
		b4.setOnAction(e -> {
			double a = Double.parseDouble(t1.getText());
			double b = Double.parseDouble(t2.getText());
			if(b==0)
			{
				System.out.println("³ýÊý²»Îª0£¡");
				System.exit(1);
			}
			t3.setText(String.format("%.2f", (a/b)));
		});
		HBox hBox = new HBox();
		hBox.getChildren().add(b1);
		hBox.getChildren().add(b2);
		hBox.getChildren().add(b3);
		hBox.getChildren().add(b4);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(5);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridpane);
		borderPane.setBottom(hBox);

		Scene scene = new Scene(borderPane);

		primaryStage.setTitle("ShowTest3");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}