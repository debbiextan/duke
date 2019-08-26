public class DukeExceptions extends Exception {
    private String errMsg;

    public DukeExceptions(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public String toString() {
        return "☹ OOPS!!! " + errMsg;
    }
}
