package models;

public class Task {
    private String description;
    private boolean isDone;
    protected String type;

    /**
     * Task constructor
     * @param description of Task object
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Get status icon on Task object
     * @return Status Icon/Symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get type of Task object
     * @return Task object type "T"
     */
    public String getType() { return type = "T"; }

    /**
     * Get status of Task object
     * @return boolean containing Task object status
     */
    public boolean getDone(){
        return isDone;
    }

    /**
     * Set status of Task object
     * @param done boolean of Task status
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Get description of Task object
     * @return String containing Task object description
     */
    public String getDescription() {
        return this.description;
    }
}