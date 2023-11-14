package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Person;


/**
 * Unhides all applicants from the list of all applicants.
 */
public class UnhideAllCommand extends Command {

    public static final String COMMAND_WORD = "unhide-all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unhides all applicants in all future lists of applicants.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All applicants unhidden from lists.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Model modelCopy = new ModelManager(model.getAddressBook(), model.getUserPrefs());
        modelCopy.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_HIDDEN_PERSONS);
        List<Person> hiddenList = modelCopy.getFilteredPersonList();
        hiddenList.forEach(p -> model.setPerson(p, createUnhiddenPerson(p)));
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_UNHIDDEN_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    /**
     * Creates and returns an unhidden {@code Person} with the details of {@code personToUnhide}
     * @param personToUnhide {@code Person} to unhide
     * @return {@code Person} with {@code IsHidden} set to false
     */
    private static Person createUnhiddenPerson(Person personToUnhide) {
        assert personToUnhide != null;

        return new Person(
                personToUnhide.getStudentNumber(),
                personToUnhide.getName(),
                personToUnhide.getPhone(),
                personToUnhide.getEmail(),
                personToUnhide.getGpa(),
                personToUnhide.getPreviousGrade(),
                personToUnhide.getInterviewScore(),
                personToUnhide.getComment(),
                personToUnhide.getTags(),
                personToUnhide.getAttachments(),
                new IsHidden(false),
                personToUnhide.getIsBookmarked());
    }
}
