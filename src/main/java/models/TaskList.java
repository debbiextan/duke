package models;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor
     * @param tasks ArrayList of Task objects input by user
     */
    // contains task list
    public TaskList(ArrayList<Task> tasks) {
        //constructor
        this.tasks = tasks;
    }

    /**
     * Add Task object to ArrayList of Task objects
     * @param t a single Task object
     */
    // Create models.Task
    public void addTask(Task t){
        tasks.add(t);
    }

    /**
     * Get Task objects from an ArrayList of Task objects
     * @return ArrayList of Task objects
     */
    // Retrieve Tasks
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * Set Task object as done according to user input Task index
     * @param index number of Task object
     */
    // Update Tasks
    public void setTaskDone(int index){
        tasks.get(index).setDone(true);
    }

    /**
     * Remove Task object from ArrayList of Task objects based on Task object
     * @param t a Task object
     */
    // Delete by Object
    public void deleteTask(Task t) {
        tasks.remove(t);
    }

    /**
     * Overload method
     * Remove Task objext from ArrayList of Task objects based on Task index
     * @param index index of a Task object
     */
    // Delete by Index
    public void deleteTask(int index) {
        tasks.remove(index);
    }
    
}
