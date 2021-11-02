/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    public void readListsFromFile_test(){
        //This method takes a string file path and returns an array of toDoLists

        //The test will use a path for a text file in the data folder and compare the result of the method
        //to a hardcoded object to confirm that it's reading and creating the objects properly
    }

    @Test
    public void writeListsToFile_test(){
        //This method takes an array of toDoLists and a string filepath, and writes them to a text file.

        //The test will use a hardcoded array and filepath to call the method, then check that the file exists and has
        //the correct contents.
    }

    @Test
    public void toDoList_test(){
        //This test will test getters, setters, and constructors for the toDoList class
        //Make a toDoList, add a title and entry, then check that the object has the correct title and entry.
    }

    @Test
    public void toDoListEntry_test(){
        //This test will test getters, setters, and constructors for the toDoListEntry class
        //Make a toDoListEntry, add a description, date, and completeness
        //then check that the object has the correct attributes.
    }

    @Test
    public void focusOnList_test(){
        //This method should update the GUI with the attributes of the chosen toDoList.
        //hopefully I can test that the contents of the list name text field are changed to match the right list
    }

    @Test
    public void makeNewList_test(){
        //This test will check to see if the children for the list-of-buttons pane on the left side is changed properly
    }

    @Test
    public void saveName_test(){
        //This test will make a toDoList, run the method on it, and check to see if the name is changed.
    }

    @Test
    public void importLists_test(){
        //This test will check to see if the number of children in the list-of-lists pane is correct after importing
    }

    @Test
    public void exportLists_test(){
        //This test will check to see if the exported text file matches a hardcoded expected string
    }

    @Test
    public void newItem_test(){
        //This test will check to see if the number of children in the list-of-items pane is correct after making the new item
    }

    @Test
    public void saveItem_test(){
        //This test will make a toDoListEntry, run the method, and compare the entry's attributes to the expected values.
    }

    @Test
    public void deleteItem_test(){
        //This test will make a toDoListEntry, add it to the GUI, then run the method to delete it and check that it's deleted.
    }

}