package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.Company;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution where there is an entity to be displayed in the UI's display box.
 */
public class DisplayableCommandResult extends CommandResult {

    private final Optional<Person> personToDisplay;
    private final Optional<Company> companyToDisplay;

    private boolean isPersonCommand;

    /**
     * Constructs a {@code DisplayableCommandResult} with the specified {@code personToDisplay}.
     */
    public DisplayableCommandResult(String feedbackToUser, Person personToDisplay) {
        super(feedbackToUser, false, false);
        this.personToDisplay = Optional.of(requireNonNull(personToDisplay));
        this.companyToDisplay = Optional.empty();
        this.isPersonCommand = true;
    }

    /**
     * Constructs a {@code DisplayableCommandResult} with the specified {@code companyToDisplay}.
     */
    public DisplayableCommandResult(String feedbackToUser, Company companyToDisplay) {
        super(feedbackToUser, false, false);
        this.companyToDisplay = Optional.of(requireNonNull(companyToDisplay));
        this.personToDisplay = Optional.empty();
        this.isPersonCommand = false;
    }

    /**
     * Returns true if the {@code DisplayableCommandResult} wants to display a {@code Person},
     * and false if it wants to display a {@code Company}.
     */
    public boolean isDisplayingPerson() {
        return isPersonCommand;
    }

    /**
     * Returns the {@code Person} the {@code DisplayableCommandResult} wants to display,
     * and null if there is no person to display.
     */
    public Optional<Person> getPersonToDisplay() {
        return this.personToDisplay;
    }

    /**
     * Returns the {@code Company} the {@code DisplayableCommandResult} wants to display,
     * and null if there is no company to display.
     */
    public Optional<Company> getCompanyToDisplay() {
        return this.companyToDisplay;
    }

    @Override
    public boolean isDisplayableCommandResult() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DisplayableCommandResult)) {
            return false;
        }

        DisplayableCommandResult otherCommandResult = (DisplayableCommandResult) other;

        if (this.isDisplayingPerson() && otherCommandResult.isDisplayingPerson()) {
            // Case: Both commands are displaying the entity "Person".
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && showHelp == otherCommandResult.showHelp
                    && exit == otherCommandResult.exit
                    && personToDisplay.get().equals(otherCommandResult.personToDisplay.get());

        } else if (!this.isDisplayingPerson() && !otherCommandResult.isDisplayingPerson()) {
            // Case: Both commands are displaying the entity "Company".
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && showHelp == otherCommandResult.showHelp
                    && exit == otherCommandResult.exit
                    && companyToDisplay.get().equals(otherCommandResult.companyToDisplay.get());
        } else {
            // Case: Both commands are displaying different entities.
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (isPersonCommand) {
            return Objects.hash(feedbackToUser, showHelp, exit, personToDisplay);
        } else {
            return Objects.hash(feedbackToUser, showHelp, exit, companyToDisplay);
        }
    }

    @Override
    public String toString() {
        if (this.isDisplayingPerson()) {
            return new ToStringBuilder(this)
                    .add("feedbackToUser", feedbackToUser)
                    .add("showHelp", showHelp)
                    .add("exit", exit)
                    .add("personToDisplay", personToDisplay.get())
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("feedbackToUser", feedbackToUser)
                    .add("showHelp", showHelp)
                    .add("exit", exit)
                    .add("companyToDisplay", companyToDisplay.get())
                    .toString();
        }
    }
}
