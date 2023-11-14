package networkbook.logic.commands;

/**
 * Represents the result of a list command execution.
 */
public class ListCommandResult extends FilterCommandResult {

    /**
     * Constructs a {@code ListCommandResult} with the specified fields.
     */
    public ListCommandResult(String feedbackToUser) {
        super(feedbackToUser, "none");
    }

}
