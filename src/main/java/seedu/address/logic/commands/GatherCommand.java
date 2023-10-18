package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;

/**
 * Gathers and lists all persons in address book emails whose details contain a given Financial Plan.
 * Keyword matching is case sensitive.
 */
public class GatherCommand extends Command {

    public static final String COMMAND_WORD = "gather";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gathers all the persons emails whose financial "
            + "plan matches the desired financial plan and display it on the output.\n"
            + "Parameters: FINANCIAL_PLAN\n"
            + "Example: " + COMMAND_WORD + " Sample Financial Plan 1";

    public static final String MESSAGE_NO_PERSON_FOUND = "0 Person were found with ";

    private final String prompt;

    /**
     * Constructs a new GatherCommand object.
     * @param prompt The user's prompt
     */
    public GatherCommand(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Overrides Command execute method.
     * @param model {@code Model} which the command should operate on.
     * @return The CommandResult depending on the user input
     */
    @Override
    public CommandResult execute(Model model) {
        String emails = model.gatherEmails(prompt);
        if (emails.isEmpty()) {
            return new CommandResult(MESSAGE_NO_PERSON_FOUND + prompt);
        }
        return new CommandResult(emails);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GatherCommand)) {
            return false;
        }

        GatherCommand otherGatherCommand = (GatherCommand) other;
        return prompt.equals(otherGatherCommand.prompt);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("prompt", prompt)
                .toString();
    }

}
