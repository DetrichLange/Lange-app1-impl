/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */


package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

class FileReader {
    public ToDoList readListsFromFile(String filePath) throws IOException{
        //Taking a file path as an argument, this method opens and scans a text file at the specified path
        //Each line contains the description, the due date, or the completion status of one item

        String nextDescription;
        LocalDate nextDate;
        boolean nextCompletion;
        ToDoList fileContents = new ToDoList(); //This is the object that stores and works with all the entries.

        try(Scanner fileInput = new Scanner(Paths.get(filePath))){
            while(fileInput.hasNext()){ //Each item is exactly three lines
                //The first line is the description.
                //Line breaks that the user enters into the description are exported as tags, tags are converted back
                //to line breaks on import.
                nextDescription = fileInput.nextLine().replaceAll("<br>", "\n");

                String nextDateString = fileInput.nextLine(); //Store the date as a string to check if it's null
                if(!nextDateString.equals("null")){ //The date might be null, in accordance with the requirements
                    nextDate = LocalDate.parse(nextDateString); //Trying to parse null value as a date would throw an exception
                }else{
                    nextDate = null;
                }

                nextCompletion = Boolean.parseBoolean(fileInput.nextLine()); //Completion status is just "true" or "false"

                //Add one entry to the to-do list object with all three of those parameters.
                //Date might be null, description and completion status are not null.
                fileContents.addEntry(new ToDoListEntry(nextDescription, nextDate, nextCompletion));

                //Continue reading until there are no more lines.
            }
        }catch(NoSuchElementException e){
            //If you somehow manage to try to read a file that doesn't exist, it gives you an empty to-do list.
            return new ToDoList();
        }

        //Once it's all read, return a to-do list with each entry from the text file.
        return fileContents;
    }
}

class FileWriter {
    public void writeListsToFile(String filePath, ToDoList saveData) throws FileNotFoundException {
        //Taking a file path and an array of to-do lists as arguments,
        //this method opens a formatter for a text file at the specified path

        try(Formatter output = new Formatter(filePath)) {

            //For each entry:
            for(int i=0;i<saveData.getLength();i++){
                //If the description is empty, replace it with a default description per the requirements.
                String nextDesc = saveData.getEntry(i).getDescription();
                if(nextDesc == null){
                    nextDesc = "no description";
                }
                //Output the description, the due date, and the completion status on three sequential lines.
                output.format("%s%n%s%n%s%n", nextDesc.replaceAll("\n", "<br>"),
                        saveData.getEntry(i).getDueDate(), saveData.getEntry(i).getCompletionStatus());
            }
        }
    }
}

class ToDoList {
    //A to-do list is created as the program starts, and it stores a list of entries.
    private List<ToDoListEntry> entries = new ArrayList<>();

    public void addEntry(ToDoListEntry newEntry){
        //Adds a new entry to the list
        entries.add(newEntry);
    }

    public ToDoListEntry getEntry(int index){
        //Returns the entry at the specified index
        return entries.get(index);
    }

    public int getLength(){
        //Returns the number of entries in the list
        return entries.size();
    }

    public void deleteEntry(int index){
        //Discards the entry at the specified index
        entries.remove(index);
    }

    public void clearList(){
        //Discards all entries from the to-do list.
        entries.clear();
    }
}

class ToDoListEntry {
    //Each ToDoListEntry
    private String description;
    private LocalDate dueDate;
    private boolean completionStatus;

    public ToDoListEntry(){
        //This constructor is used when the user hits the New Item button
        //Creates a new entry with completionStatus initialized to false and description set to "no description", which
        //is technically a description for the requirements.
        description = "no description";
        completionStatus = false;
    }

    public ToDoListEntry(String description, LocalDate dueDate, boolean completionStatus){
        //This constructor is used by the import method.
        //Creates a new entry with due date, name, and completion status initialized.
        this.dueDate = dueDate;
        this.description = description;
        this.completionStatus = completionStatus;
    }

    public void setDueDate(LocalDate dueDate){
        //Sets the new due date for this entry. DatePicker should ensure that any dates passed are valid or null.
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

    public LocalDate getDueDate(){
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
        FileReader filereader = new FileReader();
        ToDoList startupList = new ToDoList();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        Parent root = loader.load();
        FXMLController controller = loader.<FXMLController>getController();

        Scene scene = new Scene(root);
        controller.setData(startupList, scene);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setTitle("To-Do List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}