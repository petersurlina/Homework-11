/*	Class: SceneController.Java
 * 	This Class acts a Controller to the Scene.fxml file providing various methods on button actions. 
 */

package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

class Person implements Serializable	// allows Serialization of Person Objects
{
	String name;			// Variables stored
	String phoneNumber;
	String DOB;
	String email;
	
	public Person(String n, String p, String d, String e)	// Constructor for 4 Pieces of Information
	{
		this.name = n;
		this.phoneNumber = p;
		this.DOB = d;
		this.email = e;
	}
	
	@Override
	public String toString()								// Class toString() Method
	{
		return "Person [name: " + name + ", Phone Number: "+ phoneNumber + ", Date Of Birth: "+
						DOB + ", Email: " + email + " ]";
	}
}

public class SceneController
{
	
    @FXML						// Simple GUI Element (has no use)
    private Label LabelBox;	
    
    @FXML						// Other elements with use
    private Button AddButton;
    @FXML
    private Button RetrieveButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button UpdateButton;
    @FXML					
    private TextArea infoBox;
    @FXML
    private TextField nameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField DOBText;
    @FXML
    private TextField emailText;
    @FXML
    private Button Exit;

    
    ArrayList <Person> people = new ArrayList<Person>();	// ArrayList to Store Person Objects
    
    
    public static void StringToFile(String a) throws IOException		// Writes String from TextArea to "Information.Bin"
    {
    	ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("Information.bin", true));
    	oStream.writeObject(a);
    }
    
    public static void writeToFile(Person p) throws IOException			// Writes Person Objects to "Information.bin" 
    {
    	ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("Information.bin", true));
    	oStream.writeObject(p);
    }
    
    public void readFile() throws IOException, ClassNotFoundException	// Reads Contents of "Information.bin" 
    {
    	ObjectInputStream iStream = new ObjectInputStream(new FileInputStream("Information.bin"));
    	Person name = (Person) iStream.readObject();
    	infoBox.appendText(name.toString());
    }
    
    @FXML
    void RetrieveInformation(MouseEvent event) 				// Retrieves Data from "Information.Bin"	(*Only Returns first Entry- Could not figure out how to get each entry)
    {
    	try 
    	{
    		for (Person x: people)
			{
				readFile();
			}
		} 
    	catch (ClassNotFoundException | IOException e) 
    	{
    		e.printStackTrace();
    	}
    	
    }
    
    @FXML
    void AddInformation(MouseEvent event) 				// Adds Person Objects to "Information.bin" 
    {
    	
    	String tempName = nameText.getText();			// Set variables to hold data in GUI Elements 
    	String tempPhoneNumber = phoneText.getText();
    	String tempDOB = DOBText.getText();
    	String tempEmail = emailText.getText();
    	
    	Person a = new Person(tempName, tempPhoneNumber, tempDOB, tempEmail);	// Pass the data into a Person Object
    	people.add(a);															// add each new person object to people ArrayList after button Press

    	if (people.size() >= 5)							// once 5 objects are added then they get written to "Information.bin"
    	{
    		try 
    		{
    			for (Person x: people)
    			{
    				writeToFile(x);
    			}
    		}
    		catch (IOException e) 
    		{
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void DeleteInformation(MouseEvent event) 				// Erases Contents of "Information.Bin" leaving it empty
    {
    	try 
    	{
			new FileOutputStream("Information.bin").close();
		}
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	infoBox.setText("Contents Of File Deleted....");
    }
    
    @FXML
    void UpdateInformation(MouseEvent event) 	// Anything Written in the textArea will take over the contents of "Information.bin"
    {
    	try 
    	{
			new FileOutputStream("Information.bin").close();
		}
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	String update = infoBox.getText();
    	try 
    	{
			StringToFile(update);
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
        
    }
    @FXML
    void exit(MouseEvent event) 			// Closes Entire Program
    {
    	System.exit(-1);
    }
}