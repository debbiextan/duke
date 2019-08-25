public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public String getType() { return type = "T"; }

    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }
}