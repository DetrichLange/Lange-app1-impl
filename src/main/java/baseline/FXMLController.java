/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */

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
    void importList(ActionEvent event) {
        //When you click the Import button on the bottom panel:
            //Discard the contents of the toDoList
            //Use fileReader to read all the entries from the text file specified in the text field into the toDoList
    }

    @FXML
    void exportList(ActionEvent event) {
        //When you click the Export button on the bottom panel:
            //Open a formatter for a text file with the path specified in the text field
            //For each entry in the list:
                //Use fileWriter to write the item's description, due date, and completeness in the text file
    }

    @FXML
    void newItem(ActionEvent event) {
        //When you click the New Item button on the top panel:
        //Create a new toDoListEntry in the toDoList
        //Make a new pane for that entry in the main panel below the current entries
    }

    @FXML
    void clearList(ActionEvent event) {
        //When you click the Clear List button on the top panel:
        //Remove all contents from the toDoList and redraw.
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

    @FXML
    void hideShowComplete(ActionEvent event) {
        //When you click the Hide/Show Complete button
        //If completed items are currently being shown, redraw the list of items without completed items.
        //If completed items are currently hidden, redraw the list of items with completed items shown.
    }

    @FXML
    void hideShowIncomplete(ActionEvent event) {
        //When you click the Hide/Show Incomplete button
        //If incomplete items are currently being shown, redraw the list of items without incomplete items.
        //If incomplete items are currently hidden, redraw the list of items with incomplete items shown.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}