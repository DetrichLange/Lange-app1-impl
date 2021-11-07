/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Detrich Lange
 */

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    //The methods in the controller, if I wrote them right, should only affect the GUI. They add, remove, or alter
    //nodes in the scene, but don't directly mess with the to-do list's data. The methods in the ToDoList,
    //ToDoListEntry, FileReader, and FileWriter classes are called to work with that data, so those are what I'm testing.

    @Test
    public void readListsFromFile_test() throws IOException {
        //This method takes a string file path and returns an array of toDoLists

        FileReader testReader = new FileReader();

        //Use readListsFromFile on a test file in the data folder
        ToDoList testList = testReader.readListsFromFile("data/testFile.txt");
        String actual = testList.getEntry(0).getDescription();

        //Compare it to a hardcoded string
        String expected = "This is the description of the first item.";

        assertEquals(expected, actual);
    }

    @Test
    public void writeListsToFile_test() throws IOException {
        //This method takes a ToDoList and a string filepath, and writes the list to a text file.

        //Create a ToDoList
        ToDoList testList = new ToDoList();
        for(int i=0;i<100;i++){
            //Populate it with 100 entries
            testList.addEntry(new ToDoListEntry(
                    "This is entry number " + (i+1) + " for the writing test", null, false));
        }

        //Write that list to a file in the docs folder
        FileWriter testWriter = new FileWriter();
        testWriter.writeListsToFile("data/testFile2.txt", testList);

        //Read the file that we just wrote
        FileReader testReader = new FileReader();
        ToDoList testList2 = testReader.readListsFromFile("data/testFile2.txt");

        //Compare the last line in the entry to our hardcoded string
        String expected = "This is entry number 100 for the writing test";
        String actual = testList2.getEntry(99).getDescription();

        assertEquals(expected, actual);
    }

    @Test
    public void list_Test(){
        //This test will test getters, setters, and constructors for the ToDoList and ToDoListEntry classes

        //Make a to-do list
        ToDoList testList = new ToDoList();
        //Make a blank entry
        ToDoListEntry testEntry = new ToDoListEntry();
        //Set a description, date, and completion for that entry
        testEntry.setDescription("Test description");
        testEntry.setDueDate(LocalDate.of(2023, 12, 13));
        testEntry.setCompletion(true);
        //Add the entry to the list
        testList.addEntry(testEntry);

        //Get the parameters of the to-do list's first entry and compare them
        String actual = testList.getEntry(0).getDescription() +
                testList.getEntry(0).getDueDate().toString() + testList.getEntry(0).getCompletionStatus();
        String expected = "Test description2023-12-13true";

        assertEquals(expected, actual);
    }

}