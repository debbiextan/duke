package models;

import models.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    // contains task list
    public TaskList(ArrayList<Task> tasks) {
        //constructor
        this.tasks = tasks;
    }

    // Create models.Task
    public void addTask(Task t){
        tasks.add(t);
    }

    // Retrieve Tasks
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    // Update Tasks
    public void setTaskDone(int index){
        tasks.get(index).setDone(true);
    }

    // Delete by Object
    public void deleteTask(Task t) {
        tasks.remove(t);
    }

    // Delete by Index
    public void deleteTask(int index) {
        tasks.remove(index);
    }
    
}
