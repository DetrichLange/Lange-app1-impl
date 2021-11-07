/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */

package baseline;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Button importButton = new Button();
    @FXML
    private VBox itemsListBox = new VBox();

    ToDoList workingList;
    List<HBox> boxList = new ArrayList<>();
    FileChooser fileChooser = new FileChooser();
    Scene workingScene;

    public void setData(ToDoList startupList, Scene scene) {
        workingList = startupList;
        workingScene = scene;
    }

    private File chooseFile(){
        //Open a filechooser window to select a file
        Stage stage = (Stage) importButton.getScene().getWindow();
        return fileChooser.showOpenDialog(stage);
    }

    private void makeNewItemBox(){
        //This method is called once by newItemButton (clicking the New Item button) and called repeatedly by
        // populateItemBoxes (when you import)

        //Make two HBoxes
        for(int i=0;i<2;i++){
            boxList.add(new HBox());
            boxList.get(boxList.size() - 1).setPrefHeight(50.0);
            boxList.get(boxList.size() - 1).setSpacing(10.0);
            boxList.get(boxList.size() - 1).setPadding(new Insets(0,0,0,0));
            itemsListBox.getChildren().add(boxList.get(boxList.size() - 1));
        }

        //Make a text area for the entry description
        TextArea itemDescArea = new TextArea();
        itemDescArea.setPrefHeight(50.0);
        itemDescArea.setPrefWidth(338.0);
        itemDescArea.setId("description field " + boxList.size()/2);
        itemDescArea.setText(workingList.getEntry((boxList.size()/2)-1).getDescription());

        //Make a datepicker field for the entry date
        DatePicker itemDateField = new DatePicker();
        itemDateField.getEditor().setDisable(true);
        itemDateField.setPrefHeight(26.0);
        itemDateField.setPrefWidth(118.0);
        itemDateField.setId("date field " + boxList.size()/2);
        try{
            itemDateField.setValue(workingList.getEntry((boxList.size()/2)-1).getDueDate());
        }catch(NullPointerException ignored){} //If there's no date, that's fine

        //Make a checkbox for the entry's completion status
        CheckBox itemCompletionBox = new CheckBox("Complete");
        itemCompletionBox.setId("completion" + boxList.size()/2);
        itemCompletionBox.setUserData(boxList.size() / 2);
        if(workingList.getEntry((boxList.size()/2)-1).getCompletionStatus()){
            itemCompletionBox.setSelected(true);
        }
        itemCompletionBox.setOnAction(e ->
                System.out.println(itemCompletionBox.getId()));

        //Make a button to save the item
        Button itemSaveButton = new Button("Save Item #" + boxList.size()/2);
        itemSaveButton.setUserData(boxList.size() / 2);
        itemSaveButton.setOnAction(e ->
                saveItem((int) itemSaveButton.getUserData(), itemDescArea, itemDateField, itemCompletionBox));

        //Make a button to delete the item
        Button itemDeleteButton = new Button("Delete Item");
        itemDeleteButton.setUserData(boxList.size() / 2);
        itemDeleteButton.setOnAction(e ->
                deleteItem((int) itemDeleteButton.getUserData()));

        //Attach all those things we just made to the HBoxes so that it looks pretty
        boxList.get(boxList.size() - 2).getChildren().add(new Label("Item " + (boxList.size() / 2) + " Description:"));
        boxList.get(boxList.size() - 2).getChildren().add(itemDescArea);
        boxList.get(boxList.size() - 2).getChildren().add(itemSaveButton);
        boxList.get(boxList.size() - 2).getChildren().add(itemDeleteButton);
        boxList.get(boxList.size() - 1).getChildren().add(new Label("Due Date:              "));
        boxList.get(boxList.size() - 1).getChildren().add(itemDateField);
        boxList.get(boxList.size() - 1).getChildren().add(itemCompletionBox);
    }

    private void populateItemBoxes(){
        //This method is called when you import a list from a file
        //Call makeNewItemBox for each entry in the list, so that the entire list is displayed in the GUI
        for(int i=0;i<workingList.getLength();i++){
            makeNewItemBox();
        }
    }

    @FXML
    private void importList() throws IOException {
        //Make a FileReader object
        FileReader fileReader = new FileReader();

        //Call chooseFile to open an explorer window
        File importFile = chooseFile();

        //If a file was selected:
            //Call clearListAndBoxes to empty the entries from the working list and scene
            //Call fileReader.readListsFromFile to populate the new working list
            //Call populateItemBoxes to make a box for each entry in the new working list
        if(importFile != null){
            clearListAndBoxes();
            workingList = fileReader.readListsFromFile(importFile.getPath());
            populateItemBoxes();
        }
        //If a file was not selected, leave the working list and scene as they are
    }

    @FXML
    private void exportList() throws IOException {
        //Make a FileWriter object
        FileWriter fileWriter = new FileWriter();

        //Call chooseFile to open an explorer window
        File exportFile = chooseFile();

        //If a file was chosen:
            //Call writeListsToFile to export the data.
        if(exportFile != null){
            fileWriter.writeListsToFile(exportFile.getPath(), workingList);
        }
        //If no list was chosen, don't do anything.
    }

    @FXML
    private void newItemButton() {
        //This is called by the New Item button at the top of the window.
        //Tell the list to add a new empty entry
        //Make a new box to display and edit that new entry
        workingList.addEntry(new ToDoListEntry());
        makeNewItemBox();
    }

    @FXML
    private void clearListAndBoxes() {
        //When you click the Clear List button on the top panel:
        //Tell the to-do list object to clear itself.
        //Clear the nodes that displayed the old items from the GUI.
        workingList.clearList();
        boxList.clear();
        System.out.println("List of boxes cleared");
        itemsListBox.getChildren().clear();
    }

    @FXML
    private void saveItem(int whichItem, TextArea itemDescArea, DatePicker itemDateField, CheckBox itemCompletionBox) {
        //This method is called by clicking the Save Item button next to an entry.
        //Each button has userData defining which entry it's associated with.
        //The button uses this userData for the whichItem parameter when it calls this method.

        //If the description field is empty, replace it with a default string to meet the assignment requirements.
        String newDesc;
        if(itemDescArea.getText() == null){
            itemDescArea.setText("no description");
        }
        newDesc = itemDescArea.getText();

        //Send the description string, datepicker field, and checkbox status to the to-do list object and tell it to
        //update the appropriate entry.
        workingList.getEntry(whichItem - 1).setDescription(newDesc.substring(0, Math.min(newDesc.length(),256)));
        workingList.getEntry(whichItem - 1).setDueDate(itemDateField.getValue());
        workingList.getEntry(whichItem - 1).setCompletion(itemCompletionBox.isSelected());
    }

    @FXML
    private void deleteItem(int whichItem) {
        //This method is called by clicking the Delete Item button next to an entry.
        //Each button has userData defining which entry it's associated with.
        //The button uses this userData for the whichItem parameter when it calls this method.

        //Tell the to-do list object which entry it should delete.
        workingList.deleteEntry(whichItem - 1);

        //To prevent any gaps in the GUI, clear and re-populate the pane with the updated entries.
        boxList.clear();
        itemsListBox.getChildren().clear();
        populateItemBoxes();
    }

    @FXML
    private void hideShowComplete() {
        //When you click the Hide/Show Complete button

        //For each entry in the working list:
        for(int i=0;i<workingList.getLength();i++){
            //If it's marked as being complete:
            if(workingList.getEntry(i).getCompletionStatus()){
                //Make its GUI nodes invisible and unmanaged.
                boxList.get(i*2).setVisible(!boxList.get(i*2).isVisible());
                boxList.get(i*2).setManaged(!boxList.get(i*2).isManaged());
                boxList.get((i*2)+1).setVisible(!boxList.get((i*2)+1).isVisible());
                boxList.get((i*2)+1).setManaged(!boxList.get((i*2)+1).isManaged());
            }
        }
    }

    @FXML
    private void hideShowIncomplete() {
        //When you click the Hide/Show Incomplete button

        //For each entry in the working list:
        for(int i=0;i<workingList.getLength();i++){
            //If it's marked as being incomplete:
            if(!workingList.getEntry(i).getCompletionStatus()){
                //Make its GUI nodes invisible and unmanaged.
                boxList.get(i*2).setVisible(!boxList.get(i*2).isVisible());
                boxList.get(i*2).setManaged(!boxList.get(i*2).isManaged());
                boxList.get((i*2)+1).setVisible(!boxList.get((i*2)+1).isVisible());
                boxList.get((i*2)+1).setManaged(!boxList.get((i*2)+1).isManaged());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}