package project;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RadioButtonDemo extends CheckBoxDemo {
	@Override // Override the getPane() method in the super class
	protected BorderPane getPane() {
		BorderPane pane = super.getPane();

		VBox paneForRadioButtons = new VBox(20);
		paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
		paneForRadioButtons.setStyle("-fx-border-color: green");
		paneForRadioButtons.setStyle("-fx-border-width: 2px; -fx-border-color: green");
		RadioButton rbRed = new RadioButton("Red");
		RadioButton rbYellow = new RadioButton("Yellow");
		RadioButton rbBlack = new RadioButton("Black");
		RadioButton rbOrange = new RadioButton("Orange");
		RadioButton rbGreen = new RadioButton("Green");
		paneForRadioButtons.getChildren().addAll(rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
		pane.setLeft(paneForRadioButtons);

		ToggleGroup group = new ToggleGroup();
		rbRed.setToggleGroup(group);
		rbGreen.setToggleGroup(group);
		rbYellow.setToggleGroup(group);
		rbBlack.setToggleGroup(group);
		rbOrange.setToggleGroup(group);
		rbRed.setOnAction(e -> {
			if (rbRed.isSelected()) {
				text.setFill(Color.RED);
			}
		});

		rbGreen.setOnAction(e -> {
			if (rbGreen.isSelected()) {
				text.setFill(Color.GREEN);
			}
		});

		rbYellow.setOnAction(e -> {
			if (rbYellow.isSelected()) {
				text.setFill(Color.YELLOW);
			}
		});
		rbBlack.setOnAction(e -> {
			if (rbBlack.isSelected()) {
				text.setFill(Color.BLACK);
			}
		});
		rbOrange.setOnAction(e -> {
			if (rbOrange.isSelected()) {
				text.setFill(Color.ORANGE);
			}
		});

		return pane;
	}

	public static void main(String[] args) {
		launch(args);
	}
}