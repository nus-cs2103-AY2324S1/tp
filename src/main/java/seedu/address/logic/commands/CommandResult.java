package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.dentist.Dentist;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Calendar should be shown to the user. */
    private final boolean showCalendar;

    /** The application should exit. */
    private final boolean exit;

    /** This command interacts directly with the GUI. Default value is {@code false}. */
    private boolean hasGuiInteraction = false;

    /** Dentist selected by user. This value can be null. */
    private Dentist selectedDentist = null;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showCalendar) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showCalendar = showCalendar;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with selected dentist.
     */
    public CommandResult(String feedbackToUser,
                         Dentist selectedDentist) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.selectedDentist = requireNonNull(selectedDentist);
        this.showHelp = false;
        this.exit = false;
        this.showCalendar = false;
        this.hasGuiInteraction = true;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowCalendar() {
        return showCalendar;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean hasGuiInteraction() {
        return this.hasGuiInteraction;
    }

    public Optional<Dentist> getSelectedDentist() {
        return Optional.ofNullable(selectedDentist);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showCalendar == otherCommandResult.showCalendar
                && hasGuiInteraction == otherCommandResult.hasGuiInteraction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showCalendar, hasGuiInteraction);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("showCalendar", showCalendar)
                .add("hasGuiInteraction", hasGuiInteraction)
                .toString();
    }

}
