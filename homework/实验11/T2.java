package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class T2 extends Application {
	StackPane stackPane;
	Label l1;
	Label l2;
	private String[] countries = { "Canada", "China", "Danmark", "France", "Germany", "India", "Norway" };
	private String[] items = {"Single","Multiply"};
	ListView<String> lv;
	ComboBox<String> cbo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	public void Single()
	{
		lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
			for (Integer i : lv.getSelectionModel().getSelectedIndices()) {
				l1.setText("Selected items are :" + countries[i]);
			}
		});
		
	} 
	public void Multiply()
	{
		lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
			String s = "";
			for (Integer i : lv.getSelectionModel().getSelectedIndices()) {
				s += countries[i]+" ";
			}
			l1.setText("Selected items are :" + s);
		});
	}
	@Override
	public void start(Stage ps) throws Exception {
		// TODO Auto-generated method stub
		l1 = new Label();
		l1.setAlignment(Pos.CENTER);
		l2 = new Label();
		l2.setAlignment(Pos.CENTER);
		// l.setText(arg0);
		stackPane = new StackPane();
		cbo = new ComboBox<>();
		cbo.getItems().addAll(items[0],items[1]);
		cbo.setStyle("-fx-color: green");
		cbo.setValue("Single");

		HBox hBox1 = new HBox();
		hBox1.getChildren().add(l1);
		hBox1.setStyle("-fx-border-color: green");

		HBox hBox2 = new HBox();
		hBox2.getChildren().add(l2);
		hBox2.getChildren().add(cbo);
		hBox2.setStyle("-fx-border-color: green");

		lv = new ListView<>(FXCollections.observableArrayList(countries));
		lv.setPrefSize(400, 400);
		cbo.setOnAction(e->{
			if(cbo.getValue()==items[0])
			{
				Single();
			}
			else if(cbo.getValue()==items[1])
			{
				Multiply();
			}
		});
	
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(stackPane);
		borderPane.setBottom(hBox1);
		borderPane.setTop(hBox2);
		borderPane.setRight(new ScrollPane(lv));
		Scene scene = new Scene(borderPane, 500, 250);
		ps.setTitle("ShowTest1");
		ps.setScene(scene);
		ps.show();

	}

}
