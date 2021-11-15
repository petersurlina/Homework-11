/* Assignment: Serialization in Java (HW11)
 * Name: Peter Surlina
 * Dates: 11-08-2021
 * Description: using Serialization write and synthesize the presenter’s program.
 * Use and collect the following information:
 *	-Name
 *	-Phone Number
 *	-Date of birth
 *	-Email Address
 * Create a menu driven program as following:
 *		1) Add information into a file.
 *		2) Retrieve information from a file and display them.
 *		3) Delete information.
 *		4) Update information.
 *		5) Exit.
 *	Create At least 5 Entries
 */
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 	// Code to Set the Scene to "Scene.fxml" file
	{
		try 
		{
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Scene.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
