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

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class fileReader {
    public toDoList readListsFromFile(String filePath) throws IOException{

        String nextDescription = "";
        String nextDate = "";
        boolean nextCompletion = false;
        toDoListEntry nextEntry = new toDoListEntry();
        toDoList fileContents = new toDoList();

        //Taking a file path as an argument, this method opens and scans a text file at the specified path
        try(Scanner fileInput = new Scanner(Paths.get(filePath))){
            while(fileInput.hasNext()){
                //Each line contains the description, the due date, and the completion status of one item
                nextDescription = fileInput.nextLine();
                nextEntry.setDescription(nextDescription);

                nextDate = fileInput.nextLine();
                nextEntry.setDueDate(nextDate);

                nextCompletion = Boolean.parseBoolean(fileInput.nextLine());
                nextEntry.setCompletion(nextCompletion);

                fileContents.addEntry(new toDoListEntry(nextDescription, nextDate, nextCompletion));
                //Continue reading until there are no more lines.
            }
        }

        //Return a to-do list with each entry from the text file.
        return fileContents;
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
    private List<toDoListEntry> entries = new ArrayList<>();

    public void printList() {
        for(toDoListEntry entry : entries){
            System.out.println(entry.getDescription());
            System.out.println(entry.getDueDate());
            System.out.println(entry.getCompletionStatus());
        }
    }

    public void addEntry(toDoListEntry newEntry){
        //Adds a new entry to the list
        entries.add(newEntry);
    }

    public toDoListEntry getEntry(int index){
        //Returns the entry at the specified index
        return entries.get(index);
    }

    public int getLength(){
        //Returns the number of entries in the list
        return entries.size();
    }

    public toDoListEntry deleteEntry(int index){
        //Discards the entry at the specified index
        return null;
    }

    public void clearList(){
        //Discards all entries from the to-do list.
        entries.clear();
        System.out.println("List of entries cleared.");
    }
}

class toDoListEntry {
    private String description;
    private String dueDate;
    private boolean completionStatus;

    toDoListEntry(){
        //Creates a new entry with completionStatus initialized to false
    }

    toDoListEntry(String description, String dueDate, boolean completionStatus){
        //Creates a new entry with due date, name, and completion status
        this.dueDate = dueDate;
        this.description = description;
        this.completionStatus = completionStatus;
    }

    public void setDueDate(String dueDate){
        //Checks if the due date is formatted properly, returns an error message or sets the new due date.
        this.dueDate = dueDate;
    }

    public void setDescription(String description){
        //Sets the new description for the entry.
        this.description = description;
    }

    public void setCompletion(boolean completionStatus){
        //Sets the completion status of an entry.
        this.completionStatus = completionStatus;
    }

    public String getDueDate(){
        //Returns the due date of this item
        return dueDate;
    }

    public String getDescription(){
        //Returns the description of this item
        return description;
    }

    public boolean getCompletionStatus(){
        //Returns the due date of this item
        return completionStatus;
    }
}


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        fileReader filereader = new fileReader();
        toDoList startupList = new toDoList();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        Parent root = loader.load();
        FXMLController controller = loader.<FXMLController>getController();
        controller.setData(startupList);

        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setTitle("To-Do List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}