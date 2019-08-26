public class DukeException extends Exception {
    private String errMsg;

    public DukeException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public String toString() {
        return "☹ OOPS!!! " + errMsg;
    }
}
