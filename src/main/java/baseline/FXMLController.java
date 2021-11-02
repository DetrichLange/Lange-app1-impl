/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */

package baseline;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FXMLController implements Initializable {

    @FXML
    private Label fileLabel;
    @FXML
    private Button importButton = new Button();
    @FXML
    private Button exportButton = new Button();
    @FXML
    private Button newItemButton = new Button();
    @FXML
    private Button clearListButton = new Button();
    @FXML
    private TextField pathTextBox = new TextField();
    @FXML
    private HBox itemDesc1 = new HBox();
    @FXML
    private HBox itemDue1 = new HBox();
    @FXML
    private VBox itemsListBox = new VBox();
    @FXML
    toDoList workingList;
    @FXML
    private Button printListButton = new Button();
    @FXML
    private HBox[] boxArray = new HBox[200];

    fileReader filereader = new fileReader();
    List<HBox> boxList = new ArrayList<>();

    @FXML
    public void setData(toDoList startupList) {
        workingList = startupList;
    }

    @FXML
    public void printList() {
        workingList.printList();
    }

    @FXML
    void importList(ActionEvent event) {
        //When you click the Import button on the bottom panel:
            //Discard the contents of the toDoList
            //Use fileReader to read all the entries from the text file specified in the text field into the toDoList
        workingList.clearList();

        try{
            workingList = filereader.readListsFromFile(pathTextBox.getText());
            System.out.println("List imported");
        }catch(IOException e){
            System.out.println("Could not find.");
        }

        updateList();
    }

    @FXML
    void updateList(){

        for(int i=0;i< workingList.getLength();i++){

        }
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

        workingList.addEntry(new toDoListEntry());

        for(int i=0;i<2;i++){
            boxList.add(new HBox());
            boxList.get(boxList.size() - 1).setPrefHeight(50.0);
            boxList.get(boxList.size() - 1).setSpacing(10.0);
            boxList.get(boxList.size() - 1).setPadding(new Insets(0,0,0,0));
            itemsListBox.getChildren().add(boxList.get(boxList.size() - 1));
            System.out.println("Added a box");
        }

        TextArea itemDescArea = new TextArea();
        itemDescArea.setPrefHeight(50.0);
        itemDescArea.setPrefWidth(338.0);

        Button itemSaveButton = new Button("Save Item");
        itemSaveButton.setUserData(boxList.size() / 2);
        itemSaveButton.setOnAction(e -> {
            System.out.println("Saving #" + itemSaveButton.getUserData());
        });

        Button itemDeleteButton = new Button("Delete Item");
        itemDeleteButton.setUserData(boxList.size() / 2);
        itemDeleteButton.setOnAction(e -> {
            System.out.println("Deleting #" + itemDeleteButton.getUserData());
        });

        TextField itemDateField = new TextField();
        itemDateField.setPrefHeight(26.0);
        itemDateField.setPrefWidth(118.0);
        itemDateField.setPromptText("YYYY-MM-DD");

        CheckBox itemCompletionBox = new CheckBox("Complete");
        itemCompletionBox.setUserData(boxList.size() / 2);
        itemCompletionBox.setOnAction(e -> {
            System.out.println("Toggle completion #" + itemCompletionBox.getUserData());
        });

        boxList.get(boxList.size() - 2).getChildren().add(new Label("Item " + (boxList.size() / 2) + " Description:"));
        boxList.get(boxList.size() - 2).getChildren().add(itemDescArea);
        boxList.get(boxList.size() - 2).getChildren().add(itemSaveButton);
        boxList.get(boxList.size() - 2).getChildren().add(itemDeleteButton);
        boxList.get(boxList.size() - 1).getChildren().add(new Label("Due Date:            "));
        boxList.get(boxList.size() - 1).getChildren().add(itemDateField);
        boxList.get(boxList.size() - 1).getChildren().add(itemCompletionBox);
    }

    @FXML
    void clearList(ActionEvent event) {
        //When you click the Clear List button on the top panel:
        //Clear the working list, clear the boxlist, clear the child nodes from the item list vbox.

        workingList.clearList();
        boxList.clear();
        System.out.println("List of boxes cleared");
        itemsListBox.getChildren().clear();
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