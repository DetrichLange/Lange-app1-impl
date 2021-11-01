package baseline;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button buttonMakeLabel = new Button();
    @FXML
    private TextField entryTextBox = new TextField();
    @FXML
    private VBox boxOfLabels = new VBox();


    @FXML
    void focusOnList(ActionEvent event) {
        //When you click the button on the left panel for a specific list:
            //the main panel should populate with the information for that list.
            //Change the contents of the List Name text box to the focused list's name
            //remove any list entries from the previously focused list
            //create a pane with list entry details for each entry in the list in the vbox below the list's name
    }

    @FXML
    void makeNewList(ActionEvent event) {
        //When you click the New List button on the bottom panel:
            //Create a new entry in the array of toDoLists
            //Create a new button in the left panel for that list
    }

    @FXML
    void saveName(ActionEvent event) {
        //When you click the Save Name button on the top panel:
            //Update the focused list's name parameter with the contents of the name text field
    }

    @FXML
    void importLists(ActionEvent event) {
        //When you click the Import button on the bottom panel:
            //Discard the contents of the array of to-do lists
            //Use fileReader to read all the to-do lists from the text file specified in the text field into an array
            //For each to-do list in the array:
                //Create a button in the left panel
    }

    @FXML
    void exportLists(ActionEvent event) {
        //When you click the Export button on the bottom panel:
            //Open a formatter for a text file with the path specified in the text field
            //For each to-do list in the array:
                //Use fileWriter to write the list and each of its entries in the text file
    }

    @FXML
    void newItem(ActionEvent event) {
        //When you click the New Item button on the top panel:
        //Create a new toDoListEntry in the array of the focused list
        //Make a new pane for that entry in the main panel below the current entries
    }

    @FXML
    void saveItem(ActionEvent event) {
        //When you click the Save Item button for a specific to-do list entry:
            //Save the string in the due date text field to that entry's attributes
            //Do the same for description and completeness
    }

    @FXML
    void deleteItem(ActionEvent event) {
        //When you click the Delete Item button for a specific to-do list entry:
            //Remove that entry from the to-do list's array of entries
            //Remove that entry's pane
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
    }
}