package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {
  // Declare an array of Strings for flag titles
  private String[] flagTitles = {"Canada", "China", "Denmark", 
      "France", "Germany", "India", "Norway", "United Kingdom",
      "United States of America"};

  // Declare an ImageView array for the national flags of 9 countries
  private ImageView[] flagImage = {new ImageView("image/us.gif"),
      new ImageView("image/us.gif"), 
      new ImageView("image/us.gif"), 
      new ImageView("image/us.gif"), 
      new ImageView("image/us.gif"),
      new ImageView("image/us.gif"), 
      new ImageView("image/us.gif"),
      new ImageView("image/us.gif"), new ImageView("image/us.gif")};

  // Declare an array of strings for flag descriptions
  private String[] flagDescription = new String[9];

  // Declare and create a description pane
  private DescriptionPane descriptionPane = new DescriptionPane();

  // Create a combo box for selecting countries
  private ComboBox<String> cbo = new ComboBox<>(); // flagTitles);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Set text description
	File file0 = new File("e://description0.txt");
	String s0 = "";
	try {
		Scanner sc = new Scanner(file0);
		while(sc.hasNext())
		{
			s0 += sc.next();
			s0 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file1 = new File("e://description1.txt");
	String s1 = "";
	try {
		Scanner sc = new Scanner(file1);
		while(sc.hasNext())
		{
			s1 += sc.next();
			s1 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file2 = new File("e://description2.txt");
	String s2 = "";
	try {
		Scanner sc = new Scanner(file2);
		while(sc.hasNext())
		{
			s2 += sc.next();
			s2 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file3 = new File("e://description3.txt");
	String s3 = "";
	try {
		Scanner sc = new Scanner(file3);
		while(sc.hasNext())
		{
			s3 += sc.next();
			s3 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file4 = new File("e://description4.txt");
	String s4 = "";
	try {
		Scanner sc = new Scanner(file4);
		while(sc.hasNext())
		{
			s4 += sc.next();
			s4 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file5 = new File("e://description5.txt");
	String s5 = "";
	try {
		Scanner sc = new Scanner(file5);
		while(sc.hasNext())
		{
			s5 += sc.next();
			s5 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file6 = new File("e://description6.txt");
	String s6 = "";
	try {
		Scanner sc = new Scanner(file6);
		while(sc.hasNext())
		{
			s6 += sc.next();
			s6 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file7 = new File("e://description7.txt");
	String s7 = "";
	try {
		Scanner sc = new Scanner(file7);
		while(sc.hasNext())
		{
			s7 += sc.next();
			s7 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	File file8 = new File("e://description8.txt");
	String s8 = "";
	try {
		Scanner sc = new Scanner(file8);
		while(sc.hasNext())
		{
			s8 += sc.next();
			s8 += " ";
		}
		sc.close();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    flagDescription[0] = ""+s0;
    flagDescription[1] = ""+s1;
    flagDescription[2] = ""+s2;
    flagDescription[3] = ""+s3;
    flagDescription[4] = ""+s4;
    flagDescription[5] = ""+s5;
    flagDescription[6] = ""+s6;
    flagDescription[7] = ""+s7;
    flagDescription[8] = ""+s8;

    // Set the first country (Canada) for display
    setDisplay(0);

    // Add combo box and description pane to the border pane
    BorderPane pane = new BorderPane();
      
    BorderPane paneForComboBox = new BorderPane();
    paneForComboBox.setLeft(new Label("Select a country: "));
    paneForComboBox.setCenter(cbo);
    pane.setTop(paneForComboBox);
    cbo.setPrefWidth(400);
    cbo.setValue("Canada");
    
    ObservableList<String> items = 
      FXCollections.observableArrayList(flagTitles);
    cbo.getItems().addAll(items);
    pane.setCenter(descriptionPane);
    
    // Display the selected country
    cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 170);
    primaryStage.setTitle("ComboBoxDemo"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /** Set display information on the description pane */
  public void setDisplay(int index) {
    descriptionPane.setTitle(flagTitles[index]);
    descriptionPane.setImageView(flagImage[index]);
    descriptionPane.setDescription(flagDescription[index]);
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
