/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */


package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


class fileReader {
    public toDoList[] readListsFromFile(String filePath){
        //Taking a file path as an argument, this method opens and scans a text file at the specified path
        //The first line of the file should be the name of the to-do list, and how many entries it has
        //Each line after that is the description, date, and completeness of a single entry
        //Continue reading until there are no more lines
        //Return an array of the to-do lists from the text file
        return null;
    }
}

class fileWriter {
    public void writeListsToFile(String filePath, toDoList[] saveData){
        //Taking a file path and an array of to-do lists as arguments
        //this method opens a formatter for a text file at the specified path
        //For each item in the array:
            //write one line containing the name of the to-do list and how many entries it has
            //for each entry in the to-do list:
                //write one line containing the description, date, and completeness of a single entry
    }
}

class toDoList {
    private toDoListEntry[] entries;
    private String title;

    toDoList(String title){
        //Constructs a new to-do list with a given title, gives error if title is less than three characters.
    }

    public void changeTitle(String title){
        //Changes the title of the list, gives error if new title is less than three characters.
    }

    public void addEntry(toDoListEntry newEntry){
        //Adds a new entry to the list
    }

    public String getTitle(){
        //Returns the title of the list
        return null;
    }

    public toDoListEntry getEntry(int index){
        //Returns the entry at the specified index
        return null;
    }
}

class toDoListEntry {
    private String dueDate;
    private String description;
    private boolean completionStatus;

    toDoListEntry(){
        //Creates a new entry with completionStatus initialized to false
    }

    public void setDueDate(String newDueDate){
        //Checks if the due date is formatted properly, returns an error message or sets the new due date.
    }

    public void setDescription(String newDescription){
        //Sets the new description for the entry.
    }

    public void toggleCompletion(){
        //Flips the completion status of the entry from true to false or vice-versa.
    }

    public String getDueDate(){
        //Returns the due date of this item
        return null;
    }

    public String getDescription(){
        //Returns the description of this item
        return null;
    }

    public boolean getCompletionStatus(){
        //Returns the due date of this item
        return false;
    }
}


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}