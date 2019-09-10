package models;

public class DukeException extends Exception {
    private String errMsg;

    /**
     * Duke Exception constructor
     * @param errMsg String containing error message
     */
    public DukeException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    /**
     * toString method for error message
     * @return String of error message with custom "OOPS" message
     */
    public String toString() {
        return "☹ OOPS!!! " + errMsg;
    }
}
