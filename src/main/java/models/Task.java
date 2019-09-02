package models;

public class Task {
    private String description;
    private boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getType() { return type = "T"; }

    public boolean getDone(){
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return this.description;
    }
}